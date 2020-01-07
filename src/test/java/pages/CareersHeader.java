package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersHeader extends Page {

    private String headerTitle;

    @FindBy(xpath="//span[contains(@class,'center')]")
    private WebElement headerTitleElement;

    @FindBy(xpath="//a[@href='/login']/button")
    private WebElement loginButton;

    @FindBy(xpath="//*[@class='logout-box']/a")
    private WebElement loggedInUsername;

    @FindBy(xpath="//a[@href='/recruit']/button")
    private WebElement recruitButton;

    public String getHeaderTitle() {
        return headerTitleElement.getText();
    }

    public CareersLogin clickLogin() {
        click(loginButton);
        return new CareersLogin();
    }

    public CareersRecruit clickRecruit() {
        click(recruitButton);
        return new CareersRecruit();
    }

    public String getLoggedInUsername() {
        return loggedInUsername.getText();
    }
}
