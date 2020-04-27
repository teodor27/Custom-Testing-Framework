package flow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.GoogleSteps;
import steps.MainEmagSteps;
import util.BaseTestClass;

@RunWith(SerenityRunner.class)
public class MainTestClass extends BaseTestClass {

    @Steps
    MainEmagSteps mainEmagSteps;

    @Steps
    GoogleSteps googlePageSteps;


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
        mainEmagSteps.sortByMostPopular();
        mainEmagSteps.collectPopularItemInfo();
        mainEmagSteps.sortByNumberOfReviews();
        mainEmagSteps.collectInformation();
        mainEmagSteps.selectTopRatedItems();

    }

    @Title("Filter Reviews and collect data")
    @Test()
    public void test3_CheckGoogleReviews() {
        googlePageSteps.openNewGoogleTab();
//        googlePageSteps.collectRatingInformation();
//        googlePageSteps.calculateGoogleRatingsPerItem();
//        mainEmagSteps.filterByGoogleRatings();

    }


}
