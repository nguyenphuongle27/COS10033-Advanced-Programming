
import java.util.Scanner;


public class task2 {

	public static void main(String[] args) {

		// Initialize variables
		Scanner input = new Scanner(System.in);
		
		int posi_Sum = 0, nega_Sum = 0, currentInput;
		
		System.out.print("Enter 10 inteher values:");

		// Loop to iterate for 10 integer inputs
		for (int i = 0; i < 10; i++) {		
			currentInput = input.nextInt();
			
			if (currentInput > 0) {
				posi_Sum += currentInput;
			}
			else {
				nega_Sum += currentInput;
			}
		}
		
		// Print out sum of positive and negative
		System.out.println("Sum of positive: " + posi_Sum);
		System.out.println("Sum of negative: " + nega_Sum);


	}

}
