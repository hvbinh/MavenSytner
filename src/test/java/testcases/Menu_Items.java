package testcases;


import common.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


//@Listeners(common.reportconfigure.testNGListener.class)
public class Menu_Items extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    AboutUsPageObject aboutUsPage;
    CustomerServicePageObject customerServicePage;
    CareerPageObject careerPageObject;
    FinancePageObject financePageObject;


    @BeforeMethod
    @Parameters({"browser", "url"})
    public void initPage(String browser, String url) {
        driver = getBrowserDriver(browser, url);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void TC_01_Verify_About_Us_Page(Method method) {
        log.info("TC_01_Verify_About_Us_Page");
        log.info("Click to accept all cookies button");
        //homePage.clickToAcceptAllCookiesButton();

        aboutUsPage = homePage.clickToAboutUsLinkAtBottomOfPage();
        log.info("click to about us link");
        String url = aboutUsPage.getAboutUsUrl();

        Assert.assertEquals(aboutUsPage.getAboutUsUrl(), "https://www.sytner.co.uk/about-us");
        log.info("verify about us url");

        Assert.assertEquals(aboutUsPage.getTitle(), "About Sytner Group | Sytner Group");
        log.info("verify about us title");

    }

    //@Test
    public void TC_02_Verify_Customer_Service_Page(Method method) {
        homePage.clickToAcceptAllCookiesButton();
        log.info("TC_03_Verify_Customer_Service_Page");
        log.info( "Click to accept all cookies button");

        customerServicePage = homePage.clickToCustomerServiceLinkBottomOfPage();
        log.info( "click to News link");

        log.info( "verify Customer Service title");
        Assert.assertEquals(customerServicePage.getNewsUrl(), "https://www.sytner.co.uk/customer-service");
        Assert.assertEquals(customerServicePage.getTitle(), "Customer Service");
    }

    //@Test
    public void TC_04_Verify_Career_Page(Method method) {
        homePage.clickToAcceptAllCookiesButton();
        log.info( "TC_04_Verify_Career_Page");
        log.info( "Click to accept all cookies button");

        careerPageObject = homePage.clickToCareerLinkBottomOfPage();
        log.info( "Click to career link");

        log.info( "Verify career title");
        Assert.assertEquals(careerPageObject.getNewsUrl(), "https://jobs.sytner.co.uk/jobs/");
        Assert.assertEquals(careerPageObject.getTitle(), "Careers home | Sytner Group");
    }

    //@Test
    public void TC_05_Verify_Finance_Page(Method method) {
        homePage.clickToAcceptAllCookiesButton();
        log.info( "TC_05_Verify_Finance_Page");
        log.info( "Click to accept all cookies button");

        financePageObject = homePage.clickToFinanceLinkBottomOfPage();
        log.info( "Click to finance link");

        log.info( "Verify finance title");
        Assert.assertEquals(financePageObject.getNewsUrl(), "https://www.sytner.co.uk/finance");
        Assert.assertEquals(financePageObject.getTitle(), "Finance | Sytner Group");
    }



    public void takeScreenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String folder = System.getProperty("user.dir");
            File theDir = new File(folder + File.separator + "image");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());

            FileHandler.copy(source, new File(folder + File.separator + "image" + File.separator + date + ".png"));
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        closeBrowserAndDriver(driver);
    }

}
