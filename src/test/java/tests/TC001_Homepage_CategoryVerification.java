package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.ScreenShotUtil;

public class TC001_Homepage_CategoryVerification extends BaseClass {
	HomePage hp;

	@Test(priority = 1)
	public void print_mainCategory_names() {
		log.info("*** Printing Main menu items ***");
		hp = new HomePage(driver);
		List<String> text = hp.getElementText();
		log.info("*** Categories found: " + String.join(", ", text) + " ***");
	}

	@Test(priority = 2)
	public void navigate_randomCategory() {
		log.info("*** Navigating to Random sub category ***");
		hp = new HomePage(driver);
		try {
			hp.clickRandomItem();
			log.info("*** Successfully navigated to subcategory ***");
		} catch (AssertionError | Exception e) {
			ScreenShotUtil.captureImage(driver, "NavigateRandomCategory");
			log.error(">> Navigation failed: " + e.getMessage() + " <<");
			Assert.fail("No subcategories available to click. Screenshot Saved. " + e.getMessage());
		}
	}

	@Test(priority = 3, dependsOnMethods = { "navigate_randomCategory" })
	public void verifyProductCount() {
		log.info("*** Verifying product count is over three ***");
		hp = new HomePage(driver);
		try {
			int count = hp.countProducts();
			log.info("*** Products displayed: " + count + " ***");
			Assert.assertTrue(count >= 3, "Product count is less than three. Found: " + count);
		} catch (AssertionError | Exception e) {
			ScreenShotUtil.captureImage(driver, "ProductCount");
			log.error(">> Product count verification failed: " + e.getMessage() + " <<");
			Assert.fail("Verification failed. Screenshot saved");
		}

	}

}
