package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import excel_methods.ExcelUsers;
import object.BuyDress;
import object.Registration;
import object.SummerDresses;

public class BuyRegistrationTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\PC\\Desktop\\chromedriver_win32 (3)\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void BuyDress() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(SummerDresses.HOME_URL);

		SummerDresses.clickWmnBtn(driver);
		BuyDress.addToCart(driver);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://automationpractice.com/index.php?id_product=5&controller=product#/color-blue/size-m";
		Assert.assertEquals(actualUrl, expectedUrl);

	}

	// registration after adding products in chart

	@Test
	public void getRegistration1() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		BuyDress.proceedToCheckOut(driver);
		Registration.clickEmail1(driver, "jkd@gmail.com");
		Registration.addName(driver, "kaca");
		Registration.addLastName(driver, "jevtic");
		Registration.addPass(driver, "kaca123");
		Registration.addAddress(driver, "Topolska");
		Registration.addCity(driver, "Belgrade");
		Registration.addZip(driver, "11000");
		Registration.addMob(driver, "14257416224");
		Registration.addAddress2(driver, "Milesevska");
		Registration.addState(driver, "Serbia");
		Registration.regBtn(driver);
		Registration.signOut(driver);
		Registration.signIn1(driver);
		Registration.email2(driver, "jkd@gmail.com");
		Registration.addPass(driver, "kaca123");
		Registration.signIn2(driver);
		Registration.signOut(driver);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualUrl, expectedUrl);

	}

	// registration of 30 users from the excel table

	@Test
	public void getRegistration30() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		SoftAssert sa = new SoftAssert();
		ExcelUsers.setExcel();
		ExcelUsers.setWorkSheet(0);

		for (int i = 1; i <= 30; i++) {

			driver.navigate().to("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			Registration.clickEmail1(driver, ExcelUsers.getCellData(i, 0));
			Registration.addName(driver, ExcelUsers.getCellData(i, 1));
			Registration.addLastName(driver, ExcelUsers.getCellData(i, 2));
			Registration.addPass(driver, ExcelUsers.getCellData(i, 3));
			Registration.addAddress(driver, ExcelUsers.getCellData(i, 4));
			Registration.addCity(driver, ExcelUsers.getCellData(i, 5));
			Registration.addZip(driver, ExcelUsers.getCellData(i, 6));
			Registration.addMob(driver, ExcelUsers.getCellData(i, 7));
			Registration.addAddress2(driver, ExcelUsers.getCellData(i, 8));
			Registration.addState(driver, ExcelUsers.getCellData(i, 9));
			Registration.regBtn(driver);
			Registration.signOut(driver);
			Registration.signIn1(driver);
			Registration.email2(driver, ExcelUsers.getCellData(i, 0));
			Registration.addPass(driver, ExcelUsers.getCellData(i, 3));
			Registration.signIn2(driver);
			boolean SignOut = driver.findElement(By.xpath("//a[@class='logout']")).isDisplayed();
			sa.assertEquals(SignOut, true, "error");

			sa.assertAll();
		}

	}

}
