package pageobject;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static HomePageObject getHomePage(WebDriver driver)
    {
        return new HomePageObject(driver);
    }
    public static NewsPageObject getNewsPage(WebDriver driver)
    {
        return new NewsPageObject(driver);
    }
    public static AboutUsPageObject getAboutUsPage(WebDriver driver)
    {
        return new AboutUsPageObject(driver);
    }
    public static CustomerServicePageObject getCustomerServicePage(WebDriver driver)
    {
        return new CustomerServicePageObject(driver);
    }
    public static CareerPageObject getCareerPage(WebDriver driver)
    {
        return new CareerPageObject(driver);
    }
    public static FinancePageObject getFinacePage(WebDriver driver)
    {
        return new FinancePageObject(driver);
    }

}
