package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenShotUtil {

	public static void captureImage(WebDriver driver, String testName) {

		String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String path = "./screenshots/" + testName + "_" + time + ".png";

		TakesScreenshot scr = (TakesScreenshot) driver;
		File src = scr.getScreenshotAs(OutputType.FILE);
		File target = new File(path);

		// src.renameTo(target);
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
