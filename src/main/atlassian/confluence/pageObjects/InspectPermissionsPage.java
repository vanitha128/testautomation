package main.atlassian.confluence.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InspectPermissionsPage extends PageObject {

	public InspectPermissionsPage(WebDriver driver) {
		super(driver);
	}

	////*[@id="react-select-a6479a1f-6eda-4a6e-bf53-c1760e6ad53c-input"]
	@FindBy(xpath = "//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div[1]/div[1]/div/div/div/div/div[1]")
	private WebElement userNameArea;

	@FindBy(xpath = "/html/body/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div[1]/div[1]/div/div/div/div/div[1]/div[3]/div/input")
	private WebElement userName;
	
	@FindBy(xpath = "//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div[1]/div[1]/div/div/div/div[2]")
	private WebElement userNameAutoSuggestFirstOption;

	@FindBy(xpath = "/html/body/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div/div/div/div[2]")
	private WebElement permissionDropdown;

	@FindBy(xpath = "/html/body/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div/div/div[2]/div/div[3]")
	private WebElement canViewAndCommentPermission;

	@FindBy(xpath = "//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/footer/div/button/span/span/span")
	private WebElement okButton;

	public boolean isInitialized() {
		return true;
	}

	public void enterUserName(String userName) {
		this.userNameArea.click();
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void chooseUserNameAutoSuggestFirstOption() {
		userNameAutoSuggestFirstOption.click();
	}
	
	public void clickPermissionDropdown() {
		permissionDropdown.click();
	}
	
	public void chooseCanViewAndCommentPermission() {
		canViewAndCommentPermission.click();
	}
	
	public RestrictionsPage clickOk() {
		okButton.click();
		return new RestrictionsPage(driver);
	}
}
