package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;

public class SchedulePickup extends Page {

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "addressLineOne")
    private WebElement addressLineOne;

    @FindBy(id = "addressLineTwo")
    private WebElement getAddressLineTwo;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id = "emailAddress")
    private WebElement emailAddress;

    @FindBy(id = "webToolsAddressCheck")
    private WebElement checkAvailability;

    @FindBy(xpath = "//p[@class='schedule-a-pickup-validation success']")
    private WebElement serviceAvailable;

    @FindBy(id = "second-radio-verification")
    private WebElement noRadioButton;

    @FindBy(xpath = "//h2[contains(text(),'Step 2:')]")
    private WebElement step2Section;

    @FindBy(xpath = "//a[@class='btn-primary use-selected-address']")
    private WebElement useSelectedAddressButton;

    @FindBy(id = "packageLocation")
    private WebElement selectDropDown;

    @FindBy(xpath = "//option[contains(text(),'Front Door')]")
    private WebElement dropDownElement;

    @FindBy(id = "step-two-desc-item-textarea")
    private WebElement textarea;

    @FindBy(id = "pickup-regular-time")
    private WebElement regularRoundButton;

    @FindBy(xpath = "//div[@class='ui-datepicker-group ui-datepicker-group-first']//a[@class='ui-state-default'][contains(text(),'29')]")
    private WebElement date;

    @FindBy(id = "countPriorityExpress")
    private WebElement packageNumber;

    @FindBy(id = "totalPackageWeight")
    private WebElement totalWeight;

    @FindBy(xpath = "//input[@class='termsConditions']")
    private WebElement termsAndConditions;

    @FindBy(xpath = "//h2[contains(text(),'Your Pickup Has Been Scheduled.')]")
    private WebElement scheduledNotification;

    @FindBy (id = "schedulePickupButton")
    private WebElement schedulePickUpButton;

    @FindBy(id = "pickupconfirmationNumberList")
    private WebElement confirmationNumber;


    public void fillOutTheForm(Map<String, String> stepOneForm) {
        firstName.sendKeys(stepOneForm.get("firstName"));
        lastName.sendKeys((stepOneForm.get("lastName")));
        addressLineOne.sendKeys(stepOneForm.get("addressLineOne"));
        getAddressLineTwo.sendKeys(stepOneForm.get("addressLineTwo"));
        city.sendKeys(stepOneForm.get("city"));
        state.sendKeys(stepOneForm.get("state"));
        zipCode.sendKeys(stepOneForm.get("zipCode"));
        phoneNumber.sendKeys(stepOneForm.get("phoneNumber"));
        emailAddress.sendKeys(stepOneForm.get("emailAddress"));
        checkAvailability.click();
    }

    public void isServiceAvailable() {
        serviceAvailable.isDisplayed();
    }

    public void clickOnNo() {
        noRadioButton.click();
        step2Section.isDisplayed();
    }

    public void useSelectedAddress() {
        useSelectedAddressButton.click();
    }

    public void useSelectDropDown(String text) {
//        selectDropDown.click();
        new Select(selectDropDown).selectByVisibleText(text);

    }


    public void enterText(String text) {
        textarea.click();
        textarea.sendKeys(text);

    }

    public void clickRegTime() {
        regularRoundButton.click();
    }

    public void chooseDate() {
        date.click();
    }

    public void packageNumber(String packages) {
        packageNumber.sendKeys(packages);
    }

    public void enterTotalWeight(String weight) {
        totalWeight.sendKeys(weight);
    }

    public void checkTermsAndConditions() {
        termsAndConditions.click();
    }

    public void clickOnSchedulePickupButton() {
        schedulePickUpButton.click();

    }

    public String showScheduledNotification() {
        String notification = scheduledNotification.getText();
        return notification;
    }

    public String collectAllInformation (){
        String conf = confirmationNumber.getText();

        return conf;
    }

}

