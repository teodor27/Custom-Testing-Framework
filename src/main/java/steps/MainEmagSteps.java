package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.MainEmagPage;
import util.Input;

import static org.assertj.core.api.Assertions.assertThat;

public class MainEmagSteps extends ScenarioSteps {
    MainEmagPage mainEmagPage;
    Input input = new Input();

    @Step()
    public void selectSearchBox() {
        mainEmagPage.clickSearchBox();
        assertThat(mainEmagPage.isSearchBoxOpen()).isTrue();
    }

    @Step()
    public void searchForItem() {
        mainEmagPage.typeIntoSearchBox(input.getProductCategory());
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
    public void filterByBudget() {
        double budget = Double.parseDouble(input.getBudget());
        mainEmagPage.filterOverPricedItems(budget);
    }

    @Step
    public void narrowDownBestProducts() {
        mainEmagPage.reduceBestProductList();
        mainEmagPage.displayItems();
    }

    @Step
    public void determineBestProducts() {
        mainEmagPage.calculateFinalRatings();
        mainEmagPage.reduceToBestProducts();
        mainEmagPage.displayItems();
    }

    @Step
    public void openProductPage(int rank) {
        mainEmagPage.openProductPageByRank(rank);
    }

    @Step
    public void navigateToHomePage() {
        mainEmagPage.navigateToHomePage(input.getEMagUrl());
    }

    @Step
    public void applyLeftSidebarStockFilter() {
        mainEmagPage.pressStockFilter();
    }
}
