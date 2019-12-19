package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import support.RestWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class RestStepDefs {

    @Given("I login via REST as {string}")
    public void iLoginViaRESTAs(String role) {
        new RestWrapper().login(getData(role));
    }

    @When("I create via REST {string} position")
    public void iCreateViaRESTPosition(String type) {
        new RestWrapper().createPosition(getData(type));
    }

    @Then("I verify via REST new position is in the list")
    public void iVerifyViaRESTNewPositionIsInTheList() {
        List<Map<String, Object>> actualPositions = new RestWrapper().getPositions();
        Map<String, Object> lastPosition = RestWrapper.getLastPosition();

        boolean isFound = false;
        for (Map<String, Object> actualPosition : actualPositions) {
            if (actualPosition.get("id").equals(lastPosition.get("id"))) {
                isFound = true;
                for (String key : lastPosition.keySet()) {
                    Object expected = lastPosition.get(key);
                    Object actual = actualPosition.get(key);
                    System.out.println("Verifying field: " + key);
                    System.out.println("Expected: " + expected);
                    System.out.println("Actual: " + actual);
                    assertThat(actual).isEqualTo(expected);
                }
                break;
            }
        }
        assertThat(isFound).isTrue();
    }

    @When("I update via REST {string} position")
    public void iUpdateViaRESTPosition(String type) {
        Map<String, String> newFields = getData(type + "_updated");
        Object id = RestWrapper.getLastPosition().get("id");
        new RestWrapper().updatePosition(newFields, id);
    }

    @Then("I verify via REST new position is updated")
    public void iVerifyViaRESTNewPositionIsUpdated() {
        Map<String, Object> expectedPosition = RestWrapper.getLastPosition();
        Map<String, Object> actualPosition = new RestWrapper().getPositionById(expectedPosition.get("id"));

        for(String key : expectedPosition.keySet()) {
            assertThat(actualPosition.get(key)).isEqualTo(expectedPosition.get(key));
        }
    }

    @When("I delete via REST new position")
    public void iDeleteViaRESTNewPosition() {
        new RestWrapper().deletePositionById(RestWrapper.getLastPosition().get("id"));
    }

    @Then("I verify via REST new position is deleted")
    public void iVerifyViaRESTNewPositionIsDeleted() {
        List<Map<String, Object>> positions = new RestWrapper().getPositions();
        Map<String, Object> deletedPosition = RestWrapper.getLastPosition();
        for(Map<String, Object> position : positions) {
            if (position.get("id").equals(deletedPosition.get("id"))) {
                throw new RuntimeException("Position is still in the list! Id: " + deletedPosition.get("id"));
            }
        }
    }

    @When("I create via REST {string} candidate")
    public void iCreateViaRESTCandidate(String arg0) {

        // Example of handling email
        String email = "john@example.com";
        String name = email.split("@")[0];
        String domain = email.split("@")[1];
        String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-sss").format(new Date());
        String finalEmail = name + "+" + timestamp + "@" + domain;
        System.out.println(finalEmail);
    }
}
