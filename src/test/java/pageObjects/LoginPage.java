package pageObjects;

import utility.PageUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageUtil {

    @FindBy(name="username")
    private WebElement userNameField;

    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(id="login_button_Lzp1k")
    private WebElement loginBtn;

    public void enterUsername(String name){
        userNameField.clear();
        userNameField.sendKeys(name);
    }
    public void enterPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginBtn.click();
        waitForAngularStable();
    }

    @FindBy(name = "seccodeverify")
    private WebElement secCodeVerifyField;
    public boolean isSecCodeVerifyFieldDisplay(){
        return secCodeVerifyField.isDisplayed();
    }


}
