package com.hrm.employee;

import java.lang.reflect.Method;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.hrm.AddEmployeePO;
import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.EmployeeListPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.MyInfoPO;
import pageObjects.hrm.PageGenerator;
import reportConfig.ExtentTestManager;
import utilities.DataUtil;

public class Live_Coding_Testcase_HRM_Orange extends BaseTest {
	String adminUserName, adminPassword, employeeFirstName, employeeLastName, employeeUserName, employeePassword;

	// Add Employee & Edit 'Personal Details'
	String employeeFullName, editEmpFirstName, editEmpLastName, editEmpGender, editEmpMaritalStatus, editEmpNationality;
	String employeeEditedFullName;
	String bloodType;

	// Edit 'Contact Details'
	String addressStreet1, addressStreet2, city, stateProvince, zipPostalCode, country, homeTelephone, mobile,
			workTelephone, workEmail, otherEmail;
	String employeeID, statusValue;

	// Emer. Contacts
	String emerName, emerRela, emerHomeTel, emerMobile, emerWorkTel;

	// Dependents
	String dependentName, dependentRela, specifyRela, dateOfBirth;
	Date dependentDOB;

	// Immigration
	String immigNumber, immigStatus, immigCountry, passportIssuedDate, passportIExpiredDate, immigReviewDate;

	// Job
	String jobTitle, empStatus, jobCategory, jobSubUnit, jobLocation;
	String jobJoinedDate, jobStartDate, jobEndDate;

	// Salary
	String payGrade, salaryComponent, payFrequency, currencyID, amount;
	String accountType;

	// Tax Exemptions
	String federalStatus, federalExemptions, taxState, stateStatus, tateExemptions, unempState, workState;

	// Qualifications
	String companyName, eduCode, eduInstitue, eduMajor, eduYear, eduGPA, skillCode, yearsOfExp, languageCode, fluency,
			languageCompetency, licenseCode, licenseNumber;
	String expFromDate, expToDate, eduStartDate, eduEndDate, licenseDate, licenseExpiryDate;

	// Memeberships
	String membership, subscriptionPaidBy, subscriptionAmount, currency;
	String subComDate, subReDate;

	String avatarFilePath = GlobalConstants.UPLOAD_FILE + "Dedo.jpg";
	String attachmentFilePath = GlobalConstants.UPLOAD_FILE + "airbus320.jpg";
	String contractFilePath = GlobalConstants.UPLOAD_FILE + "Yunxi_Yi.jpg";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		loginPage = PageGenerator.getLoginPage(driver);
		fakeData = DataUtil.getData();
		showBrowserConsoleLogs(driver);

		statusValue = "Enabled";
		adminUserName = "Admin";
		adminPassword = "admin123";

		// Add Employee
		employeeFirstName = fakeData.getFirstName();
		employeeLastName = fakeData.getLastName();
		employeeUserName = fakeData.getUsername();
		employeePassword = fakeData.getPassword();
		employeeFullName = employeeFirstName + " " + employeeLastName;

		// Edit 'Personal Details'
		editEmpFirstName = fakeData.getEditFirstName();
		editEmpLastName = fakeData.getEditLastName();
		editEmpGender = "Female";
		editEmpMaritalStatus = "Single";
		editEmpNationality = "Vietnamese";
		employeeEditedFullName = editEmpFirstName + " " + editEmpLastName;
		bloodType = "O+";

		// Emer. Contacts
		emerName = fakeData.getName();
		emerRela = fakeData.getRelationship();
		emerHomeTel = fakeData.getNumber();
		emerMobile = fakeData.getNumber();
		emerWorkTel = fakeData.getNumber();

		// Dependents
		dependentName = fakeData.getName();
		dependentRela = "Other";
		specifyRela = fakeData.getRelationship();
		dependentDOB = fakeData.getYYYYMMDD();
		dateOfBirth = "1991-06-03";

		// Immigration
		immigNumber = fakeData.getNumber();
		immigStatus = "Yes";
		immigCountry = "Japan";
		passportIssuedDate = "2010-09-01";
		passportIExpiredDate = "2025-09-01";
		immigReviewDate = "2022-01-06";

		// Edit 'Contact Details'
		addressStreet1 = fakeData.getStreetAddress();
		addressStreet2 = fakeData.getStreetAddress();
		city = fakeData.getCity();
		stateProvince = fakeData.getState();
		zipPostalCode = fakeData.getZipPostalCode();
		country = "Israel";
		homeTelephone = fakeData.getNumber();
		mobile = fakeData.getNumber();
		workTelephone = fakeData.getNumber();
		workEmail = fakeData.getEmailAddress();
		otherEmail = fakeData.getEmailAddress();

		// Job
		jobTitle = "Automation Tester";
		empStatus = "Freelance";
		jobCategory = "Professionals";
		jobSubUnit = "  Quality Assurance";
		jobLocation = "New York Sales Office";
		jobJoinedDate = "2021-10-14";
		jobStartDate = "2021-10-15";
		jobEndDate = "2022-04-22";

		// Salary
		payGrade = "Grade 14";
		salaryComponent = fakeData.getNumber();
		payFrequency = "Weekly";
		currencyID = "United States Dollar";
		amount = "12345";
		accountType = "Savings";

		// Tax Exemptions
		federalStatus = "Not Applicable";
		federalExemptions = "12";
		taxState = "Hawaii";
		stateStatus = "Not Applicable";
		tateExemptions = "11";
		unempState = "California";
		workState = "New York";

