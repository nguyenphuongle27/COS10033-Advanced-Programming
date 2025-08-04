//#include <iostream>

import java.util.Scanner;


public class task1_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		int a;
		float b;
		String c;
		
		System.out.print("Enter an integer, a float and a String");
		
		a = input.nextInt();
		
		b = input.nextFloat();
		
		//input buffer still contains the enter key stroke
		input.nextLine();
		c = input.nextLine();
		
		System.out.println("Integer: "+a+" Float: "+b+ " String: "+c);

	}

}
