package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

public class CSVReaderUtility {
	public static Map<String, String> readSingleUser(String filePath) throws CsvValidationException {
		try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(filePath))) {
			return reader.readMap();
		} catch (IOException e) {
			System.err.println("CSV read failed: " + e.getMessage());
			return null;
		}
	}
}
