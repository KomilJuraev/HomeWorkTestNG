package com.prestashop;

import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomeWork {

	WebDriver driver;

	@BeforeClass
	public void setUP() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	// @AfterClass
	// public void theEND() throws InterruptedException {
	// Thread.sleep(1500);
	// driver.close();
	// }
	@Test(priority = 1)
	public void wrongCredentialsTest() {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.name("email")).sendKeys("Komil1994@mail.ru");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123456789", Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		assertTrue(element.isDisplayed());
		String actual = element.getText();
		String expected = "Authentication failed.";
		System.out.println(actual);
		assertEquals(actual, expected);
	}

	@Test(priority = 2)
	public void invalidEmailTest() {
		driver.navigate().back();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("HaydarLiLiLiLi");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123456789", Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		assertTrue(element.isDisplayed());
		String actual = element.getText();
		String expected = "Invalid email address.";
		System.out.println(actual);
		assertEquals(actual, expected);
	}

	@Test(priority = 3)
	public void blankEmailTest() {
		driver.navigate().back();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("123456789", Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		assertTrue(element.isDisplayed());
		String actual = element.getText();
		System.out.println(actual);
		String expected = "An email address required.";
		assertEquals(actual, expected);
	}

	@Test(priority = 4)
	public void blankPasswordTest() {
		driver.navigate().back();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("Komil1994@mail.ru");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		assertTrue(element.isDisplayed());
		String actual = element.getText();
		String expected = "Password is required.";
		System.out.println(actual);
		assertEquals(actual, expected);
	}

	@Test(priority = 5)
	public void loginTest() throws InterruptedException {
		driver.navigate().back();

		Faker faker = new Faker();

		String email = faker.internet().emailAddress();
		driver.findElement(By.xpath("//input[@class='is_required validate account_input form-control']"))
				.sendKeys(email, Keys.ENTER);
		Thread.sleep(1500);
		driver.findElement(By.id("uniform-id_gender1")).click();
		Thread.sleep(1500);
		String firstName = faker.name().firstName();
		driver.findElement(By.name("customer_firstname")).sendKeys(firstName);
		Thread.sleep(1500);
		String lastName = faker.name().lastName();
		driver.findElement(By.name("customer_lastname")).sendKeys(lastName);
		Thread.sleep(1500);
		String password = faker.internet().password();
		driver.findElement(By.id("passwd")).sendKeys(password);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[@id='days']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[@id='days']/option[6]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[@id='months']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[@id='months']/option[7]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[@id='years']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select[@id='years']/option[30]")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("uniform-newsletter")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("uniform-optin")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("company")).sendKeys("AppleTechNologies");
		Thread.sleep(1500);
		// driver.findElement(By.id("lastname")).sendKeys(lastName);
		Thread.sleep(1500);
		String streetAddress = faker.address().streetAddress();
		driver.findElement(By.name("address1")).sendKeys(streetAddress);
		Thread.sleep(1500);
		String city = faker.address().city();
		driver.findElement(By.name("city")).sendKeys(city);
		Thread.sleep(1500);
		String state = faker.address().state();
		driver.findElement(By.name("id_state")).sendKeys(state);
		Thread.sleep(1500);
		String zipCode = faker.address().zipCode().substring(0, 5);
		driver.findElement(By.id("postcode")).sendKeys(zipCode);
		Thread.sleep(1500);
		String phoneNum = faker.phoneNumber().cellPhone();
		driver.findElement(By.id("phone_mobile")).sendKeys(phoneNum);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@name='submitAccount']/span[1]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[@class='logout']")).click();
		Thread.sleep(1500);
		driver.navigate().back();
		driver.findElement(By.id("email")).sendKeys(email);
		System.out.println(email);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
		System.out.println(password);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@id='SubmitLogin']/span")).click();

		String expected = driver.findElement(By.xpath("  //div[@class='header_user_info']/a/span")).getText();
		String actual = firstName + " " + lastName;
		assertEquals(expected, actual);
	}

}
