package testcases;

import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobject.HomePageObject;
import pageobject.PageGeneratorManager;

public class Menu_Items extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;

    @BeforeClass
    public void initPage()
    {
        driver = getBrowserDriver("CHROME_UI","https://www.sytner.co.uk/");
        homePage = PageGeneratorManager.getHomePage(driver);

    }
    @Test
    public void TC_01_Verify_About_Us_Page()
    {
        homePage.clickToAcceptAllCookiesButton();
        homePage.clickToAboutUsLinkAtBottomOfPage();
    }

}
