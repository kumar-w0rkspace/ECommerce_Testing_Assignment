package pages;

import java.time.Duration;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver;
	WebDriverWait myWait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void visibilityWait(WebElement element) {
		myWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickabilityWait(WebElement element) {
		myWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void retrying(WebElement element, int maxAttempts) {
		int attempts = 0;
		while (attempts < maxAttempts) {
			try {
				visibilityWait(element);
				element.click();
				return;
			} catch (StaleElementReferenceException e) {
				attempts++;
				if (attempts == maxAttempts) {
					throw new RuntimeException(
							"Failed to click element after " + maxAttempts + " retries due to staleness.");
				}
			}
		}
	}

}
