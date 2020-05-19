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


    @Title("A - Search for item category in Emag Home Page")
    @Test()
    public void test_1() {
        mainEmagSteps.navigateToHomePage();
        mainEmagSteps.selectSearchBox();
        mainEmagSteps.searchForItem();
        mainEmagSteps.checkItemsAreFound();
        mainEmagSteps.closeCookiePrompt();

    }

    @Title("B - Filter by Reviews and collect product data")
    @Test()
    public void test_2() {
        mainEmagSteps.applyLeftSidebarStockFilter();
        mainEmagSteps.applyLeftSidebarRatingFilter();
        mainEmagSteps.sortByNumberOfReviews();
        mainEmagSteps.collectInformation();
        mainEmagSteps.filterItemsBelowAverage();
        mainEmagSteps.filterByBudget();
        mainEmagSteps.narrowDownBestProducts();
        emagProductSteps.collectProductInformation();
        baseSteps.closeTab();

    }

    @Title("C - Find reviews in PriceRunner website")
    @Test()
    public void test_3() {
        priceRunnerSteps.openNewPriceRunnerTab();
        priceRunnerSteps.collectRatingInformation();
        baseSteps.closeTab();
    }

    @Title("D - Find reviews and better prices in Compari.ro website")
    @Test()
    public void test_4() {
        compariSteps.openNewCompariTab();
        compariSteps.collectRatingAndOfferInformation();
        baseSteps.closeTab();
    }

    @Title("E - Determine best product number 1")
    @Test()
    public void test_5() {
        mainEmagSteps.determineBestProducts();
        mainEmagSteps.openProductPage(1);
    }

    @Title("F - Determine best product number 2")
    @Test()
    public void test_6() {
        baseSteps.closeTab();
        mainEmagSteps.openProductPage(2);
    }

    @Title("G - Determine best product number 3")
    @Test()
    public void test_7() {
        baseSteps.closeTab();
        mainEmagSteps.openProductPage(3);
    }

}
