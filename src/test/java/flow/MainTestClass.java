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


    @Title("Search for item category in Emag Home Page")
    @Test()
    public void test1_SearchForItem() {
        mainEmagSteps.navigateToHomePage();
        mainEmagSteps.selectSearchBox();
        mainEmagSteps.searchForItem();
        mainEmagSteps.checkItemsAreFound();
        mainEmagSteps.closeCookiePrompt();

    }

    @Title("Filter by Reviews and collect product data")
    @Test()
    public void test2_FilterAndCollect() {
        mainEmagSteps.applyLeftSidebarRatingFilter();
        mainEmagSteps.sortByNumberOfReviews();
        mainEmagSteps.collectInformation();
        mainEmagSteps.filterItemsBelowAverage();
        mainEmagSteps.filterByBudget();
        mainEmagSteps.narrowDownBestProducts();
        emagProductSteps.collectProductInformation();
        baseSteps.closeTab();

    }

    @Title("Find reviews in PriceRunner website")
    @Test()
    public void test4_CheckPriceRunnerReviews() {
        priceRunnerSteps.openNewPriceRunnerTab();
        priceRunnerSteps.collectRatingInformation();
        baseSteps.closeTab();
    }

    @Title("Find reviews and better prices in Compari.ro website")
    @Test()
    public void test5_CheckCompariReviews() {
        compariSteps.openNewCompariTab();
        compariSteps.collectRatingAndOfferInformation();
        baseSteps.closeTab();
    }

    @Title("Determine best product")
    @Test()
    public void test6_DetermineBestProduct() {
        mainEmagSteps.determineBestProduct();
        mainEmagSteps.openProductPage();
    }

}
