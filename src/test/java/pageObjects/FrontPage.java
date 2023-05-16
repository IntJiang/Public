package pageObjects;

import utility.PageUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrontPage extends PageUtil {

    @FindBy(css = "#nav-user-action-bar a[href$='login']")
    private WebElement loginBtn;

    public void clickLoginBtn() {
        loginBtn.click();
        waitForAngularStable();
    }
}
