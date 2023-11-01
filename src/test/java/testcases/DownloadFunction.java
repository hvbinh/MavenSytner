package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;



public class DownloadFunction {
    WebDriver driver;


    @BeforeClass
    public void init()
    {
        String path = System.getProperty("user.dir")+File.separator +"DownloadFile";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        Map <String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", path);

        options.setExperimentalOption("prefs", prefs);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://github.com/hvbinh/playwright-swaglabs");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test()
    public void Tc_01_Download() throws InterruptedException {

        driver.findElement(By.xpath("//Span[@class='Button-content']//Span[text()='Code']")).isEnabled();
        driver.findElement(By.xpath("//Span[@class='Button-content']//Span[text()='Code']")).click();
        driver.findElement(By.xpath("//a[contains(.,'Download ZIP')]")).isEnabled();
        driver.findElement(By.xpath("//a[contains(.,'Download ZIP')]")).click();
        Thread.sleep(3000);


        String path = System.getProperty("user.dir")+File.separator+"DownloadFile"+File.separator+"playwright-swaglabs-master.zip";
        System.out.println("path: "+path);
        File downloadFile = new File(path);
        if(downloadFile.exists()) {
            System.out.println("downloaded success");
        }
        else {
            System.out.println("download failed");
        }




    }



    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }
}
