//Yasin Enes POLAT | 150117015
//Program first takes string from user  which shows the content of a 2D array. This string should contain
//a set of numbers separated by dashes (-), and each number is used for a binary representation of a
//row. Then, generated binary digits are located to the corresponding row of 2D array. Then program changes
//values of rows and columns with respect to rules step times that also user enters. After each step updated
//version of grid will be print out. Finally program calculates the decimal value of grid after last last step
//and print that values as string.

import java.util.Scanner;
public class Pro5_2_150117015 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int numbersOut[] = new int[1];
		int steps = 0;
		
		boolean cs = false;
		while (!cs) {
			System.out.print("Enter a string: ");
			String numberString = input.nextLine();
			System.out.print("Enter a number of steps: ");
			steps = input.nextInt();
		int count = 0;
		for (int i = 0; i < numberString.length(); i++) { //This loop finds number o '-' in string.
			if (numberString.charAt(i) == '-')
				count++;
		}
		
		int[] numbers = new int[count + 1];
		int biggest = 0;
				
		for (int at = count, i = numberString.length() - 1, j = 1; i >= 0; i--) { //This loop assigns numbers at string separated by '-' to the array from end of the string to the first character. Also finds biggest number at string.
			
			if (numberString.charAt(i) == '-') {
				j = 1;
				at--;
			}
			else {
				numbers[at] += (numberString.charAt(i) - 48) * j;
				j *= 10;
				
			}
			if (numbers[at] > biggest)
				biggest = numbers[at];
		}
		
		if (biggest >= Math.pow(2, count + 1)) { //If the given string cannot be represented as square grid this statement works and breaks current iteration.
			System.out.println("The number " + biggest + " cannot be represented with" + (count + 1) + "x" + (count + 1) + " array!");
				cs = false;
		}
		else {
			cs = true;
			numbersOut = numbers;
		}
		String dust = input.nextLine(); //Program contains nextInt after nextLine so I must add this empty string cleaner.
		}
		
		int[][] binaryGrid1 = new int[numbersOut.length][numbersOut.length];
		
		
		for (int i = 0; i < binaryGrid1.length; i++) { //This loop assigns converted decimal value of first array to the 2D array.
			int[] binary = convertDectoB(numbersOut[i],numbersOut.length);
			for (int j = 0; j < binaryGrid1[i].length; j++) {
				binaryGrid1[i][j] = binary[j];
			}
		}
		for (int i = 0; i < binaryGrid1.length; i++) { //This loop prints representation of given string.
			for(int f = 0; f < (4 * binaryGrid1.length) + 1; f++)
				System.out.print("-");
			System.out.println();
			System.out.print("|");
			for (int j = 0; j < binaryGrid1[i].length; j++) {
				System.out.print(" " + binaryGrid1[i][j] + " |");
				}
			System.out.println();
			if (i == binaryGrid1.length - 1)
				for(int f = 0; f < (4 * binaryGrid1.length) + 1; f++)
					System.out.print("-");
		}
		
		for (int l = 0; l < steps; l++) { //This loop converts 2D array step times that user enters.
			binaryGrid1 = convertStepByStep(binaryGrid1);
			System.out.println();
			System.out.println("It is converted to the following table after " + (l+1) + ". step:");
			for (int i = 0; i < binaryGrid1.length; i++) {
				for(int f = 0; f < (4 * binaryGrid1.length) + 1; f++)
					System.out.print("-");
				System.out.println();
				System.out.print("|");
				for (int j = 0; j < binaryGrid1[i].length; j++) {
					System.out.print(" " + binaryGrid1[i][j] + " |");
					}
				System.out.println();
				if (i == binaryGrid1.length - 1)
					for(int f = 0; f < (4 * binaryGrid1.length) + 1; f++)
						System.out.print("-");
			}
		}
		
		convertBtoDec(binaryGrid1, steps);
		
		
		
	}
	public static int[] convertDectoB(int number, int size) {
		
		int[] binary = new int[size];
		int lastNum = number;
		
		for (int i = binary.length - 1;lastNum != 0;i--) {
			binary[i] = lastNum % 2;
			lastNum /= 2;
		}
		
		return binary;
	}
	public static int[][] convertStepByStep (int[][] array) {
		int[][] grid2 = new int[array.length][array.length];
		
		for (int i = 0; i < array.length; i++) { //These nested loops checks neighbors whether they are equal 1 or not. 
			for (int j = 0; j < array.length; j++) {
				int count = 0;
				if (array[i][j] == 0) {
					for (int k = i - 1; k <= i + 1; k++) {
						if (k < 0 || k > array.length - 1) {}
						else {
						for (int l = j - 1; l <= j + 1; l++) {
							if (l < 0 || l > array.length - 1) {}
							else {
							if (array[k][l] == 1) {
								count++;
							}
							}
						}
						}
					}
					if (count == 3)
						grid2[i][j]	= 1;
					else
						grid2[i][j]	= array[i][j];
				}
				else if (array[i][j] == 1) {
					for (int k = i - 1; k <= i + 1; k++) {
						if (k < 0 || k > array.length - 1) {}
						else {
						for (int l = j - 1; l <= j + 1; l++) {
							if (l < 0 || l > array.length - 1) {}
							else {
							if (array[k][l] == 1) {
								count++;
							}
							}
						}
						}
					}
					if (count == 4 || count == 3) //Çünkü kendisini ([k][l]) de sayýyor.
						grid2[i][j]	= 1;
					else 
						grid2[i][j]	= 0;
				}
			}
		}
		
		return grid2;
	}
	public static void convertBtoDec (int[][] binaryGrid, int steps) {
		
		int[] lastString = new int[binaryGrid.length];
		
		for (int i = 0; i < binaryGrid.length; i++) { //This loop converts binary numbers to decimal at array after last step and assigns them to another 1D array.
			for (int j = binaryGrid[i].length - 1, k = 1; j >= 0; j--) {
				lastString[i] += binaryGrid[i][j] * k;
				k *= 2;
			}
		}
		
		System.out.println();
		System.out.print("The decimal value for the " + steps + ". table after " + steps + " steps: ");
		
		for ( int i = 0; i < lastString.length; i++) { //This loop prints string after last step.
			System.out.print(lastString[i]);
			if (i != lastString.length - 1)
				System.out.print("-");
		}
	}

}
