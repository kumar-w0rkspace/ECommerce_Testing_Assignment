package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountLogin extends Base {

	public AccountLogin(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id='accountFrm_accountregister']")
	WebElement btn_register;
	@FindBy(xpath = "//button[@title='Continue']")
	WebElement btn_continue;

	public void createNewAccount() {
		if (myWait.until(ExpectedConditions.elementToBeClickable(btn_register)).isSelected()) {
			btn_continue.click();
		}
	}
}
