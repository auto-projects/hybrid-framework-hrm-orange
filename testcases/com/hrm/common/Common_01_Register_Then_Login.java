package com.hrm.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;
import utilities.DataUtil;

public class Common_01_Register_Then_Login extends BaseTest {
	public static Set<Cookie> loginPageCookie;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGenerator.getLoginPage(driver);
		fakeData = DataUtil.getData();

		statusValue = "Enabled";
		adminUserName = "Admin";
		adminPassword = "admin123";

		// Add Employee
		employeeFirstName = fakeData.getFirstName();
		employeeLastName = fakeData.getLastName();
		employeeUserName = fakeData.getUsername();
		employeePassword = fakeData.getPassword();

		log.info("Common_01 - Step 01: Login with Admin Role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);

		log.info("Common_01 - Step 02: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		log.info("Common_01 - Step 03: Click on 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		log.info("Common_01 - Step 04: Enter valid info in 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", employeeFirstName);

		log.info("Common_01 - Step 05: Enter valid info in 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", employeeLastName);

		log.info("Common_01 - Step 06: Get value of 'Employee ID' textbox");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Common_01 - Step 07: Click on 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Common_01 - Step 08: Enter valid info in 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", employeeUserName);

		log.info("Common_01 - Step 09: Enter valid info in 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", employeePassword);

		log.info("Common_01 - Step 10: Enter valid info in 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", employeePassword);

		log.info("Common_01 - Step 11: Select ' " + statusValue + " ' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);

		log.info("Common_01 - Step 12: Click on 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);
		
		log.info("Common_01 - Step 13: Login with Employee Role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, employeeUserName, employeePassword);

		log.info("Common_01 - Step 12: Get All Login Page Cookies");
		loginPageCookie = dashboardPage.getAllCookies(driver);

		log.info("►►►►►►►►►► Close Browsers and Drivers ►►►►►►►►►►" + browserName + "'");
		cleanDriverInstance();
	}

	private WebDriver driver;
	private LoginPO loginPage;
	private AddEmployeePO addEmployeePage;
	private DashboardPO dashboardPage;
	private EmployeeListPO employeeListPage;
	MyInfoPO myInfoPage;
	DataUtil fakeData;
	String adminUserName, adminPassword, employeeFirstName, employeeLastName, employeeUserName, employeePassword;
	String employeeID, statusValue;
	



}
