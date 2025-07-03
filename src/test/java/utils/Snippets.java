package utils;

import java.util.Map;

import com.opencsv.exceptions.CsvValidationException;

public class Snippets {

	public static void main(String[] args) {
		/*
		 * System.out.println(System.getProperty("user.dir"));
		 * 
		 * Scanner sc = new Scanner(System.in); System.out.println("Enter the string");
		 * String text = sc.nextLine();
		 * 
		 * // 9885349 In Stock int startingIndex = text.indexOf(" "); int endingIndex =
		 * text.indexOf("In Stock"); String pagesOnly = text.substring(startingIndex +
		 * 1, endingIndex - 1); // Note -> + 1 and - 1 are for substring methods(to
		 * equate properly)
		 * 
		 * sc.close();
		 */
		String csvPath = "testdata.csv"; 

		try {
			Map<String, String> userData = CSVReaderUtility.readSingleUser(csvPath);
			if (userData != null) {
				userData.forEach((key, value) -> System.out.println(key + " : " + value));
			} else {
				System.out.println("No data found or error reading CSV.");
			}
		} catch (CsvValidationException e) {
			System.err.println("CSV format issue: " + e.getMessage());
		}

	}
}
