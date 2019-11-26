package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.UspsHome;
import pages.UspsStore;

import static org.assertj.core.api.Assertions.assertThat;

public class UspsStepDefs {
    @When("I go to usps {string} store")
    public void iGoToUspsStore(String store) {
        switch (store) {
            case "stamps":
                new UspsHome().clickStamps();
                new UspsStore().selectStampsCategory();
            break;
            case "boxes":
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
}
