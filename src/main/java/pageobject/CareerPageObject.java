package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;

public class CareerPageObject extends BasePage {
    WebDriver driver;

    public CareerPageObject(WebDriver driver) {
        this.driver = driver;
    }


    public String getNewsUrl() {
        sleepInSecond(1);
        return getCurrentPageUrl(driver);
    }

    public String getTitle() {
        return getCurrentPageTitle(driver);
    }
}
