package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[@class='bgnone']")
	WebElement string_name;
	@FindBy(xpath = "//div[@class='productfilneprice']")
	WebElement string_price;
	@FindBy(xpath = "//ul[@class='productinfo']/li[span[text()='Availability:']]")
	WebElement string_availability;
	@FindBy(xpath = "//a[@class='cart']")
	WebElement btn_cart;

	public String getName() {

		return myWait.until(ExpectedConditions.visibilityOf(string_name)).getText();
	}

	public String getPrice() {

		return myWait.until(ExpectedConditions.visibilityOf(string_price)).getText();
	}

	public String getQuantity() {
		String availabilityText = string_availability.getText().trim().toLowerCase();

		if (availabilityText.isEmpty()) {
			return "Availability info not listed";
		}
		if (availabilityText.contains("out of stock")) {
			return "Out of Stock";
		}
		if (availabilityText.contains("in stock")) {
			if (availabilityText.replaceAll("[^0-9]", "").isEmpty()) {
				return "Available but stock count not mentioned";
			} else {
				return availabilityText.replaceAll("[^0-9]", "");
			}
		}
		return "Unknown stock status: " + availabilityText;
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public void clickCartButton() {
		btn_cart.click();
	}

	public boolean cartButtonVisiblity() {
		try {
			return btn_cart.isDisplayed() && btn_cart.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
}
