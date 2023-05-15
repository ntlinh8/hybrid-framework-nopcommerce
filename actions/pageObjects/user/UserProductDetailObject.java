package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import nopcommerce.user.UserProductDetailUI;

public class UserProductDetailObject extends BasePage{
	WebDriver driver;

	public UserProductDetailObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserAddProductReviewObject clickToAddYourReviewLink() {
		waitForElementVisible(driver, UserProductDetailUI.ADD_YOUR_REVIEWS_LINK);
		clickToElement(driver, UserProductDetailUI.ADD_YOUR_REVIEWS_LINK);
		return PageGeneratorManager.getUserAddProductReviewPage(driver);
	}
	
}