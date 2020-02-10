package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SchedulePickup;
import pages.UspsHome;
import pages.UspsShip;
import pages.UspsStore;
import support.TestContext;

import javax.swing.text.DateFormatter;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.info;
import static javax.print.attribute.standard.MediaPrintableArea.MM;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;
import static support.TestContext.getWait;

public class UspsStepDefs {
    @When("I go to usps {string} store")
    public void iGoToUspsStore(String store) {
        switch (store) {
            case "stamps":
                new UspsHome().clickStamps();
                new UspsStore().selectStampsCategory();
            break;
            case "boxes":
                new UspsHome().mouseOverQuickTools();
                new UspsHome().clickBoxes();
                break;
            default:
                throw new RuntimeException("Invalid store: " + store);
        }
    }

    @And("I sort usps results by {string}")
    public void iSortUspsResultsBy(String sortBy) {
        new UspsStore().selectSortBy(sortBy);
    }

    @Then("I verify that usps {string} is cheapest")
    public void iVerifyThatUspsIsCheapest(String item) {
        String actualItem = new UspsStore().getFirstResultItemName();
        assertThat(actualItem).contains(item);
    }

    @When("I go to {string} under {string} menu")
    public void iGoToUnderMenu(String menuItem, String menu) throws InterruptedException {
        new UspsHome().mouseOverMenu(menu);
        new UspsHome().clickMenuItem(menuItem);
    }

    @Then("I verify that {string} is required")
    public void iVerifyThatIsRequired(String arg0) {
        UspsShip ship = new UspsShip();
        ship.clickSignIn();
        assertThat(ship.isSignInErrorsDisplayed()).isTrue();
    }

    @Then("I verify that {string} is possible")
    public void iVerifyThatIsPossible(String arg0) {
        assertThat(new UspsShip().isSignUpPossible()).isTrue();
    }

    @When("I mouseover {string}")
    public void iMouseover(String text) {
            new UspsHome().mouseOverMenu(text);
    }

    @And("I go to {string}")
    public void iGoTo(String text) {
        new UspsHome().clickMenuItem(text);
    }

    @And("I fill out the form {string}")
    public void iFillOutTheForm(String step) throws InterruptedException {
        Map <String, String> stepOneForm = getData (step);
        new SchedulePickup().fillOutTheForm(stepOneForm);


    }

    @And("I verify Service Availability")
    public void iVerifyServiceAvailability() {
        new SchedulePickup().isServiceAvailable();
    }

    @And("I click on {string} radio-button")
    public void iClickOnRadioButton(String arg0) {
//        new SchedulePickup().useSelectedAddress();
        new SchedulePickup().clickOnNo();

    }

    @And("I select {string}")
    public void iSelect(String text) {
        new SchedulePickup().useSelectDropDown(text);


    }

    @And("I enter {string} in additional instructions")
    public void iEnterInAdditionalInstructions(String text) {
        new SchedulePickup().enterText(text);
    }

    @And("I choose regular time")
    public void iChooseRegularTime() {
        new SchedulePickup().clickRegTime();
    }


    @And("I choose a day")
    public void iChooseADay() {
        new SchedulePickup().chooseDate();
    }


    @And("I choose {string} number of packages")
    public void iChooseNumberOfPackages(String packages) {
        new SchedulePickup().packageNumber(packages);
    }


    @And("I enter {string} pounds of total weight")
    public void iEnterPoundsOfTotalWeight(String weight) {
        new SchedulePickup().enterTotalWeight(weight);
    }

    @And("I check terms and Conditions")
    public void iCheckTermsAndConditions() {
        new SchedulePickup().checkTermsAndConditions();
        new SchedulePickup().clickOnSchedulePickupButton();
    }

    @Then("I verify that pickup is scheduled")
    public void iVerifyThatPickupIsScheduled() {
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Your Pickup Has Been Scheduled.')]")));
       new SchedulePickup().showScheduledNotification();

    }

    @Then("I verify that all data entered is correct")
    public void iVerifyThatAllDataEnteredIsCorrect() {

        assertThat(new SchedulePickup().collectAllInformation().contains("WEC"));
    }
}
