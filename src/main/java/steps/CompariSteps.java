package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CompariPage;
import pages.MainEmagPage;
import pages.PriceRunnerPage;

public class CompariSteps extends ScenarioSteps {
    CompariPage compariPage;
    MainEmagPage mainEmagPage;

    @Step
    public void openNewCompariTab() {
        compariPage.openNewTab();
        compariPage.switchToTab(1);
    }

    @Step
    public void collectRatingInformation() {
        compariPage.collectProductInformation();
    }

}
