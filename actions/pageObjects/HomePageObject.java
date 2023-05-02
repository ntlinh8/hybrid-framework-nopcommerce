package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import nopcommerce.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToRegisterLink() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
	}

}