package utils;

import java.util.Scanner;

public class Snippets {

	public static void main(String[] args) {

		System.out.println(System.getProperty("user.dir"));
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string to find the page numbers");
		String text = sc.nextLine();
		
		// 9885349 In Stock
		int startingIndex = text.indexOf(" ");
		int endingIndex = text.indexOf("In Stock");
		String pagesOnly = text.substring(startingIndex + 1, endingIndex - 1);
		// Note -> + 1 and - 1 are for substring methods(to equate properly)
		
		sc.close();

	}

}
