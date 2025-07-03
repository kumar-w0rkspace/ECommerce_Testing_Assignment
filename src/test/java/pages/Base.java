package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	WebDriver driver;
	static WebDriverWait myWait;

	public Base(WebDriver driver) {
		this.driver = driver;
		myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void waitThenClickRandom(List<WebElement> elements, WebDriver driver) {
		
		if (elements == null || elements.isEmpty()) {
			throw new RuntimeException("No elements found to click.");
		}
		Random rand = new Random();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		int index = rand.nextInt(elements.size());
		WebElement elementToClick = elements.get(index);
		js.executeScript("arguments[0].scrollIntoView(true);", elementToClick);
		wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
	}

}
