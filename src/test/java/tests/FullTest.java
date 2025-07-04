package tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.opencsv.exceptions.CsvValidationException;

import pages.AccountLogin;
import pages.Cart;
import pages.CheckOut;
import pages.Home;
import pages.Products;
import pages.Registration;
import utils.CSVReaderUtility;
import utils.ReportUtils;
import utils.ScreenShotUtil;

public class FullTest extends BaseClass {
	Home homePage;
	Products productPage;
	Cart cartpage;
	CheckOut checkOut;
	AccountLogin accLogin;
	Registration reg;
	SoftAssert myAssert;

	@BeforeMethod
	public void initSoftAssert() {
		myAssert = new SoftAssert();
	}

	@Test(priority = 1)
	public void print_mainCategory_names() {
		log.info("*** Printing Main menu items ***");
		homePage = new Home(driver);
		List<String> text = homePage.getElementText();
		log.info("*** Categories found: " + String.join(", ", text) + " ***");
		ReportUtils.writeToReport("Categories found: " + String.join(", ", text));
	}

	@Test(priority = 2)
	public void randomCategoryNavigation() {
		log.info("*** Navigating to Random Category ***");
		homePage = new Home(driver);
		homePage.clickProdCategory();
		homePage.clickSubCategory();
	}

	@Test(priority = 3, dependsOnMethods = { "randomCategoryNavigation" })
	public void verifyProductVisibility() {
		log.info("*** Veryfing if three products are visible ***");
		homePage = new Home(driver);
		int productCount = homePage.countProducts();
		if (productCount >= 3) {
			log.info("*** Category has " + productCount + " products ***");
		} else {
			log.error("Category has less than 3 products. Found: " + productCount);
			ScreenShotUtil.captureImage(driver, "ProductCountLessThanThree");
			myAssert.assertEquals(productCount, 3, "Category must have at least 3 products. Found: " + productCount);
			myAssert.assertAll();
		}

	}

	@Test(priority = 4, dependsOnMethods = { "randomCategoryNavigation" })
	public void randomItemToCart() {
		log.info("*** Adding two items to cart ***");
		homePage = new Home(driver);
		productPage = new Products(driver);
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
				if (stock.equalsIgnoreCase("Out of Stock") || stock.equalsIgnoreCase("Availability info not listed")) {
					log.warn(">>> Product not added to cart - Reason: " + stock + " <<<");
					ScreenShotUtil.captureImage(driver, stock);
				} else if (!cartVisible) {
					log.warn(">>> Product not added - Reason: Add to Cart button not visible or not enabled <<<");
					ReportUtils.writeToReport("SKIPPED - Cart Button Not Visible\n" + "Name  : " + name + "\n"
							+ "Price : " + price + "\n" + "Stock : " + stock + "\n" + "URL   : " + url + "\n\n");
					ScreenShotUtil.captureImage(driver, "CartNotVisible");
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
				ScreenShotUtil.captureImage(driver, "ProductAddFailure");
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
		log.info("*** Validating cart has 2 items ***");
		cartpage = new Cart(driver);
		int itemCount = cartpage.getCartItemCount();
		log.info("*** Cart contains " + (itemCount - 1) + " items ***");
		myAssert.assertEquals(itemCount >= 3, true, "Less than 2 items in inventory");
		myAssert.assertAll();
	}

	@Test(priority = 6, dependsOnMethods = { "randomItemToCart" })
	public void validateCartTotal() {
		log.info("*** Validating cart total ***");
		checkOut = new CheckOut(driver);
		Assert.assertEquals(checkOut.getTotalPrice(), true, "Price mismatch");
	}

	@Test(priority = 7, dependsOnMethods = { "validateCartTotal" })
	public void clickOnCheckOut() {
		log.info("*** Clicking on Create New Account ***");
		checkOut = new CheckOut(driver);
		accLogin = new AccountLogin(driver);
		checkOut.clickCheckOut();
		accLogin.createNewAccount();
	}

	@Test(priority = 8, dependsOnMethods = { "clickOnCheckOut" })
	public void fillRegistrationForm() throws CsvValidationException {
		log.info("*** Using testdata.csv to populate input fields ***");
		Map<String, String> userData = CSVReaderUtility.readSingleUser("testdata.csv");
		reg = new Registration(driver);
		reg.fillRegistrationForm(userData);
	}

	@Test(priority = 9, dependsOnMethods = { "fillRegistrationForm" })
	public void emptyingLastName() {
		log.info("*** Removing last name and clicking continue ***");
		reg = new Registration(driver);
		reg.removeLastName();
	}

	@Test(priority = 10, dependsOnMethods = { "emptyingLastName" })
	public void validatingErrorMsg() {
		log.info("*** Checking for error alerts ***");
		reg = new Registration(driver);
		reg.clickContinue();
		if (reg.isAlertPresent()) {
			log.info(">>> Validation Alert is displayed: Error - " + reg.getAlertText() + " <<<");
			ScreenShotUtil.captureImage(driver, "ValidationError");
		} else {
			log.warn(">>> No error alert was shown <<<");
			Assert.fail("Validation alert not found");
		}
	}

}
