package Calc;

public class Test1 {
	public static void main(String args[]) {
		String[] test = new String[4];
		test[0] ="1+2 * 2" ;
		test[1] ="2×(4 + 3 )" ;
		test[2] ="1+( 2 ( 3 + (( 5 ÷ 2 ) - 3 )))";
		test[3]="1+1+3*100(1+1)";
		//文字列から数字、演算子の分離、抽出。
		for(int i=0 ; i<test.length ;i++) {
		System.out.println(test[i]);
		DB.Sampling(test[i]);
		DB.FillFormula();
		//DB.Display();//DB確認用
		//計算
		Calc.Main();
		}
	}
}
