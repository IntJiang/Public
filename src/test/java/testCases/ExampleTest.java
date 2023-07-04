package testCases;

import utility.Objects.User;
import utility.PageUtil;
import utility.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.SkipException;
import pageObjects.FrontPage;
import pageObjects.LoginPage;

public class ExampleTest extends PageUtil {
    FrontPage frontPage;
    LoginPage loginPage;

    @Test(description = "Example: Login to keylol.com")
    public void example() {
        waitForAngularStable();
        Log.debug("Open page: " + driver.getTitle());
        frontPage = new FrontPage();
        loginPage = new LoginPage();

        frontPage.clickLoginBtn();
        Assert.assertTrue(getPageTitle(driver).startsWith("账号登录"),
                "Incorrect page, current page title is:" + getPageTitle(driver));
        User user = users.get("My user");
        loginPage.enterUsername(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isPopUpWarningDisplay(), "Pop up warning is not display");
//        Assert.assertTrue(loginPage.isSecCodeVerifyFieldDisplay(), "Secret code verify field is not display");
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
