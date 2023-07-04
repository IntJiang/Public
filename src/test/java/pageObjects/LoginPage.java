package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name="username")
    private WebElement userNameField;

    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(css=".login_button")
    private WebElement loginBtn;

    public LoginPage(){
        super();
    }

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

    @FindBy(css = ".popuptext")
    private WebElement popUpWarning;
    public boolean isPopUpWarningDisplay(){
        return popUpWarning.isDisplayed();
    }


}
