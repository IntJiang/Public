package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrontPage extends BasePage {

    @FindBy(css = "#nav-user-action-bar a[href$='login']")
    private WebElement loginBtn;

    public FrontPage(){
        super();
    }
    public void clickLoginBtn() {
        loginBtn.click();
        waitForAngularStable();
    }
}
