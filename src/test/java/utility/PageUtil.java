package utility;

import io.restassured.http.Method;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Arrays;
import java.util.Date;

import static java.time.Duration.ofSeconds;

public class PageUtil extends Utils {

    API api;
    public static String projectPath;
    public static WebDriver driver;

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
                downloadChromedriver();
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

    public String getChromeVersion() {
        return (String) executeJS(
                "function getChromeVersion() {\n" +
                        "        var arr = navigator.userAgent.split(' ');\n" +
                        "        var chromeVersion = '';\n" +
                        "        for (var i = 0; i < arr.length; i++) {\n" +
                        "            if (/chrome / i.test(arr[i]))\n" +
                        "            chromeVersion = arr[i]\n" +
                        "        }\n" +
                        "        if (chromeVersion) {\n" +
                        "            return Number(chromeVersion.split('/')[1].split('.')[0]);\n" +
                        "        } else {\n" +
                        "            return false;\n" +
                        "        }\n" +
                        "    }",
                "getChromeVersion"
        );
    }

    public void downloadChromedriver() {
//      Referring to https://chromedriver.chromium.org/downloads/version-selection

        String chromeVersion = getChromeVersion();
        String uri = "https://chromedriver.storage.googleapis.com";
        String[] num = chromeVersion.split("\\.");
        String path = "/LATEST_RELEASE_" + chromeVersion.replace("." + num[num.length - 1], "");
        String driverVersion = api.sendRequest(Method.GET, null, "", uri, path).body().print();
        String link = "https://chromedriver.storage.googleapis.com/index.html?path=" + driverVersion;
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
