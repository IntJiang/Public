package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Date;

import static java.time.Duration.ofSeconds;


public class Case {
    public static String projectPath;
    public static WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public static void beforeTest() {
        projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + ".\\Driver\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("https://www.baidu.com");
        Thread.sleep(1000);
        System.out.println("Page opened");

        WebElement searchField = driver.findElement(By.cssSelector("#kw"));
        searchField.sendKeys("China");
        searchField.sendKeys(Keys.ENTER);
        System.out.println("Searching...");
        System.out.println("Sleep start at:");
        System.out.println(new Date());
        Thread.sleep(1000);
        System.out.println("Sleep end at:");
        System.out.println(new Date());


        assert driver.findElement(By.cssSelector("#wrapper_wrapper .c-border")).isDisplayed();
    }

    @AfterTest(alwaysRun = true)
    public static void afterTest() {
        System.out.println("Test end");
        driver.close();
    }

}
