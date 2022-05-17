package commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.hrm.DashboardPO;
import pageObjects.hrm.LoginPO;
import pageObjects.hrm.PageGenerator;
import pageUIs.hrm.BasePageUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	// Selenium Web Browser function
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();

	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}

	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// Alert
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	// Windows
	protected void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	// Selenium Web Element Function
	public By getByXpath(String xpathxpathLocator) {
		return By.xpath(xpathxpathLocator);
	}

	// Dynamic Locator
	private String getDynamicXpath(String xpathLocator, String... dynamicValues) {
		xpathLocator = String.format(xpathLocator, (Object[]) dynamicValues);
		return xpathLocator;
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return driver.findElements(By.xpath(getDynamicXpath(xpathLocator, dynamicValues)));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	public void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String itemText) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(itemText);
	}

	// selectItemInDefaultDropdown(driver,
	// BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
	protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String itemText,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByVisibleText(itemText);
	}

	/**
	 * Default Dropdown - Get Disabled Text then Verify
	 * 
	 * @param driver
	 * @param xpathLocator
	 * @param itemText
	 * @param dynamicValues
	 * @return
	 */
	protected String getSelectedItemByTextInDefaultDropdown(WebDriver driver, String xpathLocator, String itemText,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByVisibleText(itemText);
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName,
			String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText().trim();
	}

	public String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getText().trim();
	}

	protected String getElementCssvalue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).size();
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		try {
			return getWebElement(driver, xpathLocator).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible on UI");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));

	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// Actions
	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues))).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathLocator));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery  != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathLocator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return status;
	}

	// Wait
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathLocator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	// HRM - Menu
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		isJQueryAjaxLoadedSuccess(driver);
	}

	// Sub Menu
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);

		isJQueryAjaxLoadedSuccess(driver);
	}

	// Child Sub Menu
	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName,
			String childSubMenuPageName) {
		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverMouseToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);

		waitForElementClickable(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);

		isJQueryAjaxLoadedSuccess(driver);
	}

	// Button
	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);

	}

	// Enter Textbox
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxIDName);

	}

	/**
	 * [TEXTBOX] GET & VERIFY - Get Textbox's Value (Attribute) in Textbox ID
	 * Textbox -> Select -> Get Attribute ('value') of selected item -> Verify
	 * 
	 * @param driver
	 * @param textboxIDName
	 * @return attribute value
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxIDName);
		return getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, "value", textboxIDName);

	}

	/**
	 * [DROPDOWN] GET & VERIFY - Get Text Value (Text) in Dropdown by ID Dropdown ->
	 * Select -> Get Text of selected item -> Verify
	 * 
	 * @param driver
	 * @param dropdownID
	 * @param valueItem
	 * @return selected text in dropdown
	 */
	public String getSelectedValueInDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getSelectedItemDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, "value", dropdownID);

	}

	/**
	 * [DROPDOWN] GET & VERIFY - Get Text Value (Attribute) in Dropdown ('Disabled
	 * Dropdown') Dropdown -> Select -> Get Attribute ('value') of selected item ->
	 * Verify
	 * 
	 * @param driver
	 * @param dropdownID
	 * @return selected text in dropdown
	 */
	public String getSelectedValueInDropdownByAttribute(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getElementAttribute(driver, BasePageUI.DROPDOWN_BY_ID, "value", dropdownID);
	}

	/**
	 * Select item in Dropdown By ID
	 * 
	 * @param driver
	 * @param dropdownID
	 * @param valueItem
	 */
	public void selectItemInDropdownByID(WebDriver driver, String dropdownID, String valueItem) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, valueItem, dropdownID);
	}

	/**
	 * Get select item in Dropdown by ID
	 * 
	 * @param driver
	 * @param dropdownID
	 * @return
	 */
	public String getSelectedItemInDropdownByID(WebDriver driver, String dropdownID) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		return getSelectedItemDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
	}

	/**
	 * Click to Checkbox By Label Text
	 * 
	 * @author Yuna
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);

	}

	/**
	 * Click to Radio Button By Label Text
	 * 
	 * @author Yuna
	 * @param driver
	 * @param radioLabelName
	 */
	public void clickToRadioByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxOrRadio(driver, BasePageUI.RADIO_BY_LABEL, radioLabelName);

	}

	public boolean isRadioButtonSelectedByLable(WebDriver driver, String labelName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BY_LABEL, labelName);
		return isElementSelected(driver, BasePageUI.RADIO_BY_LABEL, labelName);
	}

	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName,
			String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex,
				String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex,
				String.valueOf(columnIndex));
	}

	public String getValueInTableOfQualicicationsAtColumnNameAndRowIndex(WebDriver driver, String tableID,
			String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_AT_QUALIFICATIONS_BY_ID, tableID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_AT_QUALIFICATIONS_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID,
				rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_AT_QUALIFICATIONS_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID,
				rowIndex, String.valueOf(columnIndex));
	}

	public DashboardPO loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageUI.USER_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.USER_LOGIN_TEXTBOX, userName);
		sendkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);

	}

	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);

		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);

	}

	public void uploadImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
	}

	public boolean isSuccessMessageDisplayed(WebDriver driver, String messageValue) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, messageValue);
		return isElementDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_VALUE, messageValue);
	}

	public boolean isFieldEnabledByID(WebDriver driver, String filedID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, filedID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, filedID);

	}

	/**
	 * Print out all values in one row in a table
	 * 
	 * @param driver
	 * @return All values of first row in table
	 */
	public List<String> getAllValuesOfEachRowInTable(WebDriver driver) {
		List<String> allRowValues = new ArrayList<String>();
		List<WebElement> allRowsElement = getListWebElement(driver, BasePageUI.ALL_ROWS_EACH_PAGE);
		for (WebElement eachRow : allRowsElement) {
			allRowValues.add(eachRow.getText());
		}
		// In tất cả giá trị row ra
		for (String value : allRowValues) {
			System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
			System.out.println(value);
		}
		return allRowValues;

	}

	/**
	 * HRM_ORANGE - BLOODTYPE - Print out all values in dropdown list/textbox
	 * 
	 * @param driver
	 * @param dropdownByName
	 * @param dynamicValues
	 * @return All values of dropdown textbox
	 */
	public boolean getAllValuesInDropdownListByName(WebDriver driver, String dropdownByName, String... dynamicValues) {
		waitForElementClickable(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, dropdownByName);
		clickToElement(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, dropdownByName);

		List<WebElement> nameElements = getListWebElement(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, dropdownByName);

		List<String> nameValues = new ArrayList<String>();

		for (WebElement name : nameElements) {
			nameValues.add(name.getText());
		}
		List<String> nameValuesClone = new ArrayList<String>();
		for (String profileList : nameValues) {
			nameValuesClone.add(profileList);
		}
		System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
		for (String profileList : nameValuesClone) {
			System.out.println(profileList);
		}

		return nameValues.equals(nameValuesClone);

	}

	/**
	 * HRM_ORANGE - BLOODTYPE - Select one item in dropdown list/textbox
	 * 
	 * @param driver
	 * @param dropdownByID
	 * @param dynamicValues
	 */
	public void selectItemInDropdownByName(WebDriver driver, String dropdownByName, String valueItem) {
		waitForElementClickable(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, dropdownByName);
		selectItemInDefaultDropdown(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, valueItem, dropdownByName);
	}

	/**
	 * HRM_ORANGE - BLOODTYPE - Select Disabled Text in dropdown list/textbox
	 * 
	 * @param driver
	 * @param dropdownByName
	 * @param disabledText
	 * @param dynamicValues
	 * @return
	 */
	public String getDisabledTextInBloodTypeDropdownByName(WebDriver driver, String dropdownByName, String disabledText,
			String... dynamicValues) {
		waitForElementVisible(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, dropdownByName);
		return getSelectedItemByTextInDefaultDropdown(driver, BasePageUI.BLOOD_TYPE_DROPDOWN, disabledText,
				dropdownByName);
	}

	public boolean getAllValuesInDropdownByID(WebDriver driver, String dropdownByID, String... dynamicValues) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownByID);
		clickToElement(driver, BasePageUI.DROPDOWN_BY_ID, dropdownByID);

		List<WebElement> nameElements = getListWebElement(driver, BasePageUI.DROPDOWN_BY_ID, dropdownByID);

		List<String> nameValues = new ArrayList<String>();

		for (WebElement name : nameElements) {
			nameValues.add(name.getText());
		}
		List<String> nameValuesClone = new ArrayList<String>();
		for (String profileList : nameValues) {
			nameValuesClone.add(profileList);
		}
		System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
		for (String profileList : nameValuesClone) {
			System.out.println(profileList);
		}

		return nameValues.equals(nameValuesClone);

	}

	public void clickOnElementByTextInTableByID(WebDriver driver, String tableID, String elementByText) {
		waitForElementClickable(driver, BasePageUI.ELEMENT_BY_TEXT_IN_TABLE_BY_ID, tableID, elementByText);
		clickToElement(driver, BasePageUI.ELEMENT_BY_TEXT_IN_TABLE_BY_ID, tableID, elementByText);
	}

	public long longTimeout = GlobalConstants.LONG_TIMEOUT;

}
