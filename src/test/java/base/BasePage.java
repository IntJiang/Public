package base;

import base.tools.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Arrays;
import java.util.Date;

import static java.time.Duration.ofSeconds;

public class BasePage{
    public static String projectPath;
    public static WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + ".\\driver\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        driver.close();
    }

    public void waitForAngularStable(){
        try {
            Log.debug("Sleep start at:" + new Date());
            Thread.sleep(1000);
            Log.debug("Sleep end at:" + new Date());

        }catch (InterruptedException e){
            Log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
}
