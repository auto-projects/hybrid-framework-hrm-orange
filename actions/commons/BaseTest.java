package commons;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReportFileInFolder();
		deleteReportNGScreenshotsFolder();

	}

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	private enum BROWSER {
		CHROME, FIREFOX, IE, SAFARI, EDGE_LEGACY, EDGE_CHROMIUM, H_CHROME, H_FIREFOX;
	}

	private enum ENVIRONMENT {
		PRODUCTION, STAGING, DEV, TESTING;
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if (browser == BROWSER.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.CHROME) {
			WebDriverManager.chromedriver().setup();
			
//			ChromeOptions options = new ChromeOptions();
//			options.addExtensions(new File(GlobalConstants.PROJECT_PATH + "\\browserExtensions\\UltraSuft"));
//			
			driver = new ChromeDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.IE) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.SAFARI) {
			driver = new SafariDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else {
			throw new RuntimeException("PLEASE ENTER A CORRECT BROWSER NAME!!!");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.PORTAL_PAGE_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
		if (browser == BROWSER.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();

			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH
					+ File.separator + "browserConsoleLogs" + File.separator + "Firefox.log");

			driver = new FirefoxDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.CHROME) {
			WebDriverManager.chromedriver().setup();
			
//			ChromeOptions options = new ChromeOptions();
//			options.addExtensions(new File(GlobalConstants.PROJECT_PATH + "\\browserExtensions\\UltraSuft"));
//			
			driver = new ChromeDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.EDGE_CHROMIUM) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.IE) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.SAFARI) {
			driver = new SafariDriver();
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else if (browser == BROWSER.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
			System.out.println("Driver init at Base Test = " + driver.toString());
		} else {
			throw new RuntimeException("PLEASE ENTER A CORRECT BROWSER NAME!!!");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appUrl);
//		driver.get(getEnvironmentValue(appUrl));
		driver.manage().window().maximize();
		return driver;
	}

	private String getEnvironmentValue(String environmentName) {
		String envUrl = null;
		ENVIRONMENT environment = ENVIRONMENT.valueOf(environmentName.toUpperCase());
		if (environment == ENVIRONMENT.PRODUCTION) {
			envUrl = "https://";
		} else if (environment == ENVIRONMENT.STAGING) {
			envUrl = "https://";
		} else if (environment == ENVIRONMENT.DEV) {
			envUrl = "https://";
		} else if (environment == ENVIRONMENT.TESTING) {
			envUrl = "https://";
		}
		System.out.println(envUrl);
		return envUrl;
	}

	public WebDriver getDriverInstance() {
		return this.driver;

	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lá»—i vÃ o ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public void deleteAllureReportFileInFolder() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public void deleteReportNGScreenshotsFolder() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/reportNGScreenshots";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void cleanDriverInstance() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}
			System.out.println("Driver Instance = " + driver.toString());

			// Browser
			if (driver != null) {
				// IE browser
				driver.manage().deleteAllCookies();
				driver.quit();
				System.out.println("Close Driver Instance");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
				System.out.println("Run Command Line ");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				System.out.println("►►►►►►►►►►►►►►► " + logging.getLevel().toString() + " ►►►►►►►►►►►►►►► \n"
						+ logging.getMessage());
			}
		}
	}
}
