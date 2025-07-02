package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//ul[@class='nav-pills categorymenu']/li[a[contains(@href,'product/category')]]")
	List<WebElement> list_categoryMenu;

	@FindBy(xpath = "//div[@class='thumbnails grid row list-inline']/div")
	List<WebElement> container_items;

	public List<String> getElementText() {
		List<String> elementText = new ArrayList<String>();
		for (WebElement webElement : list_categoryMenu) {
			elementText.add(webElement.getText());
		}
		return elementText;
	}

	public void clickRandomItem() {
		Random random = new Random();
		Actions myAction = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		List<WebElement> reFetchMenu = driver.findElements( // try updated path
				By.xpath("//ul[@class='nav-pills categorymenu']/li[a[contains(@href,'product/category')]]"));
		WebElement hoverElement = reFetchMenu.get(random.nextInt(list_categoryMenu.size()));
		myWait.until(ExpectedConditions.visibilityOf(hoverElement));
		myAction.moveToElement(hoverElement).pause(Duration.ofSeconds(2)).perform();

		WebElement subCatContainer = hoverElement.findElement(By.xpath(".//div[contains(@class,'subcategories')]"));
		myWait.until(ExpectedConditions.visibilityOf(subCatContainer));

		List<WebElement> link_product = subCatContainer.findElements(By.xpath(".//ul/li/a"));

		if (link_product.isEmpty()) {
			throw new RuntimeException("No subcategories found under: " + hoverElement.getText());
		}

		WebElement randomSubCategory = link_product.get(random.nextInt(link_product.size()));
		retrying(randomSubCategory, 3);

	}

	public int countProducts() {

		return container_items.size();
	}
}
