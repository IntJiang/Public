package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FrontPage extends BasePage {

    @FindBy(css = "#nav-user-action-bar a[href$='login']")
    private WebElement loginBtn;

    public void clickLoginBtn() {
        loginBtn.click();
        waitForAngularStable();
    }
}
