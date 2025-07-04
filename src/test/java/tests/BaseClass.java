package tests;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

// import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public Properties property;
	public static Logger log;

	@BeforeClass
	@Parameters({ "browser" })
	public void setup(String browser) throws IOException {

		FileReader file = new FileReader("./src//test//resources//config.properties");
		property = new Properties();
		property.load(file);

		log = LogManager.getLogger(this.getClass());

		switch (browser.toLowerCase()) {
		case "firefox":
			// WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			// WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser name");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(property.getProperty("appURL"));
		log.info("*** Starting Test ***");
	}

	@AfterClass
	public void teardown() {
		log.info("*** Ending Test ***");
		driver.close();
	}

}
