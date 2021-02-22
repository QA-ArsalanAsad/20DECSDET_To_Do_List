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

public class WebEquipmentTest {
	private static RemoteWebDriver driver;
	private static ExtentReports report;
	private static ExtentTest test;
	private static WebElement targ;

	@BeforeAll
	public static void setUp() {

		report = new ExtentReports(
				"C:\\Users\\Arsalan\\Documents\\Downloads\\sts-4.9.0.RELEASE\\20DECSDET2-TDL\\target\\reports\\extent-report\\siteReport2.html",
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
	void testEquipmentCreate() throws Exception {
		test = report.startTest("Test For equipment create");
		test.log(LogStatus.INFO, "Equipment created");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[5]"));
		targ.click();
		Thread.sleep(2000);
		targ = driver.findElement(By.xpath("//*[@id=\"ePrice\"]"));
		targ.sendKeys("30");
		targ = driver.findElement(By.xpath("//*[@id=\"eType\"]"));
		targ.sendKeys("Dumbbells");
		targ = driver.findElement(By.xpath("//*[@id=\"newGymId\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"createEquipmentModal\"]/div/div/button[2]"));
		targ.click();

		Thread.sleep(2000);

	}

	@Test
	void testEquipmentReadById() throws Exception {

		test = report.startTest("Test For equipment ReadById");
		test.log(LogStatus.INFO, "Equipment read");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[6]"));
		targ.click();
		Thread.sleep(2000);
		targ = driver.findElement(By.xpath("//*[@id=\"eId\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"readEquipmentModal\"]/div/div/button[3]"));
		targ.click();

		Thread.sleep(2000);

	}

	@Test
	void testEquipmentUpdate() throws Exception {

		test = report.startTest("Test For equipment update");
		test.log(LogStatus.INFO, "Equipment updated");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[7]"));
		targ.click();
		Thread.sleep(2000);
		targ = driver.findElement(By.xpath("//*[@id=\"updateEId\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"newEType\"]"));
		targ.sendKeys("Dumbbells");
		targ = driver.findElement(By.xpath("//*[@id=\"newPrice\"]"));
		targ.sendKeys("40");
		targ = driver.findElement(By.xpath("//*[@id=\"updateEquipmentModal\"]/div/div/button[2]"));
		targ.click();

		Thread.sleep(2000);
	}

	@Test
	public void testEquipmentDelete() throws Exception {

		test = report.startTest("Test For equipment delete");
		test.log(LogStatus.INFO, "Equipment deleted");
		driver.get("http://127.0.0.1:5500/DECSDET2_TDL/");

		targ = driver.findElement(By.xpath("/html/body/button[8]"));
		targ.click();
		Thread.sleep(2000);
		targ = driver.findElement(By.xpath("//*[@id=\"dEId\"]"));
		targ.sendKeys("1");
		targ = driver.findElement(By.xpath("//*[@id=\"deleteEquipmentModal\"]/div/div/button[2]"));
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
