package Calc;

import java.util.ArrayList;

public class Calc {
	static ArrayList<DB> fo_temp = new ArrayList<>();//使った数字と演算子を削除していくList。
	//計算の呼び出し(C2_Braket()を実行）。
	public static void Main(){
		for(DB st : DB.formula) {fo_temp.add(st);}
		System.out.println("Ans = "+C2_Braket(0,fo_temp.size()));
		while(fo_temp.size()>0)  {fo_temp.remove(0);}
		while(DB.formula.size()>0) {DB.formula.remove(0);}
		while(DB.element.size()>0) {DB.element.remove(0);}
	}

	//演算,数字の間に演算子がない場合は×の処理（バグありそう）。st=Start,en=End
	private static double C1_Operator(int st , int en) {
		double  num_temp ,	ans = fo_temp.get(st).getEle() ;
		int 	ope_temp=0 ;
		for(int i=st+1 ; i<en ; i++) {
			switch(fo_temp.get(i).getTyp()) {
			case 3://演算子+-
			case 4://演算子*/
				ope_temp = fo_temp.get(i).getOpe();
				break;
			case 2://数字
				num_temp = fo_temp.get(i).getEle();
				if(ope_temp==1) {
					ans += num_temp;
				}else if(ope_temp==2) {
					ans -= num_temp;
				}else if(ope_temp==3||ope_temp==0) {
					ans *= num_temp;
				}else if(ope_temp==4 && num_temp!=0) {
					ans /= num_temp;
				}else if(ope_temp==4 && num_temp==0) {
					System.out.println("Error:Can't div by 0 :count "+i);
					return 0;
				}
				break;
			default:System.out.println("Error:C1_Operator");
			}
		}
		/*処理した区間のArrayListを削除して、先頭に計算結果を保存する*/
		for(int i = 0 ; i < en-st-1 ; i++) {fo_temp.remove(st+1);}
		fo_temp.set(st,new DB("C1O",2,0,ans));
		return ans;
	}
	//*/の処理。左から順に*/がある前後の数字の場所st,enをC1_Operator()に投げる。
	private static double C2_Operator(int st , int en) {
		double num = 0 ;
		while(JudgeMulDiv(st,en)) {
			int index = SerchId_Typ(4 , st , en);
			num = C1_Operator(index-1,index+2);
			en-=2 ;
		}
		num = C1_Operator(st,en);
		return num ;
	}
	//()の処理。(を認識して、()のindex番号を把握してst,enをC2_operator()に投げる。
	private static double C2_Braket(int st , int en) {
		double num = 0 ;
		while(JudgeBraket(0,fo_temp.size())) {
			int l_index = SerchId_Typ(1 , 0 , fo_temp.size());
			int s_index = SerchLastId_Typ(0 , 0 , l_index);
			num = C2_Operator(s_index+1,l_index);
			//使用済みの()の削除。(の前に演算子がない場合の条件分岐あり。
			if(fo_temp.get(s_index-1).getTyp()==2) {
				fo_temp.remove(s_index+1);
				fo_temp.set(s_index+1,new DB("C2B",2,0,num));
				fo_temp.set(s_index,new DB("C2B*",4,3,0));
			}else {
				for(int i=0 ; i<2 ;i++) {
					fo_temp.remove(s_index+1);
				}
				fo_temp.set(s_index,new DB("C2B",2,0,num));
			}
		}
		num = C2_Operator(0,fo_temp.size());
		return num ;
	}
	
	
	/*	↓以降はミニメソッド			*/
	/*Listに()や×÷があるか判別*/
	public static boolean JudgeMulDiv(int st ,int en) {// *or/があればtrue
		for(int i=st ; i<en;i++) {
			if(fo_temp.get(i).getTyp()==4) {
				return true ;
			}
		}
		return false ;
	}
	public static boolean JudgeBraket(int st , int en) {// (or)があればtrue
		for(DB li : fo_temp) {
			if(li.getTyp()==0||li.getTyp()==1) {
				return true ;
			}
		}
		return false ;
	}
	
	
	/*ListからIndex番号の検索(2種類）*/
	private static int SerchId_Typ(int target,int st,int en) {
		//順方向検索
		for(int i=st;i<en;i++) {
			if(fo_temp.get(i).getTyp()==target) {return i;}
		}
		System.out.println("Error:Calc.SerchId_Typ");
		return 0 ;
	}
	private static int SerchLastId_Typ(int target,int st ,int en) {
		//逆方向検索
		for(int i=en-1;i>=st;i--) {
			if(fo_temp.get(i).getTyp()==target) {return i;}
		}
		System.out.println("Error:Calc.SerchLastId_Typ");
		return 0 ;
	}
	
	
}
