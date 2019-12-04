package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static support.TestContext.getWait;

public class UspsShip extends Page {

    @FindBy(id = "btn-submit")
    private WebElement signIn;

    @FindBy(id = "sign-up-button")
    private WebElement signUp;

    @FindBy(id = "error-username")
    private WebElement errorUsername;

    @FindBy(id = "error-password")
    private WebElement errorPassword;

    public void clickSignIn() {
        signIn.click();
    }

    public boolean isSignInErrorsDisplayed() {
        waitForClickable(signIn);
        return errorUsername.isDisplayed() && errorPassword.isDisplayed();
    }

    public boolean isSignUpPossible() {
        return signUp.isDisplayed() && signUp.isEnabled();
    }

}
