package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageui.HomePageUI;

public class CustomerServicePageObject extends BasePage {
    WebDriver driver;

    public CustomerServicePageObject(WebDriver driver) {
        this.driver = driver;
    }


    public String getNewsUrl() {
        return getCurrentPageUrl(driver);
    }

    public String getTitle() {
        return getCurrentPageTitle(driver);
    }
}
