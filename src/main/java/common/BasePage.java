package common;

import com.mongodb.internal.connection.Time;
import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {


    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
        Actions actions = new Actions(driver);

    }

    protected String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getCurrentPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    protected void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    protected String getTextlAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    protected void setTextlAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    protected void waitAlertPresence(WebDriver driver) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void swithToWindowById(WebDriver driver, String parentID) {
        Set <String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }

    }

    protected boolean closeAllWindowsWithoutParent(WebDriver driver, String idParent) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {

            if (!runWindow.equals(idParent)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(idParent);
        if (driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.trim().equals(title)) {
                break;
            }
        }
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    protected WebElement getElement(WebDriver driver, String locator, String... values) {
        return getElement(driver, getDynamicLocator(locator, values));
    }

    public List <WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    protected void clickToElement(WebDriver driver, String locator, String... values) {
        element = getElement(driver, getDynamicLocator(locator, values));

        if(driver.toString().toLowerCase().contains("internet explorer"))
        {
            clickToElementByJS(driver, locator, values);
            sleepInSecond(3);
        }
        else
        {
            element.click();
        }
    }

    protected void clickToElement(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        if(driver.toString().toLowerCase().contains("internet explorer"))
        {
            clickToElementByJS(driver, locator);
            sleepInSecond(3);
        }
        else
        {
            element.click();

        }

    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
        element = getElement(driver, getDynamicLocator(locator, values));
        element.clear();
        element.sendKeys(value);
    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value) {
        element = getElement(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    protected By getByXpath(String locator) {
        return By.xpath(locator);
    }

    protected String getDynamicLocator(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        return locator;
    }

    protected void selectItemInDropdown(WebDriver driver, String locator, String itemValue) {
        element = getElement(driver, locator);
        select = new Select(element);
        select.selectByVisibleText(itemValue);
    }
    protected void selectItemInDropdown(WebDriver driver, String locator, String itemValue,String... values) {
        element = getElement(driver, getDynamicLocator(locator, values));
        select = new Select(element);
        select.selectByVisibleText(itemValue);
    }

    protected String getFirstSelectedTextInDropDown(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        select = new Select(element);
        return select.isMultiple();
    }

    protected void selectTheItemInCustomeDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedText) {

        getElement(driver, parentXpath).click();
        sleepInSecond(1);
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpath)));

        elements = getElements(driver, childXpath);

        for (WebElement actualItem : elements) {
            if (actualItem.getText().trim().equals(expectedText)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
                sleepInSecond(1);
                actualItem.click();
                sleepInSecond(1);
                break;
            }
        }

    }


    protected void sleepInSecond(long second) {

        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected String getElementAtribute(WebDriver driver, String locator, String attributeName) {
        element = getElement(driver, locator);
        return element.getAttribute(attributeName);
    }

    protected String getElementAtribute(WebDriver driver, String locator, String attributeName, String... values) {
        element = getElement(driver, getDynamicLocator(locator, values));
        return element.getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        return element.getText();
    }

    protected String getElementText(WebDriver driver, String locator, String... values) {
        element = getElement(driver, locator, values);
        return element.getText();
    }

    protected int countElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    protected int countElementSize(WebDriver driver, String locator, String... values) {
        return getElements(driver, getDynamicLocator(locator, values)).size();
    }

    protected void checkToCheckbox(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkToCheckbox(WebDriver driver, String locator, String... values) {
        element = getElement(driver, getDynamicLocator(locator, values));
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void uncheckToCheckbox(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
        try {
            return getElement(driver, getDynamicLocator(locator, values)).isDisplayed();
        }catch(Exception e)
        {
            System.out.println("execption: "+e);
            return false;
        }

    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    protected void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, locator)).perform();
    }

    protected void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    protected void hoverMouseToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }
    protected void hoverMouseToElement(WebDriver driver, String locator,String... values) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, getDynamicLocator(locator, values))).perform();
    }

    protected void clickAndHoldToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    protected void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
    }

    protected void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(getElement(driver, locator), key).perform();
    }

    protected Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    protected String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    protected void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    protected void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    protected void clickToElementByJS(WebDriver driver, String locator,String...values) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, getDynamicLocator(locator, values));
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
        sleepInSecond(1);
    }

    protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition <Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        jsExecutor = (JavascriptExecutor) driver;

        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    protected void waitToElementVisible(WebDriver driver, String locator) {

        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));

    }

    protected void waitToElementVisible(WebDriver driver, String locator, String... values) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
    }

    protected void waitToElementInvisible(WebDriver driver, String locator) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        overrideImplicitWait(driver, GlobalConstants.getGlobalConstants().getSHORT_TIME());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
        overrideImplicitWait(driver, GlobalConstants.getGlobalConstants().getSHORT_TIME());
    }

    protected void waitToElementInvisible(WebDriver driver, String locator, String... values) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, values))));
    }

    protected void waitToElementClickable(WebDriver driver, String locator) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    protected void waitToElementClickable(WebDriver driver, String locator, String... values) {
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.getGlobalConstants().getSHORT_TIME()));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, values))));
    }

    protected boolean isElementUndisplayed(WebDriver driver, String locator) {
        overrideImplicitWait(driver, GlobalConstants.getGlobalConstants().getSHORT_TIME());
        elements = getElements(driver, locator);
        overrideImplicitWait(driver, GlobalConstants.getGlobalConstants().getSHORT_TIME());
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    protected void overrideImplicitWait(WebDriver driver, long timeSecond)
    {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    protected void test(WebDriver driver)
    {
        WebDriverWait eWait = new WebDriverWait(driver, Duration.ofSeconds(30000));
        eWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));
        eWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
    }
    protected void test1(WebDriver driver)
    {
        WebDriverWait iWait = new WebDriverWait(driver, Duration.ofSeconds(30000));
        driver.findElement(By.xpath("")).isEnabled();


    }
    private List<WebElement> elements;
    private WebDriverWait explicitWait;
    private WebElement element;
    private JavascriptExecutor jsExecutor;
    private Actions action;
    private Select select;





}
