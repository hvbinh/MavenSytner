package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageui.HomePageUI;

public class FinancePageObject extends BasePage {
    WebDriver driver;

    public FinancePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAcceptAllCookiesButton() {
        waitToElementClickable(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        clickToElement(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
    }


    public String getNewsUrl() {
        return getCurrentPageUrl(driver);
    }

    public String getTitle() {
        return getCurrentPageTitle(driver);
    }
}
