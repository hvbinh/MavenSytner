package testcases;

import common.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Listeners(common.reportconfigure.testNGListener.class)
public class testNGDataProvider {
    WebDriver driver;


    @Test(dataProvider="emailPassword")
    public void Tc_01_Register(String email1, String password) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //data
        String firstName, lastName, email, companyName, pass, confirmPass;
        firstName="Tony";
        lastName="Buoi Sang";
        email="tonybuoisang"+getRandomNumber()+"@gmail.com";
        companyName="Tony Buoi Sang company";
        pass="123456";
        confirmPass="123456";

        Select selectDay, selectMonth, selectYear;

        driver.findElement(By.xpath("//a[text()='Register']")).click();

        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByVisibleText("10");

        selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectMonth.selectByVisibleText("February");

        selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByVisibleText("1990");

        driver.findElement(By.id("Email")).sendKeys(email1);
        driver.findElement(By.id("Company")).sendKeys(companyName);

        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPass);

        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");



    }
    @DataProvider(name = "emailPassword")
    public Object[][] emailPasswordList()
    {
        return new Object[][] {
                {"tonybuoisang"+getRandomNumber()+"@gmail.com","123456"},
                {"tonybuoisang"+getRandomNumber()+"@gmail.com","123456"},
                {"tonybuoisang"+getRandomNumber()+"@gmail.com","123456"},
        };
    }

    public int getRandomNumber()
    {
        Random random = new Random();
        return random.nextInt(9999);
    }
    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}
