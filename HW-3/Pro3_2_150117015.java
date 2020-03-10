//YASIN ENES POLAT 150117015

/*This program prints a letter. Size and type of letter determine by user. User can enter every number and letter but program 
prints only W, X, Y, Z letters with odd and bigger than 4 number of size. In each letter case, I divided the letter several
parts and then I wrote the loops for each part. */

import java.util.Scanner;
public class Pro3_2_150117015 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to letter printer program.");
		char conttion = 'Y'; //Here is the continuation value that taken from user by the end of printing letter.
		while (conttion == 'y' || conttion == 'Y') { //The continuation situation is 'Y' by default.
		System.out.print("Enter the size   : "); 
		int lettersize = input.nextInt(); //Takes the size of letter from user.
		System.out.print("Enter the letter (W, X, Y or Z) : ");
		char letter = input.next().charAt(0); //Takes the letter from user.
		letter = Character.toUpperCase(letter); //Converts the letter to uppercase if it is lower case.
		
		while (lettersize < 5 || (lettersize + 1) % 2 != 0 || lettersize > 199) { //In thýs loop, program checks the size if it is odd number or not. If not, takes the size from user again.
			System.out.print("Invalid size. Enter the size : ");
			lettersize = input.nextInt();			
		}
		while (!(letter == 'X' || letter == 'Y' || letter == 'Z' || letter == 'W')) { //This loop checks if the user enter the wrong letter or not. If user enters wrong letter program takes the letter again from user.
			System.out.print("Invalid letter: Enter the letter : ");
			letter = input.next().charAt(0);
		}
		if (letter == 'Z') { //If user enter the letter 'Z', this if statement will be executed.
			int i;
			for (i = 1; i <= lettersize; i++) { //This loop prints the upper part of 'Z'. For instance, prints '*****' if user enter '5' for lettersize. 
				System.out.print("*");				
			}
			System.out.println(); //After the first part ends, program goes to the next line.
			int ls = lettersize - 2; //The middle part loop iterates (lettersize - 2) time because we have up and bottom part separately.
			for (i = 1; i <= lettersize - 2; i++) { 
				for (int k = ls; k >= 1; k--) { //This loop prints blank space before every middle part '*'.
					System.out.print(" ");
		}
				System.out.print("*");
				System.out.println(); //After each '*', program goes to next line on middle part.
				ls--;
		}
			for (i = 1; i <= lettersize; i++) { //This loop same as first part loop of letter 'Z'. Prints last '*****' part.
				System.out.print("*");				
			}
			System.out.println(); //Goes next line because program asks if the user wants to restart the program.
		}
		
		else if (letter == 'X') { //If user enter the letter 'X', this if statement will be executed.
			//I divide the 'X' letter to 3 parts. The parts includes 'V', a single '*' and upside-down 'V'
			int ls = lettersize - 2;
			for (int ls2 = 0; ls2 < (lettersize - 1) / 2; ls2++) { //This part prints 'V' part of 'X', except '*' at the bottom of 'V'.
				
				for (int m = 0; m < ls2; m++) { //This loop prints the blank spaces before each left-hand '*' except first one.
					System.out.print(" ");
				}
					
				
				System.out.print("*"); //This statement prints left-hand '*'.
				
				for (int k = ls;k >= 1 ;k--) { //This loop prints the blank spaces between lef-hand and right-hand '*'.
					System.out.print(" ");
				}
				System.out.print("*"); //This statement prints right-hand '*'.
				System.out.println();
				ls -= 2; //ls decreases by 2 because the blank spaces between left-hand and right-hand side '*' decreases by 2 for each line.
			}
			for(int ls2 = 0; ls2 < (lettersize - 1) / 2; ls2++) //This loop prints blank spaces before the '*' at the middle of 'X'.
				System.out.print(" ");
			System.out.print("*");
			System.out.println();
			int ls4 = lettersize - 2;
			for (int ls2 = (lettersize - 1) / 2; ls2 > 0; ls2--) { //This part prints upside-down 'V' part of 'X', except '*' at the top of upside-down 'V'.
				//Actually this part is exact-opposite to first part of 'X'.
				for (int m = ls2; m > 1; m--) { //Starting from the highest blank space number and decreases.
					System.out.print(" ");
				}
					
				
				System.out.print("*");
				
				for (int k = lettersize - 2;k >= ls4 ;k--) { //Starting from the lowest blank space number and increases by 2. This loop prints blank space between left and right-hand side '*'.
					System.out.print(" ");
				}
				System.out.print("*");
				System.out.println();
				ls4 -= 2;
			}
			
		}
		else if (letter == 'Y') { //If user enter the letter 'Y', this if statement will be executed.
			//I divide 'Y' to two parts. First part is 'V' and it is same as 'X's first part 'V'. Second part is the straight vertical '*' array.
			int ls = lettersize - 2;
			for (int ls2 = 0; ls2 < (lettersize - 1) / 2; ls2++) { //This loop is same as 'V' part of the letter 'X'. (93-108)
				
				for (int m = 0; m < ls2; m++) {
					System.out.print(" ");
				}
					
				
				System.out.print("*");
				
				for (int k = ls;k >= 1 ;k--) {
					System.out.print(" ");
				}
				System.out.print("*");
				System.out.println();
				ls -= 2;
			}
			for(int ls5 = 0; ls5 < (lettersize + 1) / 2; ls5++) { //This loop prints vertical straight '*' array.
				for(int ls2 = 0; ls2 < (lettersize - 1) / 2; ls2++) //This loop prints the same number of blank spaces for each line, blank space number changes by lettersize.
					System.out.print(" ");
			System.out.print("*"); //After each blank space line, program prints '*'.
			System.out.println();
			}
		}
		else if (letter == 'W') { //If user enter the letter 'W', this if statement will be executed.
			int ls = (lettersize - 2) * 2 + 1;
			int lsW = 1;
			int ls2;
			for (ls2 = 0; ls2 < (lettersize - 1); ls2++) {
				
				for (int m = 0; m < ls2; m++) {		//			*
					System.out.print(" ");			//			***
				}									//*************** In these lines, program prints left-hand '*' for first 'V' part. But this 'V' part is bigger than 'X's 'V' part.
													//			***
				System.out.print("*");				//			*
				
				for (int k = ls;k >= 1 ;k--) {		//	|																 					|
					System.out.print(" ");			//	|  In these lines (128,129,130,131), program prints blank spaces between left-hand 	|
				}									//	|  and right-hand '*' for first 'V' part and prints '*' after each lines. 			|
				System.out.print("*");				//	|																					|
				
				if (ls2 != 0){ //This if statement prints blank space between first and second 'V' parts, starts from second line.						
				for (int k = 2;k < lsW ;k++) {
					System.out.print(" ");
				}
				System.out.print("*"); //Prints the second 'V's left-hand '*'s.
				}
				for (int k = ls;k >= 1 ;k--) { //This loop prints blank space between left-hand and right-hand '*' of second 'V' part.
					System.out.print(" ");
				}
				System.out.print("*"); //This prints '*' each line ending.
				System.out.println();
				
				ls -= 2;
				lsW += 2;
				
			}
				for (int m = 0; m < ls2; m++) {		//				*
					System.out.print(" ");			//				**
				}									//				***	
				System.out.print("*");				//****************** In lines 150-157, program prints very last line of the letter 'W'. First prints blank space
				for (int k = 2;k < lsW ;k++) {		//****************** then prints first '*', after that prints blank spaces again and prints '*' again.
					System.out.print(" ");			//				***
				}									//				**
				System.out.print("*");				//				*
				System.out.println();				
		}
	System.out.print("Would you want to continue? (Y/N): "); //Here is the program asks user if user wants to redo the things again or not.
	conttion = input.next().charAt(0);
	while (!(conttion == 'y' || conttion == 'Y' || conttion == 'n' || conttion == 'N')) { //If user enters chars different from 'Y' or 'N' this loop will execute till user enters 'Y' or 'N'.
		System.out.println("Invalid answer!");
		System.out.print("Would you want to continue? (Y/N): ");
		conttion = input.next().charAt(0);
		
	}
	
	
	}
		
		if (conttion == 'n' || conttion == 'N') { //If user enters 'N', program will close itself.
			System.out.println("Program shutted down...");
			System.exit(0);
		}
		
	}

}
