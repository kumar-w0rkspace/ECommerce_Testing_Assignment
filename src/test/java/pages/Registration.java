package pages;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Registration extends Base {

	public Registration(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='AccountFrm_firstname']")
	WebElement txt_firstName;
	@FindBy(xpath = "//input[@id='AccountFrm_lastname']")
	WebElement txt_lastName;
	@FindBy(xpath = "//input[@id='AccountFrm_email']")
	WebElement txt_eMail;
	@FindBy(xpath = "//input[@id='AccountFrm_telephone']")
	WebElement txt_phNo;
	@FindBy(xpath = "//input[@id='AccountFrm_company']")
	WebElement txt_company;
	@FindBy(xpath = "//input[@id='AccountFrm_address_1']")
	WebElement txt_address1;
	@FindBy(xpath = "//input[@id='AccountFrm_address_2']")
	WebElement txt_address2;
	@FindBy(xpath = "//input[@id='AccountFrm_city']")
	WebElement txt_city;
	@FindBy(xpath = "//select[@id='AccountFrm_zone_id']")
	WebElement select_State;
	@FindBy(xpath = "//input[@id='AccountFrm_postcode']")
	WebElement txt_zip;
	@FindBy(xpath = "//select[@id='AccountFrm_country_id']")
	WebElement select_country;
	@FindBy(xpath = "//input[@id='AccountFrm_loginname']")
	WebElement txt_logName;
	@FindBy(xpath = "//input[@id='AccountFrm_password']")
	WebElement txt_logPass;
	@FindBy(xpath = "//input[@id='AccountFrm_confirm']")
	WebElement txt_logConfirmPass;
	@FindBy(xpath = "//input[@id='AccountFrm_newsletter1']")
	WebElement rd_newsLetterYes;
	@FindBy(xpath = "//input[@id='AccountFrm_agree']")
	WebElement box_policyAgree;
	@FindBy(xpath = "//button[@title='Continue']")
	WebElement btn_continue;
	@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
	WebElement error_Alert;

	public void fillRegistrationForm(Map<String, String> data) {
		txt_firstName.sendKeys(data.get("First Name"));
		txt_lastName.sendKeys(data.get("Last Name"));
		txt_eMail.sendKeys(data.get("E-Mail"));
		txt_phNo.sendKeys(data.get("Telephone"));
		txt_company.sendKeys(data.get("Company"));
		txt_address1.sendKeys(data.get("Address 1"));
		txt_address2.sendKeys(data.get("Address 2"));
		txt_city.sendKeys(data.get("City"));
		txt_zip.sendKeys(data.get("Zip Code"));
		txt_logName.sendKeys(data.get("Login Name"));
		txt_logPass.sendKeys(data.get("Password"));
		txt_logConfirmPass.sendKeys(data.get("Password Confirm"));

		Select country = new Select(select_country);
		country.selectByValue("99");
		Select state = new Select(select_State);
		state.selectByValue(String.valueOf(new Random().nextInt(1506 - 1475 + 1) + 1475));

		rd_newsLetterYes.click();
		box_policyAgree.click();

	}

	public void removeLastName() {
		txt_lastName.clear();
	}

	public boolean isAlertPresent() {
		try {
			return error_Alert.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getAlertText() {
		return error_Alert.getText().trim();
	}

	public void clickContinue() {
		btn_continue.click();
	}
}
