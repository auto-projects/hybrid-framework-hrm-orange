package pageObjects.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.hrm.EmployeeListPageUI;

public class EmployeeListPO extends BasePage {
	WebDriver driver;

	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}
	public void openTabAtSideBarByName(String tabName) {
		waitForElementClickable(driver, EmployeeListPageUI.TAB_LINK_AT_SIDEBAR, tabName);
		clickToElement(driver, EmployeeListPageUI.TAB_LINK_AT_SIDEBAR, tabName);
	}

}
