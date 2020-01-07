package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class CareersNewPosition extends CareersHeader {

    @FindBy(xpath = "//label[@for='positionTitle']/../input")
    private WebElement positionTitle;

    @FindBy(xpath = "//label[@for='positionDescription']/../textarea")
    private WebElement positionDescription;

    @FindBy(xpath = "//label[@for='positionAddress']/../input")
    private WebElement positionAddress;

    @FindBy(xpath = "//label[@for='positionCity']/../input")
    private WebElement positionCity;

    @FindBy(xpath = "//label[@for='positionState']/../select")
    private WebElement positionState;

    @FindBy(xpath = "//label[@for='positionZip']/../input")
    private WebElement positionZip;

    @FindBy(xpath = "//input[@id='positionDateOpen']")
    private WebElement positionDateOpen;

    @FindBy(xpath = "//button[@id='positionSubmit']")
    private WebElement submitButton;

    public CareersRecruit createPosition(Map<String, String> position) {
        positionTitle.sendKeys(position.get("title"));
        positionDescription.sendKeys(position.get("description"));
        positionAddress.sendKeys(position.get("address"));
        positionCity.sendKeys(position.get("city"));
        new Select(positionState).selectByValue(position.get("state"));
        positionZip.sendKeys(position.get("zip"));
        positionDateOpen.sendKeys(position.get("dateOpen"));
        click(submitButton);
        return new CareersRecruit();
    }


}
