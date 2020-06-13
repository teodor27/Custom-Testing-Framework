package steps;

import net.thucydides.core.annotations.Step;
import pages.MainEmagPage;

import static org.assertj.core.api.Assertions.assertThat;

public class MainEmagSteps extends BaseSteps {
    MainEmagPage mainEmagPage;

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
    public void sortByNewOrMostPopularItems() {
        mainEmagPage.clickSortButton();
        if (mainEmagPage.isNewestItemFilterDisplayed()) {
            mainEmagPage.sortByNewestItem();
        } else if (!mainEmagPage.isPopularFilterSelected()) {
            mainEmagPage.clickSortButton();
            mainEmagPage.sortByNumberOfReviews();
        }
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

    @Step
    public void displayMostItemsPerPage() {
        mainEmagPage.clickItemsPerPageButton();
        mainEmagPage.setMostItemsPerPage();
    }
}
