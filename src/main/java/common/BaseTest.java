package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

public class BaseTest {
    private WebDriver driver;
    private String projectFolder = System.getProperty("user.dir");
    private String osName = System.getProperty("os.name");
    protected final Logger log;

    public BaseTest()
    {
        log = LogManager.getLogger(getClass());
    }

    protected synchronized WebDriver getBrowserDriver(String browserName) {
        // setBrowserDriver();
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.CHROME_UI) {
            WebDriverManager.chromedriver().setup();

            // disable log
            System.setProperty("webdriver.chrome.args", "--disable-logging");
            System.setProperty("webdriver.chrome.silentOutput", "true");

            // add extension
            File file = new File(projectFolder + File.separator + "browserExtentions" + File.separator + "extension_2_0_9_0.crx");
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(file);

            // change language in browser
            options.addArguments("--lang=vi");

            // disable inforbar/notification/location
            options.addArguments("--disable-inforbars");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-geolocation");
            // incognito
            options.addArguments("--incognito");

            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            // add save/download
            HashMap <String, Object> chromePrefs = new HashMap <String, Object>();
            chromePrefs.put("profile.default_content_settings_popups", 0);
            chromePrefs.put("download.default_directory", projectFolder + File.separator + "downloadFiles");
            options.setExperimentalOption("pref", chromePrefs);

            driver = new ChromeDriver(options);
        } else if (browser == Browser.FIREFOX_UI) {
            WebDriverManager.firefoxdriver().setup();


            // add extension
            FirefoxProfile profile = new FirefoxProfile();
            File translate = new File(projectFolder + File.separator + "browserExtentions" + File.separator + "to_google_translate-4.1.0-fx.xpi");
            profile.addExtension(translate);
            FirefoxOptions options = new FirefoxOptions();
            options.setProfile(profile);

            // change language in browser
            options.addPreference("intl.accept_languages", "vi-vn,vi,en-us,en");
            options.addPreference("network.trr.send_accept-language_headers", "true");

            // private
            options.addArguments("-private");

            // add save/download
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.download.dir", projectFolder + File.separator + "downloadFiles");
            options.addPreference("browser.download.useDownloadDir", true);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip, " + "application/x-zip-compressed,application/x-compressed," + "application/msword,application/csv,text/csv,image/png ,"
                    + "image/jpeg, application/pdf, text/html,text/plain,  application/excel, " + "application/vnd.ms-excel, application/x-excel, application/x-msexcel, " + "application/octet-stream");
            options.addPreference("pdfjs.disabled", true);

            driver = new FirefoxDriver(options);
        } else if (browser == Browser.EDGE_CHROMIUM) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser == Browser.CHROME_HEADLESS) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browser == Browser.FIREFOX_HEADLESS) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            //options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        } else if (browser == Browser.SAFARI) {
            driver = new SafariDriver();
        } else if (browser == Browser.IE) {
            WebDriverManager.iedriver().arch32().setup();
            driver = new InternetExplorerDriver();
        } else {
            throw new RuntimeException("please input valid browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIME));

        driver.get("http://demo.nopcommerce.com/");

        return driver;
    }

    protected synchronized WebDriver getBrowserDriver(String browserName, String url) {
        //System.setProperty("webdriver.http.factory", "jdk-http-client");
        // setBrowserDriver();
        Browser browser = Browser.valueOf(browserName.toUpperCase());
        if (browser == Browser.CHROME_UI) {
            WebDriverManager.chromedriver().setup(); // .driverVersion("86.0.4240.22").setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if (browser == Browser.FIREFOX_UI) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser == Browser.EDGE_CHROMIUM) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser == Browser.CHROME_HEADLESS) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browser == Browser.FIREFOX_HEADLESS) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            //options.setHeadless(true);
            options.addArguments("window-size=1920x1080");
            driver = new FirefoxDriver(options);
        } else {
            throw new RuntimeException("please input valid browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIME));

        driver.get(url);

        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void closeBrowserAndDriver(WebDriver driver) {
        try {

            String osName = System.getProperty("os.name").toLowerCase();


            String cmd = "";
            if (driver != null) {
                driver.quit();
            }

            // Quit driver executable file in Task Manager
            if (driver.toString().toLowerCase().contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("internetexplorer")) {
                if (osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driver.toString().toLowerCase().contains("firefox")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("edge")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill msedgedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                }
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();


        } catch (Exception e) {
            System.out.println("close browser failed " + e.toString());
        }
    }
}
