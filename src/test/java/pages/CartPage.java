package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[contains(@class, 'product-list')]//table[contains(@class, 'table-striped')]//tr")
	List<WebElement> cart_Row;

	public int getCartItemCount() {

		return cart_Row.size();
	}
}
