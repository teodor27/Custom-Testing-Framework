package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.PriceRunnerPage;
import util.Input;

public class PriceRunnerSteps extends ScenarioSteps {
    PriceRunnerPage priceRunnerPage;
    Input input = new Input();

    @Step
    public void openNewPriceRunnerTab() {
        priceRunnerPage.openNewTab(input.getPriceRunnerUrl());
        priceRunnerPage.switchToTab(1);
    }

    @Step
    public void collectRatingInformation() {
        priceRunnerPage.collectProductInformation();
    }

}
