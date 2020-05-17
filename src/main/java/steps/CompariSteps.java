package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CompariPage;

public class CompariSteps extends ScenarioSteps {
    CompariPage compariPage;

    @Step
    public void openNewCompariTab() {
        compariPage.openNewTab();
        compariPage.switchToTab(1);
    }

    @Step
    public void collectRatingAndOfferInformation() {
        compariPage.collectProductInformation();
    }

}
