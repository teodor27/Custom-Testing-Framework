package steps;

import net.thucydides.core.annotations.Step;
import pages.CompariPage;

public class CompariSteps extends BaseSteps {
    CompariPage compariPage;

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
