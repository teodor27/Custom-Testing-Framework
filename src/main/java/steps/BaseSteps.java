package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.BasePage;

public class BaseSteps extends ScenarioSteps {
    BasePage basePage;

    @Step
    public void goToEmagPage(String url) {
    }
}
