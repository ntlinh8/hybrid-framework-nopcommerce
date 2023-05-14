package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Topic_02_Login extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	String firstName, lastName, email, password, confirmPassword, incorrectPassword;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
		firstName = "Elon";
		lastName = "Musk";
		email = "elonmusk" + getRandomNumber() + "@gmail.com";
		password = "Abc13579";
		confirmPassword = "Abc13579";
		incorrectPassword = "123123123";
		
		// Pre-condition
		System.out.println("Pre-condition - Step 1: Enter the valid information");
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		System.out.println("Pre-condition - Step 2: Enter the valid information");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(confirmPassword);
		System.out.println("Pre-condition - Step 3: Click to Register button");
		registerPage.clickToRegisterButton();
		System.out.println("Pre-condition - Step 4: Verify the register success message displays");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		System.out.println("Pre-condition - Step 5: Back to Home page");
		registerPage.clickToContinueButton();
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login 01 - Step 1: Click to Login link");
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		
		System.out.println("Login 01 - Step 2: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 01 - Step 3: Verify the email error message displays");
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login 02 - Step 1: Click to Login link to clear data");
		loginPage.clickToLoginLink();
		
		System.out.println("Login 02 - Step 2: Input invalid email to email textbox");
		loginPage.inputToEmailTextbox("@gmail.com");
		
		System.out.println("Login 02 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 02 - Step 4: Verify the email error message displays");
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");
		
	}
	
	@Test
	public void Login_03_Email_Not_Register() {
		System.out.println("Login 03 - Step 1: Click to Login link to clear data");
		loginPage.clickToLoginLink();
		
		System.out.println("Login 03 - Step 2: Input email which was not registered in textbox");
		loginPage.inputToEmailTextbox("elonmusk" + getRandomNumber() + "@gmail.com");
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("Login 03 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 03 - Step 4: Verify the email not found message displays");
		Assert.assertEquals(loginPage.getEmailNotFoundMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void Login_04_Correct_Email_And_No_Password() {
		System.out.println("Login 04 - Step 1: Click to Login link to clear data");
		loginPage.clickToLoginLink();
		
		System.out.println("Login 04 - Step 2: Input correct email and keep the password field is empty");
		loginPage.inputToEmailTextbox(email);
		
		System.out.println("Login 04 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 04 - Step 4: Verify the email not found message displays");
		Assert.assertEquals(loginPage.getEmailNotFoundMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_05_Correct_Email_And_Incorrect_Password() {
		System.out.println("Login 05 - Step 1: Click to Login link to clear data");
		loginPage.clickToLoginLink();
		
		System.out.println("Login 05 - Step 2: Input correct email and incorrect password");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(incorrectPassword);
		
		System.out.println("Login 05 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 05 - Step 4: Verify the email not found message displays");
		Assert.assertEquals(loginPage.getEmailNotFoundMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Correct_Email_And_Correct_Password() {
		System.out.println("Login 06 - Step 1: Click to Login link to clear data");
		loginPage.clickToLoginLink();
		
		System.out.println("Login 06 - Step 2: Input correct email and correct password");
		loginPage.inputToEmailTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("Login 06 - Step 3: Click to Login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login 06 - Step 4: Verify the email not found message displays");
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
	}

	public void SleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(99999);
		return randomNumber;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}