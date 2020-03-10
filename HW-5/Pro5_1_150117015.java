//Yasin Enes Polat 150117015 
//This program takes a number from user. Then rolls 10 fair six-sided dice, number times that user enter.
//Sums each ten time rolls numbers then add a '*' character to the corresponding array index. For example, 
//if the number is 1 for all ten dices, program adds '*' to the 0th (total - 10) index of occPossibs index.
//finally, program prints all stars to the screen.

import java.util.Scanner;
public class Pro5_1_150117015 {

	public static void main(String[] args) {

		System.out.println("Welcome to dice simulator program.");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a value: ");
		int number = input.nextInt();
		
		String[] occPossibs = new String[51];
		
		for (int f = 0; f < occPossibs.length; f++) //If I don't add this loop into program, program prints "null" word if there is no occurrence.
			occPossibs[f] = "";
		
		for (int i = 0; i < number; i++) { //This loop rolls 10 dices number times.
			int totalDice = 0;
			for (int j = 1; j <= 10; j++) { //This loop rolls 10 dices for each number.
				totalDice += (int)(Math.random() * 6) + 1;
			}
			occPossibs[totalDice - 10] += "*";
		}
		
		for (int f = 0; f < occPossibs.length; f++) //This loop prints graphical representation of a statistical distribution that shows how many elements fall into a set of values.
			System.out.println((f + 10) + ": " + occPossibs[f]);
	}

}
