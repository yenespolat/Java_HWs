
//YASIN ENES POLAT 150117015 | Program calculates the profit by dedicating a certain amount of your salary that you enter (for example, 10%) each month with the monthly interest rate x% and y% government support.

import java.util.Scanner;

public class Pro2_150117015 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		//We are getting a few informations from user like monthly salary, the percent of the salary that user want to save, monthly interest rate and the monthly government support between 12th and 26th lines.
		System.out.print("Enter your monthly salary, for example 4250: ");
		
			double salary = input.nextDouble();
		
		System.out.print("Enter the percent of your salary to save, for example 12%: ");
			
			double portion = input.nextDouble();
			
		System.out.print("Enter the monthly interest rate, for example 12.65%: ");
		
			double interest = input.nextDouble();
			
		System.out.print("Enter the monthly government support rate, for example 10%: ");
		
			double support = input.nextDouble();
		
		//On next 7 line, program calculates the savings month by month. Also calculates total profit.
			double firstm  = salary * portion / 100 * (1 + support / 100) * (1 + interest / 100);
			double secondm = (firstm  + salary * portion / 100 * (1 + support / 100)) * (1 + interest / 100);
			double thirdm  = (secondm + salary * portion / 100 * (1 + support / 100)) * (1 + interest / 100);
			double fourthm = (thirdm  + salary * portion / 100 * (1 + support / 100)) * (1 + interest / 100);
			double fifthm  = (fourthm + salary * portion / 100 * (1 + support / 100)) * (1 + interest / 100);
			double sixthm  = (fifthm  + salary * portion / 100 * (1 + support / 100)) * (1 + interest / 100);
			double profit  = sixthm - (salary * portion / 100 * 6);
		//On next 6 line, program prints the monthly savings from 1st to 6th month.	
		System.out.println("After the first month, the account value  is " + (int)(firstm  * 100) / 100.0 );
		System.out.println("After the second month, the account value is " + (int)(secondm * 100) / 100.0 );
		System.out.println("After the third month, the account value  is " + (int)(thirdm  * 100) / 100.0 );
		System.out.println("After the fourth month, the account value is " + (int)(fourthm * 100) / 100.0 );
		System.out.println("After the fifth month, the account value  is " + (int)(fifthm  * 100) / 100.0 );
		System.out.println("After the sixth month, the account value  is " + (int)(sixthm  * 100) / 100.0 );
		//The last thing program does is calculating total savings and prints out total savings and profit.
		System.out.println("In total, you saved " + salary * 6 + " TLs and your total profit is " + (int)(profit * 100) / 100.0 + " TLs");

	}

}
