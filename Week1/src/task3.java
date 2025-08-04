public class task3 {

	public static void main(String[] args) {
		int throwsNeeded = 1;
		while (true)
		{
			// Get random value between 1-6
			int dice1 = ((int) (Math.random() * 6) + 1);
			int dice2 = ((int) (Math.random() * 6) + 1);
			
			System.out.println("Dice thows: " +dice1 + " and " +dice2);

			
			if (dice1 == dice2) {
				System.out.println("Dice match!");
				System.out.println("Throws needed: " + throwsNeeded);
				break;
			}
			else {
				System.out.println("Dice not matched");
				throwsNeeded++;
			}
		}
		
	}

}
