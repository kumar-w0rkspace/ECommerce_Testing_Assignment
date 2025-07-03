package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.ReportUtils;

public class FullFlow extends BaseClass {
	HomePage homePage;
	ProductPage productPage;
	CartPage cartpage;
	SoftAssert myAssert;

	@BeforeMethod
	public void initSoftAssert() {
		myAssert = new SoftAssert();
	}

	@Test(priority = 1)
	public void print_mainCategory_names() {
		log.info("*** Printing Main menu items ***");
		homePage = new HomePage(driver);
		List<String> text = homePage.getElementText();
		log.info("*** Categories found: " + String.join(", ", text) + " ***");
	}

	@Test(priority = 2)
	public void randomCategoryNavigation() {
		homePage = new HomePage(driver);
		homePage.clickProdCategory();
		homePage.clickSubCategory();
	}

	public void verifyProductVisibility() {
		homePage = new HomePage(driver);
		int productCount = homePage.countProducts();
		if (productCount >= 3) {
			log.info("*** Category has " + productCount + " products ***");
		} else {
			log.error("Category has less than 3 products. Found: " + productCount);
			myAssert.assertEquals(productCount, 3, "Category must have at least 3 products. Found: " + productCount);
			myAssert.assertAll();
		}

	}

	@Test(priority = 4)
	public void randomItemToCart() {
		homePage = new HomePage(driver);
		productPage = new ProductPage(driver);
		log.info("*** Adding two items to cart ***");
		int addedCount = 0;
		int maxAttempts = 5;
		int attempts = 0;

		while (addedCount < 2 && attempts < maxAttempts) {
			try {
				homePage.clickItem();

				String name = productPage.getName();
				String price = productPage.getPrice();
				String stock = productPage.getQuantity();
				String url = productPage.getUrl();
				boolean cartVisible = productPage.cartButtonVisiblity();

				log.info("*** Product Name : " + name + " ***");
				log.info("*** Product Price : " + price + " ***");
				log.info("*** Available Stock : " + stock + " ***");
				log.info("*** Product URL : " + url + " ***");

				if (stock.equalsIgnoreCase("Out of Stock") || stock.equalsIgnoreCase("Availability info not listed")) {

					log.warn("Product not added to cart - Reason: " + stock);

				} else if (!cartVisible) {

					log.warn("Product not added - Reason: Add to Cart button not visible or not enabled");
					ReportUtils.writeToReport("SKIPPED - Cart Button Not Visible\n" + "Name  : " + name + "\n"
							+ "Price : " + price + "\n" + "Stock : " + stock + "\n" + "URL   : " + url + "\n\n");

				} else {
					productPage.clickCartButton();
					addedCount++;

					log.info("*** " + name + " Added to cart successfully ***");
					ReportUtils.writeToReport("Product added\nName: " + name + "\nPrice: " + price + "\nStock: " + stock
							+ "\nURL: " + url + "\n\n");
					if (addedCount == 2) {
						return;
					}
				}
				randomCategoryNavigation();

			} catch (Exception e) {
				log.error("Failed to add product: " + e.getMessage());
			} finally {
				attempts++;
			}
		}

		if (addedCount < 2)

		{
			myAssert.fail("Could not add 2 products to cart after " + attempts + " attempts");
			myAssert.assertAll();
		}
	}

	@Test(priority = 5, dependsOnMethods = { "randomItemToCart" })
	public void validateCartCounter() {
		cartpage = new CartPage(driver);
		int itemCount = cartpage.getCartItemCount();
		log.info("*** Cart contains " + (itemCount - 1) + " items ***");
		Assert.assertEquals(itemCount >= 3, true, "Less than 2 items in inventory");
	}
}
