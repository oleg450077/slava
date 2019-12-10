package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import support.RestWrapper;

import java.util.Map;

import static support.TestContext.getData;

public class RestStepDefs {

    @Given("I login via REST as {string}")
    public void iLoginViaRESTAs(String role) {
        new RestWrapper().login(getData(role));
    }
}
