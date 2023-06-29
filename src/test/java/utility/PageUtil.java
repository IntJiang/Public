package utility;

import io.restassured.http.Method;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utility.Objects.User;

import java.util.*;

import static java.time.Duration.ofSeconds;

public class PageUtil extends Utils {

    public static String projectPath;
    public static WebDriver driver;
    public Map<String, User> users = loadUser();

    @BeforeTest
    public void beforeTest() {
        projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + ".\\driver\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        try {
            driver = new ChromeDriver(chromeOptions);
        } catch (SessionNotCreatedException e) {
            if (e.getMessage().contains("This version of ChromeDriver only supports Chrome version")) {
                String chromeVersion = e.getMessage().split("Current browser version is | with binary path")[1];
                downloadChromedriver(chromeVersion);
                driver = new ChromeDriver(chromeOptions);
            } else {
                throw e;
            }
        }

        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://keylol.com/");
    }

    @AfterTest
    public void afterTest() {
        driver.close();
    }

    public void downloadChromedriver(String chromeVersion) {
//      Referring to https://chromedriver.chromium.org/downloads/version-selection

        String uri = "https://chromedriver.storage.googleapis.com";
        String[] num = chromeVersion.split("\\.");
        String path = "/LATEST_RELEASE_" + chromeVersion.replace("." + num[num.length - 1], "");
        String driverVersion = API.sendRequest(Method.GET, null, null, uri, path).body().print();
        String linkPath = driverVersion + "/chromedriver_win32.zip";
        assert API.sendAndDownload(uri, linkPath, projectPath + ".\\driver\\chromedriver.zip");
        unzip(projectPath + ".\\driver\\chromedriver.zip", projectPath + ".\\driver");
    }

    public void waitForAngularStable() {
        try {
            Log.debug("Sleep start at:" + new Date());
            Thread.sleep(1000);
            Log.debug("Sleep end at:" + new Date());

        } catch (InterruptedException e) {
            Log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
}
