package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageui.HomePageUI;

public class AboutUsPageObject extends BasePage {
    WebDriver driver;

    public AboutUsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAcceptAllCookiesButton() {
        waitToElementClickable(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        clickToElement(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
    }

    public void clickToAboutUsLinkAtBottomOfPage() {
        scrollToElement(driver, HomePageUI.ABOUT_US_LINK);
        waitToElementClickable(driver, HomePageUI.ABOUT_US_LINK);
        clickToElement(driver, HomePageUI.ABOUT_US_LINK);
    }

    public String getAboutUsUrl() {
        sleepInSecond(5);
        return getCurrentPageUrl(driver);
    }

    public String getTitle() {
        return getCurrentPageTitle(driver);
    }




}
