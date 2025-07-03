package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportUtils {

	private static final String FILE_PATH = "report.txt";

	public static void writeToReport(String data) {
		try (FileWriter fileWrite = new FileWriter(FILE_PATH, true);
				BufferedWriter buffWrite = new BufferedWriter(fileWrite);
				PrintWriter out = new PrintWriter(buffWrite)) {

			out.println(data);

		} catch (IOException e) {
			System.err.println("Unable to write to report.txt: " + e.getMessage());
		}
	}

}
