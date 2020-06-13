package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.BasePage;
import util.Input;

public class BaseSteps extends ScenarioSteps {
    BasePage basePage;
    Input input = new Input();

    @Step
    public void closeTab() {
        basePage.closeCurrentTab();
        basePage.switchToTab(0);
    }
}
