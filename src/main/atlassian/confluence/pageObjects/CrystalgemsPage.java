package main.atlassian.confluence.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CrystalgemsPage extends PageObject{
public CrystalgemsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//*[@id=\"system-content-items\"]/div/span/div/button/span/span/img")
	private WebElement restrictionsIconImg;
	
	
	@FindBy(xpath="//*[@id=\"profileGlobalItem\"]/div/span/div/span/span")
	private WebElement profile;

	@FindBy(xpath="//*[@id=\"uid4\"]/div/div/div/div[4]/a")
	private WebElement logout;

	
	
	public boolean isInitialized() {
		return restrictionsIconImg.isDisplayed();
	}
	
	public RestrictionsPage clickRestrictionIconImg() {
		restrictionsIconImg.click();
		return new RestrictionsPage(driver);
		
	}
	
	public void clickProfile() {
		profile.click();
	}
	
	public void clickLogout() {
		logout.click();
	}
}
