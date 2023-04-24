package pageobject;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageui.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToAcceptAllCookiesButton() {
        List <WebElement> elements = getElements(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        if(elements.size()!=0)
        {
            //waitToElementClickable(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
            clickToElementByJS(driver, HomePageUI.ACCEPT_ALL_COOKIES_BUTTON);
        }
        else
        {
            System.out.println("Cookies button is not displayed");
        }

    }

    public AboutUsPageObject clickToAboutUsLinkAtBottomOfPage() {
        if(driver.toString().contains("Safari"))
        {
            waitToElementClickable(driver, HomePageUI.ABOUT_US_LINK);
            clickToElementByJS(driver, HomePageUI.ABOUT_US_LINK);
        }
        else
        {
            waitToElementClickable(driver, HomePageUI.ABOUT_US_LINK);
            scrollToElement(driver, HomePageUI.ABOUT_US_LINK);
            clickToElementByJS(driver, HomePageUI.ABOUT_US_LINK);
        }
        return PageGeneratorManager.getAboutUsPage(driver);
    }

    public String getAboutUsUrl() {
        return getCurrentPageUrl(driver);
    }

    public String getTitle() {
        return getCurrentPageTitle(driver);
    }

    public NewsPageObject clickToNewsLinkBottomOfPage() {
        waitToElementVisible(driver, HomePageUI.NEWS_LINK);
        scrollToElement(driver, HomePageUI.NEWS_LINK);
        clickToElement(driver, HomePageUI.NEWS_LINK);
        return PageGeneratorManager.getNewsPage(driver);
    }


    public CustomerServicePageObject clickToCustomerServiceLinkBottomOfPage() {
        waitToElementVisible(driver, HomePageUI.CUSTOMER_SERVICE_LINK);
        scrollToElement(driver, HomePageUI.CUSTOMER_SERVICE_LINK);
        clickToElement(driver, HomePageUI.CUSTOMER_SERVICE_LINK);
        return PageGeneratorManager.getCustomerServicePage(driver);
    }

    public CareerPageObject clickToCareerLinkBottomOfPage() {
        waitToElementVisible(driver, HomePageUI.CAREER_LINK);
        scrollToElement(driver, HomePageUI.CAREER_LINK);
        clickToElement(driver, HomePageUI.CAREER_LINK);
        return PageGeneratorManager.getCareerPage(driver);
    }

    public FinancePageObject clickToFinanceLinkBottomOfPage() {
        waitToElementVisible(driver, HomePageUI.FINANCE_LINK);
        scrollToElement(driver, HomePageUI.FINANCE_LINK);
        clickToElement(driver, HomePageUI.FINANCE_LINK);
        return PageGeneratorManager.getFinacePage(driver);
    }
}
