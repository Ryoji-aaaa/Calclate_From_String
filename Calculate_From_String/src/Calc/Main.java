package Calc;

import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Write Formula->");
		String str_formula = sc.nextLine();
		sc.close();
		//文字列から数字、演算子の分離、抽出。FillFormula()でDBに格納。
		DB.Sampling(str_formula);
		DB.FillFormula();
		//計算
		Calc.Main();
		
	}
}
