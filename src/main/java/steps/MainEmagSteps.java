package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MainEmagPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainEmagSteps extends ScenarioSteps {
    MainEmagPage mainEmagPage;

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
        mainEmagPage.sortByNumberOfReviews();
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
    public void filterItemsBelowAverage() {
        mainEmagPage.filterDuplicateItems();
        mainEmagPage.filterLowNumberItems();
        mainEmagPage.filterLowRatedItems();
    }

    @Step
    public void applyLeftSidebarRatingFilter() {
        mainEmagPage.pressRatingFilter();
    }

    @Step
    public void filterByBudget(double budget) {
        mainEmagPage.filterOverPricedItems(budget);
    }

    @Step
    public void narrowDownBestProducts() {
        mainEmagPage.reduceBestProductList();
        mainEmagPage.displayItems();
    }

    @Step
    public void determineBestProduct() {
        mainEmagPage.calculateFinalRatings();
        mainEmagPage.reduceToBestProduct();
        mainEmagPage.displayItems();
    }

    @Step
    public void openProductPage() {
        mainEmagPage.openProductPage();
    }
}