		// Qualifications
		companyName = fakeData.getCompanyName();
		eduCode = "Master's Degree";
		eduInstitue = "Aviation Academy";
		eduMajor = "Aviation";
		eduYear = "1991";
		eduGPA = "9.0";
		skillCode = "Java";
		yearsOfExp = "3";
		languageCode = "English";
		fluency = "Writing";
		languageCompetency = "Good";
		licenseCode = "PMI Agile Certified Practitioner (PMI-ACP)";
		licenseNumber = fakeData.getNumber();
		expFromDate = "2015-06-01";
		expToDate = "2022-05-22";
		eduStartDate = "2010-09-02";
		eduEndDate = "2014-06-02";
		licenseDate = "2010-09-01";
		licenseExpiryDate = "2025-09-01";

		// Memeberships
		membership = "British Computer Society (BCS)";
		subscriptionPaidBy = "Company";
		currency = "Vietnamese Dong";
		subscriptionAmount = fakeData.getNumber();
		subComDate = "2022-01-01";
		subReDate = "2023-01-01";

		log.info("Pre-Condition - Step 02: Login with Admin Role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
		showBrowserConsoleLogs(driver);
	}

	@Test
	public void Employee_01_Add_New_Employee(Method method) {
		ExtentTestManager.startTest(method.getName(), "ADD NEW EMPLOYEE");

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 01: Open 'Employee List' Page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 02: Click on 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = PageGenerator.getAddEmployeePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 03: Enter valid info in 'First Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "firstName", employeeFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 04: Enter valid info in 'Last Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "lastName", employeeLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 05: Get value of 'Employee ID' textbox");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 06: Click on 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 07: Enter valid info in 'User Name' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_name", employeeUserName);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 08: Enter valid info in 'Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "user_password", employeePassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add_New_01 - Step 09: Enter valid info in 'Confirm Password' textbox");
		addEmployeePage.enterToTextboxByID(driver, "re_password", employeePassword);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add_New_01 - Step 10: Select '" + statusValue + "' value in 'Status' dropdown");
		addEmployeePage.selectItemInDropdownByID(driver, "status", statusValue);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 11: Click on 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 12: Open 'Employee List' Page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Add_New_01 - Step 13: Enter valid info in 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", employeeFullName);
		verifyTrue(employeeListPage.areJQueryAndJSLoadedSuccess(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Add_New_01 - Step 14: Click on 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		ExtentTestManager.getTest().log(Status.INFO,
				"Add_New_01 - Step 15: Verify Employee Infomation displayed at 'Result Table' ");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"),
				employeeID);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable",
				"First (& Middle) Name", "1"), employeeFirstName);
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"),
				employeeLastName);

	}

	@Test
	public void Employee_02_Upload_Avatar(Method method) {
		ExtentTestManager.startTest(method.getName(), "UPLOAD AVATAR");

		ExtentTestManager.getTest().log(Status.INFO, "Upload_Avatar_02 - Step 01: Login with Employee Role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, employeeUserName, employeePassword);

		ExtentTestManager.getTest().log(Status.INFO, "Upload_Avatar_02 - Step 02: Open Personal Detail Page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Upload_Avatar_02 - Step 03: Click on Change Photo Image");
		myInfoPage.clickToChangePhotoImage();

		ExtentTestManager.getTest().log(Status.INFO, "Upload_Avatar_02 - Step 04: Upload New Avatar Image");
		myInfoPage.uploadImage(driver, avatarFilePath);

		ExtentTestManager.getTest().log(Status.INFO, "Upload_Avatar_02 - Step 05: Click on 'Upload' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");
		myInfoPage.isJQueryAjaxLoadedSuccess(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Upload_Avatar_02 - Step 06: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Upload_Avatar_02 - Step 07: Verify New Avatar Image is Displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details(Method method) {
		ExtentTestManager.startTest(method.getName(), "PERSONAL DETAILS");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 01: Open 'Personal Details' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 02: Verify all fields at 'Personal Details' forms are Disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpMiddleName"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpLastName"));

		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtOtherID"));

		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicExpDate"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));

		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_optGender_2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_cmbNation"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));
		// Sometimes Admin hides these:
//		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmpNickName"));
//		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_chkSmokeFlag"));
//		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtMilitarySer"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 03: Click on 'Edit' Button at 'Personal Details' ");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 04: Verify 'Employee ID' textbox is disabled"); // 1
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtEmployeeId"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 05: Verify 'Driver's License Number textbox is disabled"); // 2
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtLicenNo"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 06: Verify 'SSN Number' textbox is disabled"); // 3
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtNICNo"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 07: Verify 'SIN Number' textbox is disabled"); // 4
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_txtSINNo"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 08: Verify 'Date of Birth' textbox is disabled"); // 5
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "personal_DOB"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 09: Enter new value in 'First Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpFirstName", editEmpFirstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 10: Enter new value in 'Last Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "personal_txtEmpLastName", editEmpLastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 11: Select new value from 'License Expiry Date' datepicker");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "personal_txtLicExpDate", licenseExpiryDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 12: Select new value from 'Gender' radio button");
		myInfoPage.clickToRadioByLabel(driver, editEmpGender);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 13: Select new value from 'Marital Status' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbMarital", editEmpMaritalStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 14: Select new value from 'Nationality' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "personal_cmbNation", editEmpNationality);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 15: Click on 'Save' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 16: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 17: Verify 'First Name' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 18: Verify 'Last Name' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 19: Verify 'License Expiry Date' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtLicExpDate"), licenseExpiryDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 20: Verify 'Gender' radio button is successfully updated");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLable(driver, editEmpGender));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 21: Verify 'Marital Status' dropdown is successfully updated");
		verifyEquals(myInfoPage.getSelectedValueInDropdownByAttribute(driver, "personal_cmbMarital"),
				editEmpMaritalStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 22: Verify 'Nationality' dropdown is successfully updated");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 23: Verify 'Employee ID' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 24: CUSTOM FIELDS - Click on 'Edit' button at 'Custom Fields' ");
		myInfoPage.clickToButtonByID(driver, "btnEditCustom");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 25: CUSTOM FIELDS - Print out all values in 'Blood Type' dropdown list");
		myInfoPage.getAllValuesInDropdownListByName(driver, "custom1");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 26: CUSTOM FIELDS - Select new value in 'Blood Type' dropdown");
		myInfoPage.selectItemInDropdownByName(driver, "custom1", bloodType);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 27: CUSTOM FIELDS - Click on 'Save' button at 'Custom Fields' field");
		myInfoPage.clickToButtonByID(driver, "btnEditCustom");

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 28: CUSTOM FIELDS - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 29: CUSTOM FIELDS - Verify new 'Blood Type' is successfully updated");
		verifyEquals(myInfoPage.getDisabledTextInBloodTypeDropdownByName(driver, "custom1", bloodType), bloodType);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 30: ATTACHMENTS - Click on 'Add' button at 'Attachments' field");
		myInfoPage.clickToButtonByID(driver, "btnAddAttachment");

		ExtentTestManager.getTest().log(Status.INFO, "Personal Details_03 - Step 31: ATTACHMENTS - Upload Attachment");
		myInfoPage.uploadImage(driver, attachmentFilePath);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 32: ATTACHMENTS - Click on 'Upload' button");
		myInfoPage.clickToButtonByID(driver, "btnSaveAttachment");
		myInfoPage.isJQueryAjaxLoadedSuccess(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 33: ATTACHMENTS - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Personal Details_03 - Step 34: ATTACHMENTS - Verify new attachment is uploaded");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "tblAttachments", "File Name", "1"),
				"airbus320.jpg");

	}

	@Test
	public void Employee_04_Contact_Details(Method method) {
		ExtentTestManager.startTest(method.getName(), "CONTACT DETAILS");

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 01: Open 'Contact Details' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 02: Verify all fields at 'Contact Details' forms are Disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "contact_emp_oth_email"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 03: Click on 'Edit' Button at 'Contact Details' ");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 04: Enter new value in 'Address Street 1' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street1", addressStreet1);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 05: Enter new value in 'Address Street 2' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_street2", addressStreet2);

		ExtentTestManager.getTest().log(Status.INFO, "Contact_Details_04 - Step 06: Enter new value in 'City' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_city", city);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 07: Enter new value in 'State/Province' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_province", stateProvince);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 08: Enter new value in 'Zip/Postal Code' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_zipcode", zipPostalCode);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 09: Select new value to 'Country' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "contact_country", country);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 10: Enter new value in 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_hm_telephone", homeTelephone);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 11: Enter new value in 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_mobile", mobile);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 12: Enter new value in 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_telephone", workTelephone);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 13: Enter new value in 'Work Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_work_email", workEmail);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 14: Enter new value in 'Other Email' textbox");
		myInfoPage.enterToTextboxByID(driver, "contact_emp_oth_email", otherEmail);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 15: Click on 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 16: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 17: Verify 'Address Street 1' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), addressStreet1);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 18: Verify 'Address Street 2' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), addressStreet2);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 19: Verify 'City' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), city);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 20: Verify 'State/Province' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), stateProvince);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 21: Verify 'Zip/Postal Code' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), zipPostalCode);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 22: Verify 'Country' dropdown is successfully updated");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "contact_country"), country);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 23: Verify 'Home Telephone' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_hm_telephone"), homeTelephone);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 24: Verify 'Mobile' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), mobile);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 25: Verify 'Work Telephone' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_telephone"), workTelephone);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 26: Verify 'Work Email' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), workEmail);

		ExtentTestManager.getTest().log(Status.INFO,
				"Contact_Details_04 - Step 27: Verify 'Other Email' textbox is successfully updated");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_oth_email"), otherEmail);

	}

	@Test
	public void Employee_05_Emergency_Contacts(Method method) {
		ExtentTestManager.startTest(method.getName(), "EMERGENCY CONTACTS");

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 01: Open 'Emergency Contacts' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 02: Click on 'Add' button at 'Assigned Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 03: Verify all fields at 'Add Emergency Contact' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "emgcontacts_name"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "emgcontacts_relationship"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "emgcontacts_homePhone"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "emgcontacts_mobilePhone"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "emgcontacts_workPhone"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 04: Enter new value in 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_name", emerName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 05: Enter new value in 'Relationship' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_relationship", emerRela);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 06: Enter new value in 'Home Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_homePhone", emerHomeTel);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 07: Enter new value in 'Mobile' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_mobilePhone", emerMobile);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 08: Enter new value in 'Work Telephone' textbox");
		myInfoPage.enterToTextboxByID(driver, "emgcontacts_workPhone", emerWorkTel);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 09: Click on 'Save' button at 'Add Emergency Contact' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 10: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 11: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Emergency_Contacts_05 - Step 12: Verify added 'Name' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"),
				emerName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 13: Verify added 'Relationship' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"),
				emerRela);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 14: Verify added 'Home Telephone' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"),
				emerHomeTel);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 15: Verify added 'Mobile' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Mobile", "1"),
				emerMobile);

		ExtentTestManager.getTest().log(Status.INFO,
				"Emergency_Contacts_05 - Step 16: Verify added 'Work Telephone' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Work Telephone", "1"),
				emerWorkTel);

	}

	@Test
	public void Employee_06_Assigned_Dependents(Method method) {
		ExtentTestManager.startTest(method.getName(), "DEPENDENTS");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 01: Open 'Dependents' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Dependents");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 02: Click on 'Add' button at 'Assigned Dependents' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 03: Verify all fields at 'Add Dependent' are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "dependent_name"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "dependent_relationshipType"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "dependent_dateOfBirth"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 04: Enter new value in 'Name' textbox");
		myInfoPage.enterToTextboxByID(driver, "dependent_name", dependentName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 05: Select '" + dependentRela + "' value in 'Relationship' dropdown");
		myInfoPage.selectItemInDropdownByID(driver, "dependent_relationshipType", dependentRela);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 06: Enter new value in 'Please Specify' textbox");
		myInfoPage.enterToTextboxByID(driver, "dependent_relationship", specifyRela);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 07: Pick new value from 'Date of Birth' datepicker");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "dependent_dateOfBirth", dateOfBirth);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 08: Click on 'Save' button at 'Add Dependent' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 09: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 10: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 11: Verify added 'Name' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"),
				dependentName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 12: Verify added 'Relationship' is updated");
		verifyEquals(myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"),
				specifyRela);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Dependents_06 - Step 13: Verify added 'Date of Birth' is updated");
		verifyEquals(
				myInfoPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"),
				dateOfBirth);
	}

	@Test
	public void Employee_07_Assigned_Immigration_Records(Method method) {
		ExtentTestManager.startTest(method.getName(), "IMMIGRATION");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 01: Open 'Immigration' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Immigration");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 02: Click on 'Add' button at 'Assigned Immigration Records' ");
		myInfoPage.clickToButtonByID(driver, "btnAdd");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 03: Verify that 'Passport' checkbox default selected");
		verifyTrue(myInfoPage.isRadioButtonSelectedByLable(driver, "Passport"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 04: Veriy all fields at 'Add Immigration' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_number"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_passport_issue_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_passport_expire_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_i9_status"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_country"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_i9_review_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "immigration_comments"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 05: Enter new value in 'Number' textbox");
		myInfoPage.enterToTextboxByID(driver, "immigration_number", immigNumber);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 06: Enter new value in 'Issued Date' datepicker-textbox");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "immigration_passport_issue_date", passportIssuedDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 07: Enter new value in 'Expiry Date' datepicker-textbox");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "immigration_passport_expire_date", passportIExpiredDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 08: Enter new value in 'Eligible Status' textbox");
		myInfoPage.enterToTextboxByID(driver, "immigration_i9_status", immigStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 09: Print out all values in 'Issued By' dropdown list at 'Add Immigration' form");
		employeeListPage.getAllValuesInDropdownByID(driver, "immigration_country");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 10: Select new value to 'Issued By' dropdown at 'Add Immigration' form");
		employeeListPage.selectItemInDropdownByID(driver, "immigration_country", immigCountry);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07 - Step 11: Enter new value in 'Eligible Review Date' datepicker-textbox");
		employeeListPage.sendKeysToDatePickerTextboxByID(driver, "immigration_i9_review_date", immigReviewDate);

		ExtentTestManager.getTest().log(Status.INFO, "Assigned_Immigration_07 - Step 12: Click on 'Save' button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 13: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 14: Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 15: Verify added 'Passport' is updated");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Document", "1"),
				"Passport");

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 16: Verify added 'Number' is updated");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Number", "1"),
				immigNumber);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 17: Verify added 'Issued By' is updated");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Issued By", "1"),
				immigCountry);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 18: Verify added 'Issued Date' is updated");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Issued Date", "1"),
				passportIssuedDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Assigned_Immigration_07_ Step 19: Verify added 'Expired Date' is updated");
		verifyEquals(
				myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Expired Date", "1"),
				passportIExpiredDate);
	}

	@Test
	public void Employee_08_Edit_View_Job(Method method) { // Only Edit by Admin
		ExtentTestManager.startTest(method.getName(), "JOB");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 01: Open 'Job' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Job");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 02: Verify some fields at 'Job' form are Disabled");
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_job_title"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_emp_status"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_eeo_category"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_joined_date"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_sub_unit"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_location"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_contract_start_date"));
		verifyFalse(myInfoPage.isFieldEnabledByID(driver, "job_contract_end_date"));

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 03: Login with 'Admin' Role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 04: Open 'Employee List' Page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = PageGenerator.getEmployeeListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 05: Enter valid info in 'Employee Name' textbox");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));
		employeeListPage.enterToTextboxByID(driver, "empsearch_employee_name_empName", employeeEditedFullName);
		verifyTrue(employeeListPage.areJQueryAndJSLoadedSuccess(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 06: Click on 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		verifyTrue(employeeListPage.isJQueryAjaxLoadedSuccess(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 07: Click on Employee's name");
		employeeListPage.clickOnElementByTextInTableByID(driver, "resultTable", editEmpFirstName);
		employeeListPage.isJQueryAjaxLoadedSuccess(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 08: Open 'Job' Tab at Side Bar");
		employeeListPage.openTabAtSideBarByName("Job");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 09: Click on 'Edit' button at 'Job' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 10: Print out all values in 'Job Title' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "job_job_title");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 11: Select new value to 'Job Title' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "job_job_title", jobTitle);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 12: Print out all values in 'Employment Status' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "job_emp_status");

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 13: Select new value to 'Employment Status' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "job_emp_status", empStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 14: Print out all values in 'Job Category' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "job_eeo_category");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 15: Select new value to 'Job Category' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "job_eeo_category", jobCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 16: Pick new value from 'Joined Date' datepicker");
		employeeListPage.sendKeysToDatePickerTextboxByID(driver, "job_joined_date", jobJoinedDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 17: Print out all values in 'Sub Unit' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "job_sub_unit");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 18: Select new value to 'Sub Unit' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "job_sub_unit", jobSubUnit);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 19: Print out all values in 'Location' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "job_location");

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 20: Select new value to 'Location' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "job_location", jobLocation);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 21: Pick new value from 'Start Date' datepicker at 'Employment Contract' ");
		employeeListPage.sendKeysToDatePickerTextboxByID(driver, "job_contract_start_date", jobStartDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 22: Pick new value from 'End Date' datepicker at 'Employment Contract' ");
		employeeListPage.sendKeysToDatePickerTextboxByID(driver, "job_contract_end_date", jobEndDate);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 23: Upload new file at 'Contract Details' ");
		employeeListPage.uploadImage(driver, contractFilePath);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 24: Click on 'Save' button");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		employeeListPage.isJQueryAjaxLoadedSuccess(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 25: Verify Success Message is Displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 26: Verify 'Job Title' textbox is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_job_title"), jobTitle);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 27: Verify 'Employment Status' textbox is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_emp_status"), empStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 28: Verify 'Job Category' textbox is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_eeo_category"), jobCategory);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 29: Verify 'Joined Date' textbox is successfully updated");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_joined_date"), jobStartDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 30: Verify 'Sub Unit' textbox is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_sub_unit"), jobSubUnit);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 31: Verify 'Location' textbox is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_location"), jobLocation);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 32: Verify 'Start Date' textbox is successfully updated");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_contract_start_date"), jobStartDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Job_08 - Step 33: Verify 'End Date' textbox is successfully updated");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_contract_end_date"), jobEndDate);

		ExtentTestManager.getTest().log(Status.INFO, "Job_08 - Step 34: Verify 'Contact Details' is uploaded");
		verifyEquals(employeeListPage.getTextOfContractDetails(), contractFilePath);

	}

	@Test
	public void Employee_09_Edit_View_Salary(Method method) {
		ExtentTestManager.startTest(method.getName(), "SALARY");

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 01: Open 'Salary' Tab at Side Bar");
		employeeListPage.openTabAtSideBarByName("Salary");

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 02: Click on 'Add' button at 'Assigned Salary Components' form");
		employeeListPage.clickToButtonByID(driver, "addSalary");

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 03: Verify all fields at 'Add Salary Component' are Enabled");
		verifyTrue(employeeListPage.isFieldEnabledByID(driver, "salary_sal_grd_code"));
		verifyTrue(employeeListPage.isFieldEnabledByID(driver, "salary_salary_component"));
		verifyTrue(employeeListPage.isFieldEnabledByID(driver, "salary_payperiod_code"));
		verifyTrue(employeeListPage.isFieldEnabledByID(driver, "salary_currency_id"));
		verifyTrue(employeeListPage.isFieldEnabledByID(driver, "salary_basic_salary"));
		verifyTrue(employeeListPage.isFieldEnabledByID(driver, "salary_comments"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 04: Print out all values in 'Pay Grade' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "salary_sal_grd_code");

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 05: Select new value to 'Pay Grade' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "salary_sal_grd_code", payGrade);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 06: Enter new value in 'Salary Component' textbox");
		employeeListPage.enterToTextboxByID(driver, "salary_salary_component", salaryComponent);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 07: Print out all values in 'Pay Frequency ' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "salary_payperiod_code");

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 08: Select new value to 'Pay Frequency' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "salary_payperiod_code", payFrequency);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 09: Print out all values in 'Currency ' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "salary_currency_id");

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 10: Select new value to 'Currency' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "salary_currency_id", currencyID);

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 11: Enter new value in 'Amount' textbox");
		employeeListPage.enterToTextboxByID(driver, "salary_basic_salary", amount);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 12: Click on 'Add Direct Deposit Details' checkbox");
		employeeListPage.clickToCheckboxByLabel(driver, "Add Direct Deposit Details");

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 13: Verify some fields at 'Add Direct Deposit Details' are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "directdeposit_account"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "directdeposit_account_type"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "directdeposit_routing_num"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "directdeposit_amount"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 14: Enter new value in 'Account Numbert' textbox");
		employeeListPage.enterToTextboxByID(driver, "directdeposit_account", amount);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 15: Print out all values in 'Account Type' dropdown list");
		employeeListPage.getAllValuesInDropdownByID(driver, "directdeposit_account_type");

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 16: Select new value to 'Account Type' dropdown");
		employeeListPage.selectItemInDropdownByID(driver, "directdeposit_account_type", accountType);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 17: Enter new value in 'Routing Number' textbox");
		employeeListPage.enterToTextboxByID(driver, "directdeposit_routing_num", amount);

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 18: Enter new value in 'Amount' textbox");
		employeeListPage.enterToTextboxByID(driver, "directdeposit_amount", amount);

		ExtentTestManager.getTest().log(Status.INFO,
				"Salary_09 - Step 19: Click on 'Add' button at 'Add Salary Component' form");
		employeeListPage.clickToButtonByID(driver, "btnSalarySave");

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 20: Verify Success Message is Displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 21: Verify added 'Salary Component' is updated");
		verifyEquals(
				employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Salary Component", "1"),
				salaryComponent);

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 22: Verify added 'Pay Frequency' is updated");
		verifyEquals(
				employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Pay Frequency", "1"),
				payFrequency);

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 23: Verify added 'Currency' is updated");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Currency", "1"),
				currencyID);

		ExtentTestManager.getTest().log(Status.INFO, "Salary_09 - Step 24: Verify added 'Amount' is updated");
		verifyEquals(employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Amount", "1"),
				amount);

	}

	@Test
	public void Employee_10_Edit_View_Tax(Method method) {
		ExtentTestManager.startTest(method.getName(), "TAX EXEMPTIONS");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 01: Open 'Tax Exemptions' Tab at Side Bar");
		employeeListPage.openTabAtSideBarByName("Tax Exemptions");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 02: Verify all fields at 'Tax Exemptions' form are Disabled");
		// Federal Income Tax
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_federalStatus"));
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_federalExemptions"));
		// State Income Tax
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_state"));
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_stateStatus"));
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_stateExemptions"));
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_unempState"));
		verifyFalse(employeeListPage.isFieldEnabledByID(driver, "tax_workState"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 03: Click on 'Edit' button at 'Tax Exemptions' ");
		employeeListPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 04: Print out all values in 'Status' dropdown list at 'Federal Income Tax' ");
		employeeListPage.getAllValuesInDropdownByID(driver, "tax_federalStatus");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 05: Select new value to 'Status' dropdown at 'Federal Income Tax' ");
		employeeListPage.selectItemInDropdownByID(driver, "tax_federalStatus", federalStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 06: Enter new value on 'Exemptions' textbox at 'Federal Income Tax' ");
		employeeListPage.enterToTextboxByID(driver, "tax_federalExemptions", federalExemptions);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 07: Print out all values in 'State' dropdown list at 'State Income Tax' ");
		employeeListPage.getAllValuesInDropdownByID(driver, "tax_state");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 08: Select new value to 'State' dropdown at 'State Income Tax' ");
		employeeListPage.selectItemInDropdownByID(driver, "tax_state", taxState);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 09: Print out all values in 'Status' dropdown list at 'State Income Tax' ");
		employeeListPage.getAllValuesInDropdownByID(driver, "tax_stateStatus");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 10: Select new value to 'Status' dropdown at 'State Income Tax' ");
		employeeListPage.selectItemInDropdownByID(driver, "tax_stateStatus", stateStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 11: Enter new value in 'Exemptions' textbox at 'State Income Tax' ");
		employeeListPage.enterToTextboxByID(driver, "tax_stateExemptions", tateExemptions);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 12: Print out all values in 'Unemployment State' dropdown list at 'State Income Tax' ");
		employeeListPage.getAllValuesInDropdownByID(driver, "tax_unempState");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 13: Select new value to 'Unemployment State' dropdown at 'State Income Tax' ");
		employeeListPage.selectItemInDropdownByID(driver, "tax_unempState", unempState);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 14: Print out all values in 'Work State' dropdown list at 'State Income Tax' ");
		employeeListPage.getAllValuesInDropdownByID(driver, "tax_workState");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 15: Select new value to 'Work State' dropdown at 'State Income Tax' ");
		employeeListPage.selectItemInDropdownByID(driver, "tax_workState", workState);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 16: Click on 'Save' button at 'Tax Exemptions' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 17: Verify Success Message is Displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 18: Verify 'Status' textbox at 'Tax Exemptions'  is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_federalStatus"), federalStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 19: Verify 'Exemptions' textbox at 'Tax Exemptions'  is successfully updated");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "tax_federalExemptions"), federalExemptions);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 20: Verify 'State' textbox at 'State Income Tax'  is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_state"), taxState);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 21: Verify 'Status' textbox at 'State Income Tax'  is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_stateStatus"), stateStatus);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 22: Verify 'Exemptions' textbox at 'State Income Tax'  is successfully updated");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "tax_stateExemptions"), tateExemptions);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 23: Verify 'Unemployment State' textbox at 'State Income Tax'  is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_unempState"), unempState);

		ExtentTestManager.getTest().log(Status.INFO,
				"Tax_Exemptions_10 - Step 24: Verify 'Work State' textbox at 'State Income Tax'  is successfully updated");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_workState"), workState);

	}

	@Test
	public void Employee_11_Qualifications(Method method) {
		ExtentTestManager.startTest(method.getName(), "QUALIFICATIONS");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 01: Login back again with 'Employee Role'");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, employeeUserName, employeePassword);
//		loginPage.setAllCookies(driver, Common_01_Register_Then_Login.loginPageCookie);
//		loginPage.sleepInSecond(5);
//		loginPage.refreshCurrentPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Qualifications_11 - Step 02: Open 'Personal Detail' Page");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = PageGenerator.getMyInfoPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 03: Open 'Qualifications' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Qualifications");

		// Work Experience
		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 04: WORK EXPERIENCE - Click on 'Add' button at 'Work Experience' form");
		myInfoPage.clickToButtonByID(driver, "addWorkExperience");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 05: WORK EXPERIENCE - Verify all fields at 'Add Work Experience' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "experience_employer"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "experience_jobtitle"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "experience_from_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "experience_to_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "experience_comments"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 06: WORK EXPERIENCE - Enter new value in 'Company' textbox at 'Add Work Experience' form");
		myInfoPage.enterToTextboxByID(driver, "experience_employer", companyName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 07: WORK EXPERIENCE - Enter new value in 'Job Title' textbox at 'Add Work Experience' form");
		myInfoPage.enterToTextboxByID(driver, "experience_jobtitle", jobTitle);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 08: WORK EXPERIENCE - Pick new value at 'From' ");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "experience_from_date", expFromDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 09: WORK EXPERIENCE - Pick new value at 'To' ");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "experience_to_date", expToDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 10 : WORK EXPERIENCE - Click on 'Save' button at 'Add Work Experience' form");
		myInfoPage.clickToButtonByID(driver, "btnWorkExpSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 11 : WORK EXPERIENCE - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 12: WORK EXPERIENCE - Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 13: WORK EXPERIENCE - Verify added 'Company' is updated");
		verifyEquals(
				myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionWorkExperience", "Company", "1"),
				companyName);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 14: WORK EXPERIENCE - Verify added 'Job Title' is updated");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionWorkExperience",
				"Job Title", "1"), jobTitle);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 15: WORK EXPERIENCE - Verify added 'From' is updated");
		verifyEquals(
				myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionWorkExperience", "From", "1"),
				expFromDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 16: WORK EXPERIENCE - Verify added 'To' is updated");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionWorkExperience", "To", "1"),
				expToDate);

		// Education
		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 17: EDUCATION - Click on 'Add' button at 'Education' form");
		myInfoPage.clickToButtonByID(driver, "addEducation");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 18: EDUCATION - Verify all fields at 'Education' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_code"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_institute"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_major"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_year"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_gpa"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_start_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "education_end_date"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 19: EDUCATION - Print out all values in 'Level' dropdown list at 'Add Education' ");
		myInfoPage.getAllValuesInDropdownByID(driver, "education_code");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 20: EDUCATION - Select new value 'Level' dropdown list at 'Add Education' ");
		myInfoPage.selectItemInDropdownByID(driver, "education_code", eduCode);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 21: EDUCATION - Enter new value in 'Institute' textbox at 'Add Education' form");
		myInfoPage.enterToTextboxByID(driver, "education_institute", eduInstitue);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 22: EDUCATION - Enter new value in 'Major/Specialization' textbox at 'Add Education' form");
		myInfoPage.enterToTextboxByID(driver, "education_major", eduMajor);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 23: EDUCATION - Enter new value in 'Year' textbox at 'Add Education' form");
		myInfoPage.enterToTextboxByID(driver, "education_year", eduYear);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 24: EDUCATION - Enter new value in 'GPA/Score' textbox at 'Add Education' form");
		myInfoPage.enterToTextboxByID(driver, "education_gpa", eduGPA);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 25: EDUCATION - Pick new value from 'Start Date' datepicker at 'Add Education' form");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "education_start_date", eduStartDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 26: EDUCATION - Pick new value from 'End Date' datepicker at 'Add Education' form");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "education_end_date", eduEndDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 27: EDUCATION - Click on 'Save' button at 'Add Education' form");
		myInfoPage.clickToButtonByID(driver, "btnEducationSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 28: EDUCATION - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 29: EDUCATION - Print out all values at all rows");
		myInfoPage.getAllValuesOfEachRowInTable(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 30 : EDUCATION - Verify 'Education' infomation displayed at 'Result Table' ");
		verifyEquals(
				employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionEducation", "Level", "1"),
				eduCode);
		verifyEquals(
				employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionEducation", "Year", "1"),
				eduYear);
		verifyEquals(employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionEducation",
				"GPA/Score", "1"), eduGPA);

		// Skills
		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 31: SKILLS - Click on 'Add' button at 'Skills' form");
		myInfoPage.clickToButtonByID(driver, "addSkill");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 32: SKILLS - Verify all fields at 'Skills' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "skill_code"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "skill_years_of_exp"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "skill_comments"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 33: SKILLS - Print out all values in 'Skill' dropdown list at 'Add Skill' form");
		myInfoPage.getAllValuesInDropdownByID(driver, "skill_code");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 34: SKILLS - Select new value 'Skill' dropdown list at 'Add Skill' form");
		myInfoPage.selectItemInDropdownByID(driver, "skill_code", skillCode);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 35: SKILLS - Enter new value in 'Years of Experience' textbox at 'Add Skill' form");
		myInfoPage.enterToTextboxByID(driver, "skill_years_of_exp", yearsOfExp);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 36: SKILLS - Click on 'Save' button at 'Add Skill' form");
		myInfoPage.clickToButtonByID(driver, "btnSkillSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 37: SKILLS - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 38 : SKILLS - Verify 'Skills' infomation displayed at 'Result Table' ");
		verifyEquals(employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionSkill", "Skill", "1"),
				skillCode);
		verifyEquals(employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionEducation",
				"Years of Experience", "1"), yearsOfExp);

		// Languages
		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 39: LANGUAGES - Click on 'Add' button at 'Languages' form");
		myInfoPage.clickToButtonByID(driver, "addLanguage");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 40: LANGUAGES - Verify all fields at 'Languages' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "language_code"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "language_code"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "language_competency"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "language_comments"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 41: LANGUAGES - Print out all values in 'Language' dropdown list at 'Add Language' form");
		myInfoPage.getAllValuesInDropdownByID(driver, "language_code");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 42: LANGUAGES - Select new value in 'Language' dropdown list at 'Add Language' form");
		myInfoPage.selectItemInDropdownByID(driver, "language_code", languageCode);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 43: LANGUAGES - Print out all values in 'Fluency' dropdown list at 'Add Language' form");
		myInfoPage.getAllValuesInDropdownByID(driver, "language_lang_type");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 44: LANGUAGES - Select new value 'Fluency' dropdown list at 'Add Language' form");
		myInfoPage.selectItemInDropdownByID(driver, "language_lang_type", fluency);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 45: LANGUAGES - Print out all values in 'Competency' dropdown list at 'Add Language' form");
		myInfoPage.getAllValuesInDropdownByID(driver, "language_competency");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 46: LANGUAGES - Select new value 'Competency' dropdown list at 'Add Language' form");
		myInfoPage.selectItemInDropdownByID(driver, "language_competency", languageCompetency);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 47: LANGUAGES - Click on 'Save' button at 'Add Language' form");
		myInfoPage.clickToButtonByID(driver, "btnLanguageSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 48: LANGUAGES - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 49 : LANGUAGES - Verify 'Languages' infomation displayed at 'Result Table' ");
		verifyEquals(
				employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "lang_data_table", "Language", "1"),
				languageCode);
		verifyEquals(
				employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "lang_data_table", "Fluency", "1"),
				fluency);
		verifyEquals(
				employeeListPage.getValueInTableIDAtColumnNameAndRowIndex(driver, "lang_data_table", "Competency", "1"),
				languageCompetency);

		// License
		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 50: LICENSE - Click on 'Add' button at 'License' form");
		myInfoPage.clickToButtonByID(driver, "addLicense");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 51: LICENSE - Verify all fields at 'License' form are Enabled");
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "license_code"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "license_license_no"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "license_date"));
		verifyTrue(myInfoPage.isFieldEnabledByID(driver, "license_renewal_date"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 52: LICENSE - Print out all values in 'License Type' dropdown list at 'Add License' form");
		myInfoPage.getAllValuesInDropdownByID(driver, "license_code");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 53: LICENSE - Select new value in 'License Tyope' dropdown list at 'Add License' form");
		myInfoPage.selectItemInDropdownByID(driver, "license_code", licenseCode);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 54: LICENSE - Enter new value in 'License Number' textbox at 'Add License' form");
		myInfoPage.enterToTextboxByID(driver, "license_license_no", licenseNumber);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 55: LICENSE - Enter new value in 'Issued Date' textbox at 'Add License' form");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "license_date", licenseDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 56: LICENSE - Enter new value in 'Expiry Date' textbox at 'Add License' form");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "license_renewal_date", licenseExpiryDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 57: LICENSE - Click on 'Save' button at 'Add License' form");
		myInfoPage.clickToButtonByID(driver, "btnLicenseSave");

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 58: LICENSE - Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Qualifications_11 - Step 59 : LICENSE - Verify 'License' infomation displayed at 'Result Table' ");
		verifyEquals(employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionLicense",
				"License Type", "1"), licenseCode);
		verifyEquals(employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionLicense",
				"Issued Date", "1"), licenseDate);
		verifyEquals(employeeListPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "actionLicense",
				"Expiry Date", "1"), licenseExpiryDate);

	}

	@Test
	public void Employee_12_Memberships(Method method) {
		ExtentTestManager.startTest(method.getName(), "MEMBERSHIPS");

		ExtentTestManager.getTest().log(Status.INFO, "Memberships_12 - Step 01: Open 'Memberships' Tab at Side Bar");
		myInfoPage.openTabAtSideBarByName("Memberships");

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 02: Click on 'Add' button at 'Assigned Memberships' form");
		myInfoPage.clickToButtonByID(driver, "btnAddMembershipDetail");

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 03: Verify all fields at 'Add Membership' are Enabled");
		myInfoPage.isFieldEnabledByID(driver, "membership_membership");
		myInfoPage.isFieldEnabledByID(driver, "membership_subscriptionPaidBy");
		myInfoPage.isFieldEnabledByID(driver, "membership_subscriptionAmount");
		myInfoPage.isFieldEnabledByID(driver, "membership_currency");
		myInfoPage.isFieldEnabledByID(driver, "membership_subscriptionCommenceDate");
		myInfoPage.isFieldEnabledByID(driver, "membership_subscriptionRenewalDate");

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 04: Print out all values in 'Membership' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "membership_membership");

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 05: Select new  value in 'Membership' dropdown list");
		myInfoPage.selectItemInDropdownByID(driver, "membership_membership", membership);

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 06: Print out all values in 'Subscription Paid By' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "membership_subscriptionPaidBy");

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 07: Select new  value in 'Subscription Paid By' dropdown list");
		myInfoPage.selectItemInDropdownByID(driver, "membership_subscriptionPaidBy", subscriptionPaidBy);

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 08: Enter new value  in 'Subscription Amount' textbox");
		myInfoPage.enterToTextboxByID(driver, "membership_subscriptionAmount", subscriptionAmount);

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 09: Print out all values in 'Currency' dropdown list");
		myInfoPage.getAllValuesInDropdownByID(driver, "membership_currency");

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 10: Select new  value in 'Currency' dropdown list");
		myInfoPage.selectItemInDropdownByID(driver, "membership_currency", currency);

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 11: Select new  value in 'Subscription Commence Date' from DatePicker");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "membership_subscriptionCommenceDate", subComDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 12: Select new  value in 'Subscription Renewal Date' from DatePicker");
		myInfoPage.sendKeysToDatePickerTextboxByID(driver, "membership_subscriptionRenewalDate", subReDate);

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 13: Click on 'Save' button at 'Add Membership' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveMembership");

		ExtentTestManager.getTest().log(Status.INFO, "Memberships_12 - Step 14: Verify Success Message is Displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManager.getTest().log(Status.INFO,
				"Memberships_12 - Step 15: Verify 'Assigned Memberships' infomation displayed at 'Result Table' ");
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Membership", "1"),
				membership);
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions",
				"Subscription Paid By", "1"), subscriptionPaidBy);
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions",
				"Subscription Amount", "1"), subscriptionAmount);
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions", "Currency", "1"),
				currency);
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions",
				"Subscription Commence Date", "1"), subComDate);
		verifyEquals(myInfoPage.getValueInTableByHTMLAtColumnNameAndRowIndex(driver, "listActions",
				"Subscription Renewal Date", "1"), subReDate);

	}

	// @Test
	public void Employee_13_Search_Employee(Method method) {
		ExtentTestManager.startTest(method.getName(), "SEARCH");

	}

	@Parameters({ "browser" })
	@AfterClass()
	public void cleanBrowser() {
		ExtentTestManager.getTest().log(Status.INFO, "►►►►►►►►►► Close Browsers and Drivers ►►►►►►►►►►");
		cleanDriverInstance();
	}

	WebDriver driver;
	LoginPO loginPage;
	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	MyInfoPO myInfoPage;
	EmployeeListPO employeeListPage;
	DataUtil fakeData;

}
