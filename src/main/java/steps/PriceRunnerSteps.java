package steps;

import net.thucydides.core.annotations.Step;
import pages.PriceRunnerPage;

public class PriceRunnerSteps extends BaseSteps {
    PriceRunnerPage priceRunnerPage;

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
