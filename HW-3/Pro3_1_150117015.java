//YASIN ENES POLAT 150117015

/*This program  takes a sequence of characters and draws the image they represent.
  The ‘b’s in the sequence are for printing a blank character, ‘s’s for printing ‘*’
  and ‘n’s for newline. If there is a digit before any of these characters, it shows how many times
  the character printed, if the digit is missing, the character printed only once.
*/

import java.util.Scanner;
public class Pro3_1_150117015 {

	public static void main(String[] args) {
		System.out.println("Welcome to image printer program.");
		Scanner input = new Scanner(System.in);
		System.out.print("Pleasae enter your sequence: "); //User enters a sequence whatever he or she want.
		String sequence = input.nextLine();
		int i ;
		for (i = 0; i <= sequence.length() - 1;) { //(First Loop) This loop checks the sequence from 1st to last character.
				int j = 1;
				if (i == sequence.length() - 1 && (sequence.charAt(i) >= '1' && sequence.charAt(i) <= '9')) //If i don't add this statement, program gives user an error
					break;																					//message after printing representation if sequence ends with a number.
				else {
			if (sequence.charAt(i) >= '1' && sequence.charAt(i) <= '9') { //If i th character is a number, that number assigns to a variable. 
				j = sequence.charAt(i) - 48;
				if (sequence.charAt(i+1) == 'b') { //Program prints j times blank space if character at i + 1 in sequence is 'b'
					for (int k = 1; k <= j; k++) {
						System.out.print(" ");
				}
					i += 2; //First Loop still at same iteration and should skip the (i+1)th iteration, so program increments i by 2. (Hala ayný iterasyonda olduðundan harften sonraki iterasyona geçmesi için i 2 arttýrýlýyor.)
				}
				else if (sequence.charAt(i+1) == 'n') { //Program goes next line j times if character at i + 1 in sequence is 'n'
					for (int k = 0; k <= j; k++) {
						System.out.println();
				}
					i += 2;
				}
				else if (sequence.charAt(i+1) == 's') { //Program prints j times '*' if character at i + 1 in sequence is 's'
					for (int k = 1; k <= j; k++) {
						System.out.print("*");
				}
					i += 2;
				}
				else { //If character at i+1 different from b, s or n, increments i by 1.
					i ++;
				}
			
			}
			else if (sequence.charAt(i) == 'b') { //Program prints blank space once if there isn't a number before character at i and character at i is b.
				System.out.print(" ");
				i++;
			}
			else if (sequence.charAt(i) == 'n') { //Program goes next line once if there isn't a number before character at i and character at i is n.
				System.out.println();
				i++;
			}
			else if (sequence.charAt(i) == 's') { //Program prints '*' once if there isn't a number before character at i and character at i is s.
				System.out.print("*");
				i++;
			}
			
			else //If character at i is different from b,n,s or number 0 to 9, program increments i by 1.
				i++;
				}
		
	}

}
}