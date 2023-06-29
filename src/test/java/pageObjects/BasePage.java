package pageObjects;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utility.PageUtil;

public class BasePage extends PageUtil {

    BasePage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
}
