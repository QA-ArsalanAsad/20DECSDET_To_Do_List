package com.qa.TDL.site;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class WebGymTest {

	private static RemoteWebDriver driver;
	private static ExtentReports report;
	private static ExtentTest test;
	private static WebElement targ;

	@BeforeAll
	public static void setUp() {

		report = new ExtentReports(
				"C:\\Users\\Arsalan\\Documents\\Downloads\\sts-4.9.0.RELEASE\\20DECSDET2-TDL\\target\\reports\\extent-report\\siteReport.html",
				true);

		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chrome/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());

	}

	@AfterAll
	public static void cleanUp() {
		driver.quit();
		report.flush();
		report.close();
	}

	@Test
	void testGymCreate() throws Exception {

		test = report.startTest("Test For gym create");
		test.log(LogStatus.INFO, "Gym created");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[1]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"gName\"]"));
		targ.sendKeys("TEST");
		targ = driver.findElement(By.xpath("//*[@id=\"gType\"]"));
		targ.sendKeys("TEST");
		targ = driver.findElement(By.xpath("//*[@id=\"createGymModal\"]/div/div/button[2]"));
		targ.click();

	}

	@Test
	void testGymReadById() throws Exception {

		test = report.startTest("Test For gym read by id");
		test.log(LogStatus.INFO, "Read id of gym");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[2]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"id\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"readGymModal\"]/div/div/button[3]"));
		targ.click();

		Thread.sleep(2000);

	}

	@Test
	void testGymUpdate() throws Exception {

		test = report.startTest("Test For gym update");
		test.log(LogStatus.INFO, "Gym updated");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[3]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"gymId\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"newName\"]"));
		targ.sendKeys("TEST");
		targ = driver.findElement(By.xpath("//*[@id=\"newType\"]"));
		targ.sendKeys("TEST1");
		targ = driver.findElement(By.xpath("//*[@id=\"updateGymModal\"]/div/div/button[2]"));
		targ.click();

		Thread.sleep(2000);

	}

	@Test
	void testGymDelete() throws Exception {

		test = report.startTest("Test For gym delete");
		test.log(LogStatus.INFO, "Gym deleted");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[4]"));
		targ.click();
		targ = driver.findElement(By.xpath("//*[@id=\"deleteId\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"deleteGymModal\"]/div/div/button[2]"));
		targ.click();

		Thread.sleep(2000);

	}

	@AfterEach
	public void afterTest() {
		report.endTest(test);
	}

	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);
		cOptions.addArguments("test-type");
		cOptions.addArguments("start-maximized");
		cOptions.addArguments("--js-flags=--expose-gc");
		cOptions.addArguments("--enable-precise-memory-info");
		cOptions.addArguments("--disable-default-apps");
		cOptions.addArguments("test-type=browser");
		cOptions.addArguments("disable-infobars");

		return cOptions;
	}

}
