package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[@class='nav-pills categorymenu']/li[a[contains(@href,'product/category')]]")
	List<WebElement> menu_Category;

	@FindBy(xpath = "//ul[@class='thumbnails row']/li")
	List<WebElement> sub_Category;

	@FindBy(xpath = "//div[@class='thumbnails grid row list-inline']/div")
	List<WebElement> subCat_Items;

	@FindBy(xpath = "//div[@class='thumbnails grid row list-inline']//div[@class='fixed']/a")
	List<WebElement> link_ToCliCk;

	public List<String> getElementText() {
		List<String> elementText = new ArrayList<String>();
		for (WebElement webElement : menu_Category) {
			elementText.add(webElement.getText());
		}
		return elementText;
	}

	public void clickProdCategory() {

		waitThenClickRandom(menu_Category, driver);
	}

	public void clickSubCategory() {
		waitThenClickRandom(sub_Category, driver);
	}

	public void clickItem() {

		waitThenClickRandom(link_ToCliCk, driver);
	}

	public int countProducts() {

		return subCat_Items.size();
	}
}
