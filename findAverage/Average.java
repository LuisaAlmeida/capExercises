package findAverage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Average {

	private List<Integer> integerList;

	public Average() {
		integerList = new ArrayList<>();
	}

	public List<Integer> getIntegerList() {
		return integerList;
	}

	public void setIntegerList(List<Integer> integerList) {
		this.integerList = integerList;
	}

	public void getInput(Scanner input) {
			System.out.println("->Please enter the first number<-");
			getIntegerList().add(input.nextInt());
			System.out.println("-->Please enter the second number<--");
			getIntegerList().add(input.nextInt());
			System.out.println("--->Please enter the third number<---");
			getIntegerList().add(input.nextInt());
			System.out.println("---->Please enter the fourth number<----");
			getIntegerList().add(input.nextInt());
			System.out.println("You added " + getIntegerList() + " to your number list");	
			input.close();
		} 

	public void getSumAverage() {
		int sum = 0;
		for(int i = 0; i < integerList.size(); i ++) {
		sum += i;
		}
		System.out.println(sum + " --->This is the sum of the first 3 numbers<---");
		double average = (double) sum/3;
		System.out.println(average + " -->This is their average<--");
	}

	public void getDivision() {
		int sum = 0;
		for(int i = 0; i < integerList.size(); i ++) {
			sum += i;
		} 
		double division = (double) sum/integerList.get(3);
		System.out.println(division + " ->This is the result of the previous sum divided by the last number you've chosen<-");
	}
}
