package testCases;

import utility.PageUtil;
import utility.Log;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.SkipException;
import pageObjects.FrontPage;
import pageObjects.LoginPage;

public class ExampleTest extends PageUtil {
    FrontPage frontPage = PageFactory.initElements(driver, FrontPage.class);
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

    @Test(description = "Example: Login to keylol.com")
    public void example() {
        waitForAngularStable();
        Log.debug("Open page: " + driver.getTitle());

        frontPage.clickLoginBtn();
        Assert.assertTrue(getPageTitle(driver).startsWith("账号登陆"),
                "Incorrect page, current page title is:" + getPageTitle(driver));

        loginPage.enterUsername("503412941@qq.com");
        loginPage.enterPassword("jzx8315308");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isSecCodeVerifyFieldDisplay(), "Secret code verify field is not displayed");
    }

    @Test(description = "Desc - Passing a scenario")
    public void passTestMethod() {
        Log.debug("Inside PassTestMethod");
    }

    @Test(description = "Desc - Failing a scenario")
    public void failTestMethod() {
        int a = 9 / 0;
        Log.debug("Inside FailTestMethod");
    }

    @Test(description = "Desc - Skipping a scenario")
    public void skipTestMethod() {
        throw new SkipException("Intentionally skipping");
    }
}
