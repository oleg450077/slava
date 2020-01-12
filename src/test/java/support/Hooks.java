package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static support.TestContext.*;

public class Hooks {

    @Before(order = 0)
    public void scenarioStart() {
        TestContext.initialize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(getConfig().getInt("pageLoadTimeout"), TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(getConfig().getInt("implicitTimeout"), TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    @After(order = 0)
    public void scenarioEnd(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();
            byte[] screenshot = screenshotTaker.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }
        TestContext.teardown();
    }

    @After(value = "@clean_position")
    public void clearPosition(Scenario scenario) {
        Integer positionId = getIntegerTestData("lastPositionId");
        if (positionId != null) {
            new RestWrapper()
                    .login(getData("recruiter"))
                    .deletePositionById(positionId);
        }
    }
}
