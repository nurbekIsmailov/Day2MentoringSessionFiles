package log4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PorscheConfigurator {

	WebDriver driver;

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

	}

	@Test
	public void PorscheConfiguratorTest() throws InterruptedException {

		String url = "https://www.porsche.com/usa/";
		driver.get(url);

		// driver.findElement(By.xpath(".//*[@id='m-01-car-configurator-button']/a/span")).click();

		String winHandleBefore = driver.getWindowHandle();

		System.out.println("--------------------------------------" + driver.getTitle());

		// Perform the click operation that opens new window
		driver.findElement(By.linkText("Build your Porsche")).click();

		// ("Switch to new window opened");

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		System.out.println("--------------------------------------" + driver.getTitle());

		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(.,'911')]")).click();

		Thread.sleep(1000);
		System.out.println("--------------------------------------" + driver.getTitle());

		// ("click on 911 GT3 build link");
		driver.findElement(By.xpath(".//*[@id='m991810y2018']/div/div/a/span")).click();

		// ("Switch to new window opened");
		winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		// Setting a variable for a sleepy time
		int x = 300;
		// Clicking around with colors

		List<WebElement> allClorOptions = driver.findElements(By.xpath(".//*[@class='tileColor_1']"));
		for (WebElement color : allClorOptions) {
			color.click(); // Click each color
			Thread.sleep(500);
		}

		// Picking Fire Orange Colour
		driver.findElement(By.xpath("//li[@id='s_exterieur_x_FH2']/span")).click();

		Thread.sleep(x);
		// Clicking on options menu
		driver.findElement(By.xpath("//a[contains(.,'3. Options')]")).click();

		Thread.sleep(x);
		// Selecting 2 exterior options
		driver.findElement(By.id("IEX_subHdl")).click();
		Thread.sleep(x);
		driver.findElement(By.id("vs_table_IEX_x_M498_x_c01_M498")).click();
		Thread.sleep(x);
		driver.findElement(By.id("vs_table_IEX_x_M602_x_c41_M602")).click();
		Thread.sleep(x);
		driver.findElement(By.id("IEX_subHdl")).click();
		Thread.sleep(x);
		// Selecting 1 PERFORMANCE options
		driver.findElement(By.id("IMG_subHdl")).click();
		Thread.sleep(x);
		driver.findElement(By.id("vs_table_IMG_x_M474_x_c21_M474")).click();
		Thread.sleep(x);

		Thread.sleep(x);
		driver.findElement(By.id("IAU_subHdl")).click();
		Thread.sleep(x);

		driver.findElement(By.id("navigation_main_x_mainsuboffer_x_myPorsche")).click();

		Thread.sleep(2000);
		// Checking total price and making sure it matches what we have
		String totalPrice = driver.findElement(By.xpath(".//*[@id='s_price']/div[1]/div[4]/div[2]")).getText();
		System.out.println("Total Price for Build is " + totalPrice);

		Assert.assertTrue(totalPrice.equals("$153,750"), "Build price DOES NOT MATCH");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}