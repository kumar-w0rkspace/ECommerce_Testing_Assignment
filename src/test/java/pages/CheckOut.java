package pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckOut extends Base {

	public CheckOut(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@class='container-fluid cart-info product-list']/table[@class='table table-striped table-bordered']//tr[2]//td[6]")
	WebElement price_firstItem;
	@FindBy(xpath = "//div[@class='container-fluid cart-info product-list']/table[@class='table table-striped table-bordered']//tr[3]//td[6]")
	WebElement price_secondItem;
	@FindBy(xpath = "//div[@class='container-fluid cart_total']//table[@class='table table-striped table-bordered']/tbody/tr[2]//td[2]/span")
	WebElement price_shippingRate;
	@FindBy(xpath = "//div[@class='container-fluid cart_total']//table[@class='table table-striped table-bordered']//tr[3]//td[2]/span")
	WebElement price_total;

	@FindBy(xpath = "//a[@id='cart_checkout1']")
	WebElement btn_checkOut;
	@FindBy(xpath = "//select[@id='estimate_country']")
	WebElement select_country;
	@FindBy(xpath = "//select[@id='estimate_country_zones']")
	WebElement select_zone;

	public boolean getTotalPrice() {
		double priceOne = Double.parseDouble(price_firstItem.getText().substring(1));
		double priceTwo = Double.parseDouble(price_secondItem.getText().substring(1));
		double priceShipping = Double.parseDouble(price_shippingRate.getText().substring(1));
		double priceTotal = Double.parseDouble(price_total.getText().substring(1));

		return priceTotal == (priceOne + priceTwo + priceShipping);
	}

	public void clickCheckOut() {
		/*Select myCountrySelect = new Select(select_country);
		Random random = new Random();
		myCountrySelect.selectByValue("99");
		Select myStateSelect = new Select(select_zone);
		int min = 1475;
		int max = 1506;
		int randomValue = random.nextInt(max - min + 1) + min;
		myStateSelect.selectByValue(String.valueOf(randomValue)); */
		btn_checkOut.click();
	}
}
