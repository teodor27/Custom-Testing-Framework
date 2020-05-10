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
        webDriver.navigate().to("https://emag.ro");
        //TODO add login
        mainEmagSteps.selectSearchBox();
        //TODO implement inpunt collection form file
        mainEmagSteps.searchForItem("Masina de spalat vase");
        //TODO make check that the search has relevant results
        mainEmagSteps.checkItemsAreFound();
        mainEmagSteps.closeCookiePrompt();

    }

    @Title("Filter Reviews and collect data")
    @Test()
    public void test2_FilterAndCollect() {
        mainEmagSteps.applyLeftSidebarRatingFilter();
        //TODO there are cases where the filter is already applied
        mainEmagSteps.sortByNumberOfReviews();
        mainEmagSteps.collectInformation();
        mainEmagSteps.filterItemsBelowAverage();
        mainEmagSteps.filterByBudget(300);
        mainEmagSteps.narrowDownBestProducts();
        emagProductSteps.collectProductInformation();
        baseSteps.closeTab();

    }

    @Title("Filter Reviews and collect data")
    @Test()
    public void test3_CheckPriceRunnerReviews() {
        priceRunnerSteps.openNewPriceRunnerTab();
        priceRunnerSteps.collectRatingInformation();
        baseSteps.closeTab();
    }

    @Title("Filter Reviews and collect data")
    @Test()
    public void test3_CheckCompariReviews() {
        ///TO CONTINUE FROM HERE
        compariSteps.openNewCompariTab();
        compariSteps.collectRatingInformation();
        baseSteps.closeTab();
    }


}
