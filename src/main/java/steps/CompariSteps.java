package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.CompariPage;
import util.Input;

public class CompariSteps extends ScenarioSteps {
    CompariPage compariPage;
    Input input = new Input();

    @Step
    public void openNewCompariTab() {
        compariPage.openNewTab(input.getCompariUrl());
        compariPage.switchToTab(1);
    }

    @Step
    public void collectRatingAndOfferInformation() {
        compariPage.collectProductInformation();
    }

}
