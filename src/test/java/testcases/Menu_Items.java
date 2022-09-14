package testcases;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobject.*;

public class Menu_Items extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewsPageObject newsPage;
    AboutUsPageObject aboutUsPage;
    CustomerServicePageObject customerServicePage;
    CareerPageObject careerPageObject;
    FinancePageObject financePageObject;

    @BeforeMethod
    public void initPage()
    {
        driver = getBrowserDriver("CHROME_UI","https://www.sytner.co.uk/");
        log.info("start website");
        homePage = PageGeneratorManager.getHomePage(driver);


    }
    @Test
    public void TC_01_Verify_About_Us_Page()
    {
        log.info("click to accept all cookies button");
        homePage.clickToAcceptAllCookiesButton();

        aboutUsPage = homePage.clickToAboutUsLinkAtBottomOfPage();
        log.info("click to about us link");

        Assert.assertEquals(aboutUsPage.getAboutUsUrl(),"https://beta.sytner.co.uk/about-us");
        log.info("verify about us url");

        Assert.assertEquals(aboutUsPage.getTitle(),"About Us | Sytner Group");
        log.info("verify about us title");

    }
    //@Test
    public void TC_02_Verify_News_Page()
    {
        homePage.clickToAcceptAllCookiesButton();
        newsPage = homePage.clickToNewsLinkBottomOfPage();
        Assert.assertEquals(newsPage.getNewsUrl(),"https://www.sytner.co.uk/news");
        Assert.assertEquals(newsPage.getTitle(),"Latest News | About Us | Sytner Group");
    }
    @Test (enabled = false)
    public void TC_03_Verify_Customer_Service_Page()
    {
        homePage.clickToAcceptAllCookiesButton();
        customerServicePage = homePage.clickToCustomerServiceLinkBottomOfPage();
        Assert.assertEquals(customerServicePage.getNewsUrl(),"https://beta.sytner.co.uk/customer-service");
        Assert.assertEquals(customerServicePage.getTitle(),"Customer Service");
    }
    @Test (enabled = false)
    public void TC_04_Verify_Career_Page()
    {
        homePage.clickToAcceptAllCookiesButton();
        careerPageObject = homePage.clickToCareerLinkBottomOfPage();
        Assert.assertEquals(careerPageObject.getNewsUrl(),"https://jobs.sytner.co.uk/jobs/");
        Assert.assertEquals(careerPageObject.getTitle(),"Careers home | Sytner Group");
    }
    @Test (enabled = false)
    public void TC_05_Verify_Finance_Page()
    {
        homePage.clickToAcceptAllCookiesButton();
        financePageObject = homePage.clickToFinanceLinkBottomOfPage();
        Assert.assertEquals(financePageObject.getNewsUrl(),"https://beta.sytner.co.uk/finance");
        Assert.assertEquals(financePageObject.getTitle(),"Finance | Sytner Group");
    }
    @AfterMethod
    public void closeBrowser()
    {
        closeBrowserAndDriver(driver);
    }

}
