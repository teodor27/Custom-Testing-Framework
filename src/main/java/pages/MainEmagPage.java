package pages;

import model.Item;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class MainEmagPage extends BasePage {

    private static final String SEARCH_BOX_ID = "searchboxTrigger";
    private static final String POPULAR_SEARCHES_BOLD_CSS = ".searchbox-active strong";
    private static final String SEARCH_BUTTON_CSS = ".searchbox-submit-button";
    private static final String SEARCH_RESULTS_CARDS_CSS = ".card-item.js-product-data";
    private static final String FILTER_BUTTON_CSS = "div:nth-child(2) > div.sort-control-btn-dropdown.hidden-xs > button";
    private static final String FILTER_BUTTON_TEXT_CSS = "div:nth-child(2) > div.sort-control-btn-dropdown.hidden-xs > button .text-truncate";
    private static final String ITEM_PER_PAGE_BUTTON_CSS = "div > div.sort-control-item.arn-test.hidden-xs > div > button";
    private static final String MOST_POPULAR_CSS = "[data-sort-id='popularity']";
    private static final String NEWEST_CSS = "[data-sort-id='id']";
    private static final String ITEM_STAR_RATING_CSS = ".js-product-url .star-rating";
    private static final String ITEM_STAR_NUMBER_CSS = ".js-product-url .star-rating-text .hidden-xs";
    private static final String ITEM_TITLE_CSS = ".product-title-zone a";
    private static final String ITEM_PRICE_CSS = ".product-new-price";
    private static final String NEXT_PAGE_BUTTON_XPATH = "//span[text()='Pagina urmatoare']";
    private static final String COOKIE_PROMPT_CSS = ".js-later";
    private static final String SEO_PAGE_BODY_ID = "#seo-links-body";
    private static final String PRE_LOADER_CSS = "#card_grid .preloader";
    private static final String RATING_FILTER_CSS = "[data-option-id='1-5']";
    private static final String STOCK_FILTER_CSS = "[data-name='In Stoc']";
    private static final String MAX_ITEMS_PER_PAGE_CSS = "[data-results-per-page='100']";


    double averageNumberOfReviews;
    double averageRating;


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
        WebElement element = this.findElementByCssSelector(FILTER_BUTTON_CSS);
        Actions action = new Actions(this.getDriver());
        action.moveToElement(element).build().perform();
        element.click();
    }

    public void sortByNumberOfReviews() {
        this.findElementByCssSelector(MOST_POPULAR_CSS).click();
        this.waitUntilPageIsLoadedByCss(PRE_LOADER_CSS);
        this.waitUntilElementIsInvisible((PRE_LOADER_CSS), 10);

    }

    public void closeCookiePrompt() {
        WebElement laterButton = this.shortWaitUntilPageIsLoadedByIdAndClickable(COOKIE_PROMPT_CSS);

        Actions action = new Actions(this.getDriver());
        action.doubleClick(laterButton).build().perform();
    }

    public boolean isCookiePromptClosed() {
        return this.findElementByCssSelector(COOKIE_PROMPT_CSS).isDisplayed();
    }

    public boolean isNextButtonEnabled() {
        boolean status;
        try {
            status = this.findElementByXpath(NEXT_PAGE_BUTTON_XPATH).isEnabled();
        } catch (NoSuchElementException e) {
            status = false;
        }
        return status;
    }

    public void pressNextButton(WebElement element) {
        Actions action = new Actions(this.getDriver());
        action.moveToElement(element).build().perform();
        this.findElementByXpath(NEXT_PAGE_BUTTON_XPATH).click();
        this.waitUntilPageIsLoadedByCss(PRE_LOADER_CSS);
        this.waitUntilElementIsInvisible((PRE_LOADER_CSS), 20);
    }

    public void collectInformation() {
        boolean isCollecting = true;
        WebElement seoPageBody = this.findElementByCssSelector(SEO_PAGE_BODY_ID);
        while (isCollecting) {
            int numberOfItemsPerPage = this.findElementsByCssSelector(ITEM_TITLE_CSS).size();
            for (int i = 1; i <= numberOfItemsPerPage; i++) {
                WebElement numberElement = findElementByCssSelector("[data-position='" + i + "'] " + ITEM_STAR_NUMBER_CSS);
                WebElement ratingElement = findElementByCssSelector("[data-position='" + i + "'] " + ITEM_STAR_RATING_CSS);
                WebElement titleElement = findElementByCssSelector("[data-position='" + i + "'] " + ITEM_TITLE_CSS);
                WebElement priceElement = findElementByCssSelector("[data-position='" + i + "'] " + ITEM_PRICE_CSS);

                String priceText = priceElement.getText().split(" ")[0];
                double correctedPrice;

                if (priceText.contains(".")) {
                    correctedPrice = Double.parseDouble(priceText) * 1000;
                } else {
                    correctedPrice = Double.parseDouble(priceText) / 100;
                }

                Item item = new Item()
                        .setName(titleElement.getAttribute("title"))
                        .setUrl(titleElement.getAttribute("href"))
                        .setNumberOfReviews(Integer.parseInt(numberElement.getText().split(" ")[0]))
                        .setRating(Double.parseDouble(ratingElement.getAttribute("class").split(" ")[2].split("-")[1]))
                        .setPrice((correctedPrice));

                if (item.getNumberOfReviews() == 0) {
                    break;
                }
                itemList.add(item);
            }

            if (isNextButtonEnabled()) {
                this.pressNextButton(seoPageBody);
            } else {
                isCollecting = false;
            }
        }
    }

    public void displayItems() {
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    public void filterLowNumberItems() {
        double average = averageNumberOfReviews;
        System.out.println("Average Number of ratings = " + average);
        if (itemList.size() > 10)
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getNumberOfReviews() < average) {
                    itemList.remove(i);
                    i--;
                }
            }

        System.out.println("DIMENSIUNE LISTA dupa nr filter: " + itemList.size());
    }

    public void filterLowRatedItems() {
        double average = averageRating;
        System.out.println("Average Ratings = " + average);
        if (itemList.size() > 10)
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getRating() < average) {
                    itemList.remove(i);
                    i--;
                }
            }

        System.out.println("DIMENSIUNE LISTA dupa rating filter: " + itemList.size());
    }


    public void filterOverPricedItems(double budget) {
        averageNumberOfReviews = calculateNumberOfRatingsAverage();
        averageRating = calculateRatingAverage();
        System.out.println("Buget = " + budget);
        int priceDeviationPercentage = 15;
        double referenceMaxPrice = budget + (priceDeviationPercentage * budget) / 100;
        double referenceMinPrice = budget - (priceDeviationPercentage * budget) / 100;
        System.out.println("PRICE RANGE = " + referenceMaxPrice + " - " + referenceMinPrice);

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getPrice() > referenceMaxPrice ||
                    itemList.get(i).getPrice() <= referenceMinPrice) {
                itemList.remove(i);
                i--;
            }
        }

        System.out.println("DIMENSIUNE LISTA dupa price filter: " + itemList.size());
    }

    public void filterDuplicateItems() {

        for (int i = 0; i < itemList.size(); i++) {
            for (int j = 0; j < itemList.size(); j++) {
                if ((!itemList.get(i).getName().equalsIgnoreCase(itemList.get(j).getName())) &&
                        itemList.get(i).getNumberOfReviews() == itemList.get(j).getNumberOfReviews() &&
                        itemList.get(i).getRating() == itemList.get(j).getRating() &&
                        itemList.get(i).getName().contains(itemList.get(j).getName().split(",")[0])) {
                    System.out.println("Removing duplicate ...." + itemList.remove(j).toString());
                }
            }
        }
        System.out.println("LIST AFTER DUPLICATE FILTER: " + itemList.size());
    }

    public void pressRatingFilter() {
        Actions action = new Actions(getDriver());
        action.moveToElement(findElementByCssSelector(RATING_FILTER_CSS)).build().perform();
        this.findElementByCssSelector(RATING_FILTER_CSS).click();
        this.waitUntilPageIsLoadedByCss(PRE_LOADER_CSS);
        this.waitUntilElementIsInvisible((PRE_LOADER_CSS), 10);
    }

    public void reduceBestProductList() {
        int size = itemList.size();
        if (size > 10)
            itemList.subList(10, size).clear();
    }

    public void calculateFinalRatings() {
        for (Item item : itemList) {
            double sum = 0;
            for (int i = 0; i < item.getSelectionPoints().size(); i++) {
                sum += item.getSelectionPoints().get(i);
            }
            double averageRating = (item.getRating() + sum / 1000) / (item.getSelectionPoints().size() + 1);
            item.setRating(averageRating);
            System.out.println("Calculated average rating for " + item.getBrand() + " " + item.getProductCode() + " = " + item.getRating());
        }
    }

    public void reduceToBestProducts() {
        if (itemList.size() > 3) {
            List<Item> top3List = new ArrayList<>();
            top3List.add(itemList.get(0));
            itemList.remove(0);

            Item maxNumberOfReviews = itemList.get(0);
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getNumberOfReviews() > maxNumberOfReviews.getNumberOfReviews()) {
                    maxNumberOfReviews = itemList.get(i);
                } else if (maxNumberOfReviews.getNumberOfReviews() == itemList.get(i).getNumberOfReviews()) {
                    if (maxNumberOfReviews.getRating() < itemList.get(i).getRating()) {
                        maxNumberOfReviews = itemList.get(i);
                    } else if (maxNumberOfReviews.getRating() == itemList.get(i).getRating()) {
                        if (maxNumberOfReviews.getPrice() > itemList.get(i).getPrice()) {
                            maxNumberOfReviews = itemList.get(i);
                        }
                    }
                }
            }
            top3List.add(maxNumberOfReviews);
            itemList.remove(maxNumberOfReviews);

            Item bestRating = itemList.get(0);
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getRating() > bestRating.getRating()) {
                    bestRating = itemList.get(i);
                } else if (bestRating.getRating() == itemList.get(i).getRating()) {
                    if (bestRating.getNumberOfReviews() < itemList.get(i).getNumberOfReviews()) {
                        bestRating = itemList.get(i);
                    } else if (bestRating.getNumberOfReviews() == itemList.get(i).getNumberOfReviews()) {
                        if (bestRating.getPrice() > itemList.get(i).getPrice()) {
                            bestRating = itemList.get(i);
                        }
                    }
                }
            }
            top3List.add(bestRating);

            itemList.clear();
            itemList = top3List;
        }
    }

    private double calculateRatingAverage() {
        double sum = 0;
        for (Item item : itemList) {
            sum += item.getRating();
        }
        return sum / itemList.size();
    }

    private double calculateNumberOfRatingsAverage() {
        double sum = 0;
        for (Item item : itemList) {
            sum += item.getNumberOfReviews();
        }
        return sum / itemList.size();
    }


    public void openProductPageByRank(int rank) {
        try {
            this.openNewTab(input.getEMagUrl());
            this.switchToTab(1);
            this.sleep(1000);
            System.out.println("Accessing URL: " + itemList.get(rank - 1).getUrl());
            getDriver().navigate().to(itemList.get(rank - 1).getUrl());
            this.waitUntilPageIsLoadedByCss("body");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Rank " + rank + " ..... does not exist");
        }

    }

    public void navigateToHomePage(String eMagUrl) {
        getDriver().navigate().to(eMagUrl);
    }

    public void pressStockFilter() {
        Actions action = new Actions(getDriver());
        action.moveToElement(findElementByCssSelector(STOCK_FILTER_CSS)).build().perform();
        this.findElementByCssSelector(STOCK_FILTER_CSS).click();
        this.waitUntilPageIsLoadedByCss(PRE_LOADER_CSS);
        this.waitUntilElementIsInvisible((PRE_LOADER_CSS), 10);
    }

    public void clickItemsPerPageButton() {
        WebElement element = this.findElementByCssSelector(ITEM_PER_PAGE_BUTTON_CSS);
        Actions action = new Actions(this.getDriver());
        action.moveToElement(element).build().perform();
        element.click();
    }

    public void setMostItemsPerPage() {
        this.findElementByCssSelector(MAX_ITEMS_PER_PAGE_CSS).click();
        this.waitUntilPageIsLoadedByCss(PRE_LOADER_CSS);
        this.waitUntilElementIsInvisible((PRE_LOADER_CSS), 10);
    }

    public boolean isPopularFilterSelected() {
        return this.findElementByCssSelector(FILTER_BUTTON_TEXT_CSS).getText().contentEquals("Cele mai populare");
    }

    public boolean isNewestItemFilterDisplayed() {
        boolean status = false;
        try {
            status = this.findElementByCssSelector(NEWEST_CSS).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Newer Filter not available ...");
        }
        return status;
    }

    public void sortByNewestItem() {
        this.findElementByCssSelector(NEWEST_CSS).click();
        this.waitUntilPageIsLoadedByCss(PRE_LOADER_CSS);
        this.waitUntilElementIsInvisible((PRE_LOADER_CSS), 10);
    }
}
