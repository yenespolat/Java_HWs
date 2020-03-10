
//YASIN ENES POLAT 150117015 | Prgram finds the equation of the perpendecular bisector of the line segment between 2 points with the value of points that user enters.

import java.util.Scanner;

public class Pro1_150117015 {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
	//The program getting the coordinates of two points between 11th and 18th lines	
		System.out.print("Enter the value of x1: ");
			double x1 = input.nextDouble();
		System.out.print("Enter the value of y1: ");
			double y1 = input.nextDouble();
		System.out.print("Enter the value of x2: ");
			double x2 = input.nextDouble();
		System.out.print("Enter the value of y2: ");
			double y2 = input.nextDouble();
	
	//Computing the slope and midpoints of the line between two points in 20th, 21th and 22th lines.	
			double slope 	  = (y2 - y1) / (x2 - x1);
			double midX  	  = (x2 + x1) / 2;
			double midY  	  = (y2 + y1) / 2;
			
		if ((int)slope == 0) { //If the slope of the two points = 0,  (-1) * Math.pow(slope, -1) this equation will find the result of infinity. So I use if else statement to print the correct equation for the perpendicular bisector
			double pbslope = 0;
			double interceptY = midY - (pbslope * midX);
			//Printing the equation of the perpendicular bisector down below
			System.out.println("The equation of the perpendicular bisector of the line segment between (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + ") is x=" + (int)(interceptY * 100) / 100.0);
		}
		else {
			double pbslope	  = (-1) * Math.pow(slope, -1); //computing the slope of the perpendicular
			double interceptY = midY - (pbslope * midX); 	//compute the y intercept of the perpendicular bisector
			//Printing the equation of the perpendicular bisector down below
			System.out.println("The equation of the perpendicular bisector of the line segment between (" + x1 + "," + y1 + ") and (" + x2 + "," + y2 + ") is y=" + (int)(pbslope * 100) / 100.0 + "x + " + (int)(interceptY * 100) / 100.0);
		}
			
		 
		
	}

}
