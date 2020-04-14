package test.atlassian.confluence;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import main.atlassian.confluence.pageObjects.CrystalgemsPage;
import main.atlassian.confluence.pageObjects.InspectPermissionsPage;
import main.atlassian.confluence.pageObjects.LoginPage;
import main.atlassian.confluence.pageObjects.RestrictionsPage;
import test.atlassian.confluence.setup.SetupTest;

public class ConfluenceRestrictionsTest extends SetupTest {
	
	private String driverUrl="https://zeroloose1.atlassian.net/wiki/spaces/CRYSTAL/pages/229377/crystal+gems";

	@DataProvider(name = "testDataForApplyRestriction")
	public Object[][] getDataForApplyRestriction() {
		return new Object[][] { 
			{ "000loose@gmail.com", "test123$%^", "zero loose", "zero loose can view and comment on this page" },
			};

	}
	
	@DataProvider(name = "testDataForRemoveRestriction")
	public Object[][] getDataForRemoveRestriction() {
		return new Object[][] { 
			{ "000loose@gmail.com", "test123$%^", "zero loose", "denied permissions on 1 level" }
			};

	}

//	@Parameters({ "email", "password" }) - Uncomment this setting if parameters are needed to be provided from testng.xml
	@Test(groups = { "restrictions", "critical" }, dataProvider = "testDataForApplyRestriction")
	public void testRestriction(String email, String password, String userName, String searchString) {
		driver.get(driverUrl);

		LoginPage loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isInitialized());

		loginPage.enterUserName(email);
		loginPage.clickContinueButton();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		CrystalgemsPage crystalgemsPage = new CrystalgemsPage(driver);
		Assert.assertTrue(crystalgemsPage.isInitialized());

		RestrictionsPage restrictionsPage = crystalgemsPage.clickRestrictionIconImg();
		Assert.assertTrue(restrictionsPage.isInitialized());

		restrictionsPage.clickViewEditRestrictionsDropdown();
		restrictionsPage.clickDropOptionviewingAndEditingRestricted();
		restrictionsPage.enterUserName(userName);
		restrictionsPage.chooseUserNameAutoSuggestFirstOption();
		restrictionsPage.clickViewEditDropDown();
		restrictionsPage.chooseViewAndEditActionChoice();
		restrictionsPage.clickAdd();

		// validate if the new user is added to restrictions
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + userName + "')]"));
		Assert.assertTrue(list.size() > 0);

		restrictionsPage.clickApply();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		inspectPermissions(crystalgemsPage, userName, searchString);

		crystalgemsPage.clickProfile();
		crystalgemsPage.clickLogout();

	}

	private void inspectPermissions(CrystalgemsPage crystalgemsPage, String userName, String searchString) {

		// Validate restrictions via Inspect Permissions

		RestrictionsPage restrictionsPage = crystalgemsPage.clickRestrictionIconImg();
		Assert.assertTrue(restrictionsPage.isInitialized());

		InspectPermissionsPage inspectPermissionsPage = restrictionsPage.clickInspectPermissions();
		inspectPermissionsPage.isInitialized();

		inspectPermissionsPage.enterUserName(userName);
		inspectPermissionsPage.chooseUserNameAutoSuggestFirstOption();
		inspectPermissionsPage.clickPermissionDropdown();
		inspectPermissionsPage.chooseCanViewAndCommentPermission();
		
		//validate permissions displayed
		//zero loose can view and comment on this page
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + searchString + "')]"));
		Assert.assertTrue(list.size() > 0);
		
		restrictionsPage = inspectPermissionsPage.clickOk();
		
		restrictionsPage.clickCancel();
		
	}
	
	/*
	 * This test depends on the same user used by first step 
	 * To remove restrictions, it needs to be added first hence making this test depend on the first test using dependsOnMethod
	 * This can be avoided if we can use 2 separate users as this inhibits parallel execution of tests using testng
	 */
//	/dependsOnMethods= {"testRestriction"}
	@Test(dependsOnMethods= {"testRestriction"}, groups = {"restrictions", "critical" }, dataProvider = "testDataForRemoveRestriction")
	public void removeRestrictions(String email, String password, String userName, String searchString) {
		
		driver.get(driverUrl);

		LoginPage loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isInitialized());

		loginPage.enterUserName(email);
		loginPage.clickContinueButton();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		CrystalgemsPage crystalgemsPage = new CrystalgemsPage(driver);
		Assert.assertTrue(crystalgemsPage.isInitialized());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		RestrictionsPage restrictionsPage = crystalgemsPage.clickRestrictionIconImg();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(restrictionsPage.isInitialized());
		//click remove for user for whom restrictions were added earlier
		restrictionsPage.clickRemoveButton();
		restrictionsPage.clickApply();

		InspectPermissionsPage inspectPermissionsPage = restrictionsPage.clickInspectPermissions();
		inspectPermissionsPage.isInitialized();

		inspectPermissionsPage.enterUserName(userName);
		inspectPermissionsPage.chooseUserNameAutoSuggestFirstOption();
		inspectPermissionsPage.clickPermissionDropdown();
		inspectPermissionsPage.chooseCanViewAndCommentPermission();
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + searchString + "')]"));
		Assert.assertTrue(list.size() > 0);
		
	}

}
