package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import support.RestWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

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
    public void iCreateViaRESTCandidate(String type) {
        new RestWrapper().createCandidate(getData(type));
    }

    @And("I add via REST {string} resume to a new candidate")
    public void iAddViaRESTResumeToANewCandidate(String fileType) {
        File resume = getFile("resume", fileType);
        new RestWrapper().addResume(resume, RestWrapper.getLastCandidate().get("id"));
    }

    @Then("I verify via REST that {string} resume has been added")
    public void iVerifyViaRESTThatResumeHasBeenAdded(String fileType) {
        ExtractableResponse<Response> response = new RestWrapper()
                        .getResume(RestWrapper.getLastCandidate().get("id"));
        assertThat(response.header("Content-Disposition")).isEqualTo("attachment; filename=resume." + fileType);
        byte[] resumeByteArray = response.asByteArray();
        String hexSignature = Hex.encodeHexString(resumeByteArray);
        assertThat(hexSignature).startsWith("255044462d"); // PDF signature, refer to https://en.wikipedia.org/wiki/List_of_file_signatures
        saveData("returnedResume", fileType, resumeByteArray);
        File actualFile = getFile("resume", "pdf");
        File expectedFile = getFile("returnedResume", "pdf");
        boolean areEqual = false;
        try {
            areEqual = FileUtils.contentEquals(actualFile, expectedFile);
        } catch (IOException e) {
            System.out.println("Issue accessing the files: " + e);
        }
        assertThat(areEqual).isTrue();
    }

    @Then("I verify via REST new candidate is in the list")
    public void iVerifyViaRESTNewCandidateIsInTheList() {
        new RestWrapper().verifyCandidateInList();
    }

    @When("I update via REST {string} candidate")
    public void iUpdateViaRESTCandidate(String type) {
        Map<String, String> newFields = getData(type + "_updated");
        Object id = RestWrapper.getLastCandidate().get("id");
        new RestWrapper().updateCandidate(newFields, id);
    }

    @Then("I verify via REST new candidate is updated")
    public void iVerifyViaRESTNewCandidateIsUpdated() {
        Map<String, Object> expectedCandidate = RestWrapper.getLastCandidate();
        Map<String, Object> actualCandidate = new RestWrapper().getCandidateById(expectedCandidate.get("id"));

        assertThat(actualCandidate.get("id")).isEqualTo(expectedCandidate.get("id"));
        assertThat(actualCandidate.get("summary")).isEqualTo(expectedCandidate.get("summary"));
        assertThat(actualCandidate.get("address")).isEqualTo(expectedCandidate.get("address"));
        assertThat(actualCandidate.get("city")).isEqualTo(expectedCandidate.get("city"));
        assertThat(actualCandidate.get("state")).isEqualTo(expectedCandidate.get("state"));
        assertThat(actualCandidate.get("zip")).isEqualTo(expectedCandidate.get("zip"));

    }

    @When("I delete via REST new candidate")
    public void iDeleteViaRESTNewCandidate() {
        new RestWrapper().deleteCandidateById(RestWrapper.getLastCandidate().get("id"));
    }

    @Then("I verify via REST new candidate is deleted")
    public void iVerifyViaRESTNewCandidateIsDeleted() {
        List<Map<String, Object>> candidates = new RestWrapper().getCandidates();
        Map<String, Object> deletedCandidate = RestWrapper.getLastCandidate();
        for(Map<String, Object> candidate : candidates) {
            if (candidate.get("id").equals(deletedCandidate.get("id"))) {
                throw new RuntimeException("Candidate is still in the list! Id: " + deletedCandidate.get("id"));
            }
        }

    }

    @And("I apply via REST new candidate to a new position")
    public void iApplyViaRESTNewCandidateToANewPosition() {

        new RestWrapper().applyCandidateToPosition(RestWrapper.getLastCandidate().get("id"), RestWrapper.getLastPosition().get("id"));

    }

    @And("I apply via REST new candidate to a last position")
    public void iApplyViaRESTNewCandidateToALastPosition() {
    }

    @Then("I verify via REST new application is submitted")
    public void iVerifyViaRESTNewApplicationIsSubmitted() {
        
    }

    @When("I delete via REST new application")
    public void iDeleteViaRESTNewApplication() {
        
    }

    @Then("I verify via REST new application is deleted")
    public void iVerifyViaRESTNewApplicationIsDeleted() {
    }
}
