package flow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.*;
import util.BaseTestClass;

@RunWith(SerenityRunner.class)
public class MainTestClass extends BaseTestClass {

    @Steps
    MainEmagSteps mainEmagSteps;

    @Steps
    PriceRunnerSteps priceRunnerSteps;

    @Steps
    BaseSteps baseSteps;

    @Steps
    EmagProductSteps emagProductSteps;

    @Steps
    CompariSteps compariSteps;


    @Title("Search for item in Emag Main Page")
    @Test()
    public void test1_SearchForItem() {
        webDriver.navigate().to("https://www.emag.ro/homepage");
        //TODO add login
        mainEmagSteps.selectSearchBox();
        mainEmagSteps.searchForItem("Boxe wireless");
        //TODO make check that the search has relevant results
        mainEmagSteps.checkItemsAreFound();
        mainEmagSteps.closeCookiePrompt();

    }

    @Title("Filter Reviews and collect data")
    @Test()
    public void test2_FilterAndCollect() {
        mainEmagSteps.applyLeftSidebarRatingFilter();
        mainEmagSteps.sortByNumberOfReviews();
        mainEmagSteps.collectInformation();
        mainEmagSteps.filterItemsBelowAverage();
        mainEmagSteps.filterByBudget(600.0);
        mainEmagSteps.narrowDownBestProducts();
        emagProductSteps.collectProductInformation();
        baseSteps.closeTab();

    }

    @Title("Filter Reviews and collect data 2")
    @Test()
    public void test3_CheckPriceRunnerReviews() {
        priceRunnerSteps.openNewPriceRunnerTab();
        priceRunnerSteps.collectRatingInformation();
        baseSteps.closeTab();
    }

    @Title("Filter Reviews and collect data 3")
    @Test()
    public void test3_CheckCompariReviews() {
        compariSteps.openNewCompariTab();
        compariSteps.collectRatingAndOfferInformation();
        baseSteps.closeTab();
    }

    @Title("Determine final product")
    @Test()
    public void test3_DetermineBestProduct() {
        mainEmagSteps.determineBestProduct();
        mainEmagSteps.openProductPage();
    }

}
