//Yasin Enes Polat 150117015 
/* Menus are "counting numbers of chars", "printing the words in sentence" "deleting substring" 
"replacing substring" "sorting characters" "printing hash code of a string" by user's inputs. 
If the word is either “exit” or “quit”, your program should terminate; otherwise, your program
will execute the related methods based on the user choice.
*/
import java.util.Scanner;
public class Pro4_1_150117015 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to our String Analyzer Program."); 
		
		int cs = 1; //For while loop continuity. 
		while (cs == 1){
		System.out.println("1. Count number of chars\n" + 
						"2. Print the words in a sentence\n" + 
						"3. Delete substring\n" + 
						"4. Replace substring\n" + 
						"5. Sort characters\n" + 
						"6. Hash code of a string");
		System.out.print("Please enter your menu choice: "); //These 2 statements takes the menu choice or terminate input from user.
		String choice = input.next();
		
		if (choice.equals("exit") || choice.equals("quit")) { //If user enters either "exit" or "quit" strings, program will terminate.
			System.out.print("Program ends. Bye :)");
			System.exit(0); 
		}
		
		int menuchoice = (choice.charAt(0)) - 48; //This statement calculates decimal value from ASCII value because we get the input as a string.
		
		
		
		switch (menuchoice) {
		case 1:
			//This case is counting a occurence of number in input string that user enters and depends on user's character choice.
			String dust = input.nextLine(); //This takes empty moving cursor to the next line part in each case.
			System.out.print("Enter an input string: ");
			String inputfor1 = input.nextLine();
			System.out.print("Enter an input char: ");
			char occurrenceofchar = input.next().charAt(0);			
			System.out.println("The number of " + occurrenceofchar + " in " + inputfor1 + " is " + numOfChars(inputfor1, occurrenceofchar)); //Calls numOfChars method and print the result.
			break;
			
		case 2:
			//This case prints the each word in input string. The cursor moves the next line after each word.
			dust = input.nextLine();
			System.out.print("Enter an input string: ");
			String inputfor2 = input.nextLine();
			System.out.println("The output is:");
			printWords(inputfor2); //calls printWords method. Method doesn't care some punctionational symbol.
			
			
			break;
			
		case 3:
			//This case deletes the substring that user enters in input string.
			dust = input.nextLine();
			System.out.print("Enter an input string: ");
			String inputfor3 = input.nextLine();
			System.out.print("Enter a substring: ");
			String substring = input.nextLine();
			System.out.print("Enter a type: ");
			int type = input.nextInt();
			while (type != 0 && type != 1) {
				System.out.println("Wrong type please enter either 0 or 1: ");
				type = input.nextInt();
			}
			System.out.println(delete(inputfor3, substring, type)); //This method deletes the substrings. If user enter 0 as type, method deletes the first occurrence of substring. If type is 1, method will delete the each occurrence of substring that user enters. 
			
			break;
			
		case 4:
			//Replace the substrings by user's string choices.
			dust = input.nextLine();
			System.out.print("Enter an input string: ");
			String inputfor4 = input.nextLine();
			System.out.print("Enter a first substring: "); //Takes the substring from user that will change.
			String substring1 = input.nextLine();
			System.out.print("Enter a second substring: "); //Takes the substring from user that written over instead of first substrings in main string.
			String substring2 = input.nextLine();
			System.out.println(replace(inputfor4, substring1, substring2));
			
			break;
			
		case 5:
			//This case sorts character by its ASCII values. If type is 0, program sort the string non-ruled, if it is 1 program sorts first lower case letters, upper case letter, numbers and then other symbols in order. 
			dust = input.nextLine();
			System.out.print("Enter an input string: ");
			String inputfor5 = input.nextLine();
			System.out.print("Enter a type: ");
			int typeFor5 = input.nextInt();
			while (typeFor5 != 0 && typeFor5 != 1) {
				System.out.println("Wrong type please enter either 0 or 1: ");
				typeFor5 = input.nextInt();
			}
			System.out.println(sortByType(inputfor5, typeFor5));
			
			break;
			
		case 6:
			//This case calculates hash code of user's input string by this formula: s(0)*b^(n-1) + s(1)*b^(n-2) + ... + s(n)-1 
			dust = input.nextLine();
			System.out.print("Enter an input string: ");
			String inputfor6 = input.nextLine();
			System.out.print("Enter a value for b: ");
			int b = input.nextInt();
			System.out.println(hashCode(inputfor6, b));
			
			break;
			
		default:
			System.out.println("!!!Please enter a number between 1-6!!!");
		}
		}
	}
	public static int numOfChars (String str, char ch) {
		int times = 0;
		
		for (int i = 0; i < str.length(); i++) { //If charAt(i) is equal to character that user enters, program increments the times variable by 1.
			if (str.charAt(i) == ch)
				times++;
		}
		
		return times;
	}
	public static void printWords (String str) {
		
		int j = 0;
		String forCheck = "";
		for (int i = str.indexOf(' ', j); i <= str.length(); ) { //Finds the each words in sentence by looking ' ' occurrence in string.
			if (i == str.length()) {
				forCheck = str.substring(j);
				checkWords(forCheck);
				//en sonuncu i deðerinden sonra hata verdiði için
				break;
			}
			forCheck = str.substring(j, i);
			checkWords(forCheck);
			j = i + 1;
			if (i != str.lastIndexOf(' '))
				i = str.indexOf(' ', i + 1);
			else //If i is equal to last occurrence of ' ', changes it values to length of string to end the loop.
				i = str.length();
				
		}
		
	}
	public static void checkWords (String str) { //Checks the words if it includes the unnecessary symbols or not. If it is, deletes that symbol from word.
		int i = str.length();
		char[] check = new char[i];
		int k = 0; 
		for (int j = 0; j < str.length(); ) {
			
			if (str.charAt(j) == ' ' || str.charAt(j) == ',' || str.charAt(j) == '.' || str.charAt(j) == '!' || str.charAt(j) == '?' || str.charAt(j) == '_' || str.charAt(j) == '-' || str.charAt(j) == '(' || str.charAt(j) == ')')
				j++;
			else {
				check[k] = str.charAt(j);
				k++;
				j++;
			}
		}
		
		String last = "";
		for (int j = 0; j < str.length(); j++) { //Adds each character in check array to last string.
			if (check[j] != ' ' && check[j] != '0')
				last += check[j];
		}
		System.out.println(last);
				
	}
	public static String delete (String str, String subStr, int type) {
		String[] strArray = new String[str.length()];
		for (int j = 0; j < str.length(); j++) { //Assigns each characters in string to one index of strArray array.
			strArray[j] = str.substring(j, j + 1);
		}
		String lastStr = "";
			
		if (type == 0) { //Deletes the first occurrence of substring in input string.
			int first = str.indexOf(subStr);
			int last = first + subStr.length();
			int firstTemp = first;
			for (int i = 0; i < last - firstTemp; first++, i++) {
				strArray[first] = "";
			}
		for (int i = 0; i < str.length(); i++) {
			lastStr += strArray[i];
		}
		}
		else if (type == 1) { //deletes each occurrence of substring in input string.
			
			int k = 0;
			int first = 0;
			int last;
			int firstTemp;
			
			while (first != -1) { //If there isn't an occurrence of substring in input string, first will be -1 and loop will end.
				
				first = str.indexOf(subStr, k);
				if (first != -1) {
				last = first + subStr.length();
				firstTemp = first;
				
				for (int i = 0; i < last - firstTemp; first++, i++) {
					strArray[first] = "";
				}
			k = last;
			}
			}
			for (int i = 0; i < str.length(); i++) {
				lastStr += strArray[i];
			}
			
		}
		
		
		return lastStr;
	}
	public static String replace(String str, String subStr1, String subStr2) {
		
		String[] str1 = new String[str.length()];
		
		String lastStr = "";
		
		for (int i = 0; i < str.length(); i++) { //Assigns characters in string to an array one by one.
			str1[i] = str.substring(i, i + 1);
		}
		
		int k;
		int y = 0;
		int tempK = -1;
		
		while (y != -1) { //This loop finds the occurrence of first substring in string, deletes it and writes second substring over it.
			
			if (subStr1.length() == 1) {
				k = str.indexOf(subStr1.charAt(0), tempK + 1); //tempK + 1'den itibaren baktýrmazsam neden olduðunu bilmediðim þekilde k'nýn deðeri sürekli ayný çýkýyor.
				tempK = k;
				for (int i = 0; i < 1; i++, k++) {
					str1[k] = "";
				}
			}
			
			else {
				k = str.indexOf(subStr1, tempK + 1);
				tempK = k;
			for (int i = 0; i < subStr1.length(); i++, k++) { //This loop deletes the substring from string.
				str1[k] = "";
			}
			
			}
			
			str1[tempK] = subStr2;	//This statement writes second substring over index of first substring. 
						
			if (k >= str.lastIndexOf(subStr1) +  subStr1.length())
				y = -1;
			
		}
		
		for (int i = 0; i < str.length(); i++) { //This loop converts the array to a string.
			lastStr += str1[i];
		}
		
		return lastStr;
	}
	public static String sortByType (String str, int type) {
		String lowerCase = "";
		String upperCase = "";
		String number 	 = "";
		String other 	 = "";
		
		if (type == 0) //Directly sorts the string, does not seperate lower case upper case etc.
			str = sortChars(str);
		else if (type == 1) { //This sorts the string's lower case letters, upper case letters, numbers, symbols separately.
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
					lowerCase += str.charAt(i);
				else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
					upperCase += str.charAt(i);
				else if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
					number += str.charAt(i);
				else
					other += str.charAt(i);
			}
			lowerCase = sortChars(lowerCase);
			upperCase = sortChars(upperCase);
			number 	  = sortChars(number);
			other	  = sortChars(other);
			str 	  = lowerCase + upperCase + number + other;
		}
		
		return str;
	}
	
	public static String sortChars (String str) {
		char[] strArray5 = new char[str.length()];
		
		String lastStr = "";
		
		for (int i = 0; i < str.length(); i++) { //Assigns characters from string to array one by one.
			strArray5[i] = str.charAt(i);
		}
		
		for (int i = 0; i < str.length() - 1; i++) { //Respectively looks for minimum character in array, takes it and puts at current index in loop.
			char tempI = strArray5[i];
			int minJ = i;
			
			for (int j = i; j < str.length(); j++) {
				if (strArray5[j] < strArray5[i] && strArray5[j] < strArray5[minJ]) {
					minJ = j;
				}
			}
		
			strArray5[i] = strArray5[minJ];
			strArray5[minJ] = tempI;
			
		}
		for (int i = 0; i < str.length(); i++) {
			lastStr += strArray5[i];
		}
		return lastStr;
	}
	public static int hashCode (String str, int b) {
		
		int hash = 0;
		for (int i = 0, j = (str.length() - 1); i < str.length() ;i++, j--) { //This loop calculates the hashcode of input string.
			hash += str.charAt(i) * (int)(Math.pow(b, j));
		}
		return hash;
	}


}
