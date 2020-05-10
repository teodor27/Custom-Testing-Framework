package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.PriceRunnerPage;
import pages.MainEmagPage;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceRunnerSteps extends ScenarioSteps {
    PriceRunnerPage priceRunnerPage;
    MainEmagPage mainEmagPage;

    @Step
    public void openNewPriceRunnerTab() {
        priceRunnerPage.openNewTab();
        priceRunnerPage.switchToTab(1);
//        assertThat(priceRunnerPage.isPriceRunnerOpen()).isTrue();
    }

    @Step
    public void collectRatingInformation() {
//        googleSearchPage.searchProductList();
        priceRunnerPage.collectProductInformation();
    }

}
