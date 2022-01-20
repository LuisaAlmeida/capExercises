package findAverage;

import java.util.Scanner;



public class Main {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Average list = new Average();
		
		System.out.println("---->Please enter 4 Integer numbers<----");
		list.getInput(input);
		
		list.getSumAverage();
		
		list.getDivision();
	}
}
