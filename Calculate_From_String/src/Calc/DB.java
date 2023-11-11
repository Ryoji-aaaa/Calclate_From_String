package Calc;
import java.util.ArrayList;

public class DB {
	//elementに数字と記号それぞれに分割して格納。
	static ArrayList<String> element = new ArrayList<>(); 
	public static void Sampling(String str) {
		String str0 = str.replaceAll(" ","");
		for(int i=0 ; i<str0.length() ; i++) {
			
			if(Character.isDigit(str0.charAt(i))) /*数字の場合*/{
				int count_j = 0 ;
				String temp_num = "";
				for(int j=0 ;Character.isDigit(str0.charAt(i+j));j++){
					temp_num += str0.charAt(i+j);
					count_j = j ;
					if(i+j+1 >= str0.length() ) {break;}
				}
				element.add(temp_num);
				i += count_j;
			}else/*記号の場合*/ {
				String temp_s = String.valueOf(str0.charAt(i));
				element.add(temp_s);
			}	
		}
	}
	/*それぞれに属性を振るobject型
	 * type 	:{0,1,2,3,4}	={( , )　,数字　,　+-演算子 , ×÷演算子 }
	 * operator	:{0,1,2,3,4}={その他 , + , - , * , / }
	 */
	static ArrayList<DB> formula = new ArrayList<>();
	private String	Str ;
	private int 	Typ , Ope ;
	private double 	Ele ;
	public DB(String str , int type ,int operator , double element) {
		this.Str = str;
		this.Typ = type;
		this.Ope = operator;
		this.Ele = element;
	}
	/*アクセサ*/
	String getStr() {return Str;}
	int getTyp() {return Typ;}
	int getOpe() {return Ope;}
	double getEle() {return Ele;}
	//elementを判別してFormulaに格納。
	public static void FillFormula() {
		for(int i=0 ; i<element.size() ; i++) {
			String str = element.get(i);
			switch(element.get(i)) {
			case "(":
				formula.add(new DB(str,0,0,0));break;
			case ")":
				formula.add(new DB(str,1,0,0));break;
			case "+":
				formula.add(new DB(str,3,1,0));break;
			case "-":
				formula.add(new DB(str,3,2,0));break;
			case "*":
			case "＊":
			case "×":
				formula.add(new DB(str,4,3,0));break;
			case "/":
			case "÷":
				formula.add(new DB(str,4,4,0));break;
			default:
				if(Character.isDigit(element.get(i).charAt(0))) {
				Double num = Double.valueOf(element.get(i));
				formula.add(new DB(str,2,0,num));
				}else {System.out.println("error : DB.FillFormura()");}
			}
		}
	}
	//DBの中身確認用。
	public static void Display() {
		System.out.println("(Str)  ( Type , Ope , Ele )");
        System.out.println("---------------------------");
        for(int i=0 ; i<DB.formula.size();i++) {
         System.out.printf("%4s   (%3d , %3d , %.2f  ) \n"
        		 ,DB.formula.get(i).getStr()
        		 ,DB.formula.get(i).getTyp()
        		 ,DB.formula.get(i).getOpe()
        		 ,DB.formula.get(i).getEle());
        }
	}
	public static void Display2(ArrayList<DB> list) {
		System.out.println("(Str)  ( Type , Ope , Ele )");
        System.out.println("---------------------------");
        for(int i=0 ; i<list.size();i++) {
         System.out.printf("%4s   (%3d , %3d , %.2f  ) \n"
        		 ,list.get(i).getStr()
        		 ,list.get(i).getTyp()
        		 ,list.get(i).getOpe()
        		 ,list.get(i).getEle());
        }
	}
	
}
