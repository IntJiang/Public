package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

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
