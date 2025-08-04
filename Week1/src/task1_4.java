import java.util.*;

public class task1_4 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int i;
		//datatype[] arrayname = new datatype[size];
		//int x[5]; -> C++
		//int[] x = new int [5]; -> java
		String [] colors= {"Red","Orange","Yellow","Green","Blue","Indigo","Violet"};
		do
		{
			System.out.println("Please enter form (1-7). And enter -1 to exit: ");
			i = input.nextInt();
			
			if (i >= 1 && i <= 7)
			{
				System.out.println("Color is: " + colors[--i]);				
			}
		}while(i != -1);

		
		
	}

}
