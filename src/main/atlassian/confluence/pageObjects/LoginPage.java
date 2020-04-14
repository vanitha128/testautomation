package main.atlassian.confluence.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {
	
	
	@FindBy(id="username")
	private WebElement userName;
	
	@FindBy(xpath="//*[@id=\"login-submit\"]/span/span/span")
	private WebElement continueButton;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="login-submit")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void clickContinueButton() {
		continueButton.click();
	}
	
	public void enterPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public boolean isInitialized() {
		return userName.isDisplayed();
	}
}
