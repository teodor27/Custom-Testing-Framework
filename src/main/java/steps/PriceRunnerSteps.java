package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.PriceRunnerPage;

public class PriceRunnerSteps extends ScenarioSteps {
    PriceRunnerPage priceRunnerPage;

    @Step
    public void openNewPriceRunnerTab() {
        priceRunnerPage.openNewTab();
        priceRunnerPage.switchToTab(1);
    }

    @Step
    public void collectRatingInformation() {
        priceRunnerPage.collectProductInformation();
    }

}
