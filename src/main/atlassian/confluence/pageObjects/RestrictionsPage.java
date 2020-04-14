package main.atlassian.confluence.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestrictionsPage extends PageObject{
	public RestrictionsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]/div/div/div[1]/div/div/div/div[2]")
	private WebElement viewEditRestrictionDropdown;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[1]/div/div[2]/div/div[3]/div/div[2]")
	private WebElement dropOptionviewingAndEditingRestricted;
	
	@FindBy(xpath="//*[@id=\"react-select-restrictions.user-search-input\"]")
	private WebElement userName;
	
	// this element is the first auto suggest option with the test entering full user name in userName text field
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/div/div/div/div/div/div/div[2]/div")
	private WebElement userNameAutoSuggestFirstOption;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/div/div[1]")
	private WebElement viewEditDropDown;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[2]/div/div/div[2]/div/div[2]/div/div[2]")
	private WebElement viewAndEditActionChoice;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[2]/div/div/div[3]/button/span/span/span")
	private WebElement add;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/footer/div/div[1]/button/span/span/span")
	private WebElement apply;

	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/header/div/div[2]/button/span/span[2]/div/span")
	private WebElement inspectPermissions;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/footer/div/div[2]/button/span/span/span")
	private WebElement cancelButton;
	
	@FindBy(xpath="//*[@id=\"com-atlassian-confluence\"]/div[2]/div[5]/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/table/tbody/tr[3]/td[3]/button/span/span/span")
	private WebElement removeButton;
	
	public boolean isInitialized() {
		return viewEditRestrictionDropdown.isDisplayed();
	}
	
	public void clickViewEditRestrictionsDropdown() {
		viewEditRestrictionDropdown.click();
	}
	
	public void clickDropOptionviewingAndEditingRestricted() {
		dropOptionviewingAndEditingRestricted.click();
	}
	
	public void enterUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void chooseUserNameAutoSuggestFirstOption() {
		userNameAutoSuggestFirstOption.click();
	}
	
	public void clickViewEditDropDown() {
		viewEditDropDown.click();
	}
	
	public void chooseViewAndEditActionChoice() {
		viewAndEditActionChoice.click();
	}
	
	public void clickAdd() {
		add.click();
	}
	
	public InspectPermissionsPage clickInspectPermissions() {
		inspectPermissions.click();
		return new InspectPermissionsPage(driver);
	}
	
	public void clickApply() {
		apply.click();
	}
	
	public CrystalgemsPage clickCancel() {
		cancelButton.click();
		return new CrystalgemsPage(driver);
	}
	
	public void clickRemoveButton() {
		removeButton.click();
	}
	

}
