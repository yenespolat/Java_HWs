//Yasin Enes Polat 150117015
//Program simply takes car type, additional package informations from user, adds taxes on total price and prints out the base price, total price, tax and additional cost.

import java.util.Scanner;

public class HW2_150117015 {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Marmara Motor Cars! \n------------------------------");
		System.out.println("Compact (C)\nLuxury  (L)\nMidsize (M)\nPickup  (P)\nSUV     (S)");
		//user selects car type.
		Scanner in = new Scanner(System.in);
		System.out.print("What model of car do you want? (C, L, M, P, S) : ");
		String type = in.next();
		//program asks user if user wants to add protective u. or not.
		System.out.print("Do you want protective undercoating for (C,L,M)$125.0, (P)$150.0, (S)$200.0? (Y/N) : ");
		String prounder = in.next();
			double addcost = 0;
			
			if ((int)prounder.toUpperCase().charAt(0) == 89)
				addcost += 125.0;
			else if ((int)prounder.toUpperCase().charAt(0) == 78)
				addcost += 0;
			else
				System.out.println("Invalid Answer");
		
		switch (type.toUpperCase().charAt(0)) {
			case 67:
				//program gets information from user about which additional packages that user wants to add for each case statements.
				//then as user gives answer yes program adds the package price to addcost variable.
				System.out.print("Do you want the security package for $800.0 (Y/N)? : ");
				String secpack = in.next();
				
					if ((int)secpack.toUpperCase().charAt(0) == 89) 
						addcost += 800.0;	
					else if ((int)secpack.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
				System.out.println("You ordered a Compact car with a base price of $16000.0");
				System.out.println("Your additional cost is " + addcost);
				double totalbt = 16000 + addcost;
				double otv = totalbt * 35 / 100;
				double kdv = (otv + totalbt) * 18 / 100;
				double totalat = totalbt + otv + kdv;
				System.out.println("Sales Tax : " + (int)((otv + kdv) * 100) / 100.0);
				System.out.println("Total cost of car & options : " + (int)(totalat * 100) / 100.0);
					break;
			case 76:
				System.out.print("Do you want the multimedia package for $1250.0 (Y/N)? : ");
				String multimedia = in.next();
				
					if (multimedia.toUpperCase().charAt(0) == 89) 
						addcost += 1250;
					else if (multimedia.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					//as the last calculations for each case program calculates taxes and adds on total prices before printing out.
					if (multimedia.toUpperCase().charAt(0) == 89 && prounder.toUpperCase().charAt(0) == 89) {
						System.out.println("You ordered a Luxury car with a base price of $39000.0");
						double addcost1 = (addcost - (addcost *5 /100)) * 100 / 100.0;
						System.out.println("Your additional cost is " + addcost1);
						double totalbt1 = 39000 + addcost1;
						double otv1 = totalbt1 * 40 / 100;
						double kdv1 = (otv1 + totalbt1) * 18 / 100;
						double totalat1 = totalbt1 + otv1 + kdv1;
						System.out.println("Sales Tax : " + (int)((otv1 + kdv1) * 100) / 100.0);
						System.out.println("Total cost of car & options : " + (int)(totalat1 * 100) / 100.0);
					}
						
					else {
						System.out.println("You ordered a Luxury car with a base price of $39000.0");
						System.out.println("Your additional cost is " + addcost);
						double totalbt2 = 39000 + addcost;
						double otv2 = totalbt2 * 40 / 100;
						double kdv2 = (otv2 + totalbt2) * 18 / 100;
						double totalat2 = totalbt2 + otv2 + kdv2;
						System.out.println("Sales Tax : " + (int)((otv2 + kdv2) * 100) / 100.0);
						System.out.println("Total cost of car & options : " + (int)(totalat2 * 100) / 100.0);
					}
						
				break;
			case 77:
				System.out.print("Do you want the automatic transmission for $2000.0 (Y/N)? : ");
				String atransm = in.next();
				System.out.print("Do you want the sunroof for $1500.0 (Y/N)? : ");
				String sunroof = in.next();
				System.out.print("Do you want the multimedia package for $750.0 (Y/N)? : ");
				String multimedia1 = in.next();
				System.out.print("Do you want the security package for $1000.0 (Y/N)? : ");
				String security = in.next();
				System.out.print("Do you want the sports package for $1300.0 (Y/N)? : ");
				String sports = in.next();
				
					if (atransm.toUpperCase().charAt(0) == 89)
						addcost += 2000;
					else if (atransm.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (sports.toUpperCase().charAt(0) == 89)
						addcost += 1300;
					else if (sports.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (sunroof.toUpperCase().charAt(0) == 89)
						addcost += 1500;
					else if (sunroof.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (multimedia1.toUpperCase().charAt(0) == 89)
						addcost += 750;
					else if (multimedia1.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					//In some case total prices decreases, i did it by adding || or && operators into if else statements. Like down below.
					if (multimedia1.toUpperCase().charAt(0) == 89 && sunroof.toUpperCase().charAt(0) == 89)
						addcost -= 250;
					else
						addcost -= 0;
					
					if (security.toUpperCase().charAt(0) == 89)
						addcost += 1000;
					else if (security.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (atransm.toUpperCase().charAt(0) == 89 && security.toUpperCase().charAt(0) == 89)
						addcost -= 300;
					else
						addcost += 0;
					
					System.out.println("You ordered a Midsize car with a base price of $26000.0");
					System.out.println("Your additional cost is " + addcost);
					double totalbt2 = 26000 + addcost;
					double otv2 = totalbt2 * 35 / 100;
					double kdv2 = (otv2 + totalbt2) * 18 / 100;
					double totalat2 = totalbt2 + otv2 + kdv2;
					System.out.println("Sales Tax : " + (int)((otv2 + kdv2) * 100) / 100.0);
					System.out.println("Total cost of car & options : " + (int)(totalat2 * 100) / 100.0);
				break;
			case 80:
				System.out.print("Do you want the multimedia package for $750.0 (Y/N)? : ");
				String multimedia2 = in.next();	
				
				if (multimedia2.toUpperCase().charAt(0) == 89)
					addcost += 750;
				else if (multimedia2.toUpperCase().charAt(0) == 78)
					addcost += 0;
				
				if ((int)prounder.toUpperCase().charAt(0) == 89)
					addcost += 25;
				else
					addcost += 0;
				
				System.out.println("You ordered a Pickup car with a base price of $19500.0");
				System.out.println("Your additional cost is " + addcost);
				double totalbt3 = 19500 + addcost;
				double otv3 = totalbt3 * 35 / 100;
				double kdv3 = (otv3 + totalbt3) * 18 / 100;
				double totalat3 = totalbt3 + otv3 + kdv3;
				System.out.println("Sales Tax : " + (int)((otv3 + kdv3) * 100) / 100.0);
				System.out.println("Total cost of car & options : " + (int)(totalat3 * 100) / 100.0);
			
				break;
			case 83:
				System.out.print("Do you want the automatic transmission for $2500.0 (Y/N)? : ");
				String atransm2 = in.next();
				System.out.print("Do you want the sunroof for $2300.0 (Y/N)? : ");
				String sunroof2 = in.next();
				System.out.print("Do you want the multimedia package for $1000.0 (Y/N)? : ");
				String multimedia3 = in.next();
				System.out.print("Do you want the security package for $1350.0 (Y/N)? : ");
				String security2 = in.next();
				System.out.print("Do you want the sports package for $1450.0 (Y/N)? : ");
				String sports2 = in.next();
				
					if (atransm2.toUpperCase().charAt(0) == 89)
						addcost += 2500;
					else if (atransm2.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");

					if (sunroof2.toUpperCase().charAt(0) == 89)
						addcost += 2300;
					else if (sunroof2.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (multimedia3.toUpperCase().charAt(0) == 89)
						addcost += 1000;
					else if (multimedia3.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (security2.toUpperCase().charAt(0) == 89)
						addcost += 1350;
					else if (security2.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if (sports2.toUpperCase().charAt(0) == 89)
						addcost += 1450;
					else if (sports2.toUpperCase().charAt(0) == 78)
						addcost += 0;
					else
						System.out.println("Invalid Answer");
					
					if ((int)prounder.toUpperCase().charAt(0) == 89)
						addcost += 75;
					else
						addcost += 0;
					
					if (atransm2.toUpperCase().charAt(0) == 89 && sunroof2.toUpperCase().charAt(0) == 89 && security2.toUpperCase().charAt(0) == 89 && prounder.toUpperCase().charAt(0) == 89) {
						System.out.println("You won protective undercoating for free!");
						addcost -= 200; }
					else if (atransm2.toUpperCase().charAt(0) == 89 && sunroof2.toUpperCase().charAt(0) == 89 && security2.toUpperCase().charAt(0) == 89 && prounder.toUpperCase().charAt(0) == 78) {
						System.out.println("You won protective undercoating for free!");
						addcost -= 0; }
					else 
						addcost += 0;
					
					System.out.println("You ordered a SUV car with a base price of $29000.0");
					System.out.println("Your additional cost is " + addcost);
					double totalbt4 = 29000 + addcost;
					
						if (totalbt4 < 35000) {
							double otv4 = totalbt4 * 35 / 100;
							double kdv4 = (otv4 + totalbt4) * 18 / 100;
							double totalat4 = totalbt4 + otv4 + kdv4;
							System.out.println("Sales Tax : " + (int)((otv4 + kdv4) * 100) / 100.0);
							System.out.println("Total cost of car & options : " + (int)(totalat4 * 100) / 100.0); }
						else {
							double otv4 = totalbt4 * 40 / 100;
							double kdv4 = (otv4 + totalbt4) * 18 / 100;
							double totalat4 = totalbt4 + otv4 + kdv4;
							System.out.println("Sales Tax : " + (int)((otv4 + kdv4) * 100) / 100.0);
							System.out.println("Total cost of car & options : " + (int)(totalat4 * 100) / 100.0); }
							
				break;
			default:
				System.out.print("Invalid Car Type!");
				break;
				
			}
		
	
		
		
	}

}
