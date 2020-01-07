package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class CareersLogin extends CareersHeader {

    @FindBy(xpath = "//*[@for='loginUsername']/../input")
    private WebElement username;

    @FindBy(xpath="//*[@for='loginPassword']/../input")
    private WebElement password;

    @FindBy(xpath="//*[@id='loginButton']")
    private WebElement submit;

    public CareersLanding login(Map<String, String> credentials) {
        username.sendKeys(credentials.get("email"));
        password.sendKeys(credentials.get("password"));
        click(submit);
        return new CareersLanding();
    }
}
