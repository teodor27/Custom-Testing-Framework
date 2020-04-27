package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.SoftAssertions;
import pages.MainEmagPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainEmagSteps extends ScenarioSteps {
    MainEmagPage mainEmagPage;
    SoftAssertions softAssertions;

    @Step()
    public void selectSearchBox() {
        mainEmagPage.clickSearchBox();
        assertThat(mainEmagPage.isSearchBoxOpen()).isTrue();
    }

    @Step()
    public void searchForItem(String input) {
        mainEmagPage.typeIntoSearchBox(input);
        mainEmagPage.pressSearch();
    }

    @Step()
    public void checkItemsAreFound() {
        assertThat(mainEmagPage.areAnyItemsFound()).isTrue();
    }

    @Step
    public void sortByNumberOfReviews() {
        mainEmagPage.clickSortButton();
        mainEmagPage.sortByReviewNumber();
    }

    @Step
    public void closeCookiePrompt() {
        mainEmagPage.closeCookiePrompt();
        assertThat(mainEmagPage.isCookiePromptClosed()).isTrue();
    }

    @Step
    public void collectInformation() {
        mainEmagPage.collectInformation();
    }

    @Step
    public void selectTopRatedItems() {
        mainEmagPage.filterDuplicateItems();
        mainEmagPage.filterLowNumberItems();
        mainEmagPage.filterLowRatedItems();
        mainEmagPage.filterNonPopularItems();
//        mainEmagPage.filterOverPricedItems();
        mainEmagPage.displayItems();

    }

    @Step
    public void applyLeftSidebarRatingFilter() {
        mainEmagPage.pressRatingFilter();
    }

    @Step
    public void collectPopularItemInfo() {
        mainEmagPage.collectPopularItemInfo();
    }

    @Step
    public void sortByMostPopular() {
        mainEmagPage.clickSortButton();
        mainEmagPage.sortByMostPopular();
    }

}
