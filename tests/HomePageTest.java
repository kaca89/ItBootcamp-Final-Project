package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import object.BuyDress;
import object.SummerDresses;

public class HomePageTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\PC\\Desktop\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void SummerDressesPageURL1() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(SummerDresses.HOME_URL);

		SummerDresses.clickWmnBtn(driver);
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://automationpractice.com/index.php?id_category=11&controller=category";
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	@Test
	public void SummerDressesPageURL2() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(SummerDresses.HOME_URL);

		SummerDresses.clickDrsBtn(driver);
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://automationpractice.com/index.php?id_category=11&controller=category";
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	@Test
	public void leadSameURL() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(SummerDresses.HOME_URL);
		String currentw = SummerDresses.clickWmnBtn(driver);
		driver.get(SummerDresses.HOME_URL);
		String currentd = SummerDresses.clickDrsBtn(driver);

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(currentw, currentd);

	}

	@AfterClass
	public void closeChrome() {
		driver.close();
	}

}
