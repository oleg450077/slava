package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.CareersLanding;
import pages.CareersRecruit;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;

public class CareersStepDefs {
    @And("I login as {string}")
    public void iLoginAs(String role) {
        new CareersLanding()
                .clickLogin()
                .login(getData(role));
    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String role) {
        String actualName = new CareersLanding().getLoggedInUsername();
        String expectedName = getData(role).get("name");
        assertThat(actualName).isEqualTo(expectedName);
    }

    @When("I create new {string} position")
    public void iCreateNewPosition(String type) {
        Map<String, String> position = getData(type);
        position.put("title", addTimeStampToField(position.get("title")));
        setTestData("lastPositionTitle", position.get("title"));
        new CareersLanding()
                .clickRecruit()
                .clickNewPosition()
                .createPosition(position);
    }

    @And("I verify {string} position created")
    public void iVerifyPositionCreated(String type) {
        String expectedTitle = getStringTestData("lastPositionTitle");
        assertThat(new CareersRecruit().isPositionPresent(expectedTitle)).isTrue();
        setTestData("lastPositionId", new CareersRecruit().getPositionIdByTitle(expectedTitle));
        System.out.println();
    }
}
