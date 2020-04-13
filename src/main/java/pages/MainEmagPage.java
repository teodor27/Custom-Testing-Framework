package pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainEmagPage extends BasePage {

    private static final String SEARCH_BOX_ID = "searchboxTrigger";
    private static final String POPULAR_SEARCHES_BOLD_CSS = ".searchbox-active strong";
    private static final String SEARCH_BUTTON_CSS = ".searchbox-submit-button";
    private static final String SEARCH_RESULTS_CARDS_CSS = ".card-item.js-product-data";
    private static final String FILTER_BUTTON_CSS = "div:nth-child(2) > div.sort-control-btn-dropdown.hidden-xs > button";
    private static final String FILTER_BUTTON_SPAN_CSS = "div:nth-child(2) > div.sort-control-btn-dropdown.hidden-xs > button > .sort-control-btn-option";
    private static final String NR_REVIEWURI_CSS = "[data-sort-id='reviews']";
    private static final String ITEM_CARDS_REVIEW_CSS = ".card .star-rating-text  .hidden-xs";

    public void clickSearchBox() {
        this.findElementById(SEARCH_BOX_ID).click();
    }

    public boolean isSearchBoxOpen() {
        this.waitUntilPageIsLoadedByCss(POPULAR_SEARCHES_BOLD_CSS);
        return this.findElementByCssSelector(POPULAR_SEARCHES_BOLD_CSS).isDisplayed();
    }

    public void typeIntoSearchBox(String input) {
        this.findElementById(SEARCH_BOX_ID).sendKeys(input);
    }

    public void pressSearch() {
        this.findElementByCssSelector(SEARCH_BUTTON_CSS).click();
    }

    public boolean areAnyItemsFound() {
        this.waitUntilPageIsLoadedByCss(SEARCH_RESULTS_CARDS_CSS);
        return findElementByCssSelector(SEARCH_RESULTS_CARDS_CSS).isDisplayed();
    }

    public void clickSortButton() {
        this.findElementByCssSelector(FILTER_BUTTON_CSS).click();
    }

    public void sortByReviewNumber() {
        this.findElementByCssSelector(NR_REVIEWURI_CSS).click();
    }

    public boolean checkPageIsSortedByNumberOfReviews() {
        this.waitUntilUrlContains("/sort-reviewsdesc", 5);
        this.waitUntilPageIsFullyLoaded(5);
        getDriver().navigate().refresh();
        this.waitUntilPageIsLoadedByCss(SEARCH_RESULTS_CARDS_CSS);

        boolean status = true;
        List<Integer> numberOfRatingsList = new ArrayList<>();
        List<WebElement> elementList = this.findElementsByCssSelector(ITEM_CARDS_REVIEW_CSS);
        for (WebElement element : elementList) {
            String text = element.getText();
            int number = Integer.parseInt(text.split(" ")[0]);
            numberOfRatingsList.add(number);
        }

        for (int i = 0; i < numberOfRatingsList.size() - 1; i++) {
            if (numberOfRatingsList.get(i) < numberOfRatingsList.get(i + 1)) {
                status = false;
                break;
            }
        }
        return status;
    }
}
