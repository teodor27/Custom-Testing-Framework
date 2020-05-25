package pages;

import model.Item;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CompariPage extends BasePage {

    private static final String SEARCH_BOX_ID = "st";
    private static final String RATING_CSS = ".hidden-xs [data-tab-name='pareri']";
    private static final String SEARCH_BUTTON_CSS = ".btn-primary.button-search";
    private static final String TOP_OFFERED_PRICES_CSS = "#offer-block-paying .row-price > span";
    private static final String TOP_OFFERED_URL_XPATH = "//*[@id='offer-block-paying']/div/a";
    private static final String ITEM_NOT_FOUND_WARNING_CSS = ".alert-warning";

    public void collectProductInformation() {
        for (Item item : itemList) {
            searchForItem(item);
            if (!isItemNotFoundWarningDisplayed()) {
                if (isItemFound(item.getProductCode())) {
                    this.findElementByXpath("//a[contains(@title,'" + item.getProductCode() + "')]").click();
                    setItemRating(item);
                    saveBetterOffer(item);
                } else {
                    System.out.println("Searching by description ....");
                    String description = searchForItemByDescription(item);
                    if (!isItemNotFoundWarningDisplayed()) {
                        if (isItemFound(description)) {
                            this.findElementByXpath("//a[contains(@title,'" + description + "')]").click();
                            setItemRating(item);
                            saveBetterOffer(item);
                        }
                    }
                }
            } else {
                System.out.println("Searching by description ....");
                String description = searchForItemByDescription(item);
                if (!isItemNotFoundWarningDisplayed()) {
                    if (isItemFound(description)) {
                        this.findElementByXpath("//a[contains(@title,'" + description + "')]").click();
                        setItemRating(item);
                        saveBetterOffer(item);
                    }
                }
            }
        }
    }

    private boolean isItemNotFoundWarningDisplayed() {
        boolean status = false;
        try {
            status = this.findElementByCssSelector(ITEM_NOT_FOUND_WARNING_CSS).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Item not found ... on compari");
        }
        return status;
    }

    private void setItemRating(Item item) {
        this.waitUntilPageIsLoadedByCss(RATING_CSS);
        String ratingText = this.findElementByCssSelector(RATING_CSS).getAttribute("title");
        System.out.println("Star rating ELEMENT FOUND = " + ratingText);
        double rating = Double.parseDouble(ratingText) * 1000;
        if (rating > 0) {
            item.addSelectionPoints((int) rating);
            System.out.println("CALCULATED SELECTION POINT FOR " + item.getName() + " " + item.getSelectionPoints());
        }
    }

    private void searchForItem(Item item) {
        this.sleep(800);
        System.out.println("Searching Compari.ro for: " + item.getBrand() + " " + item.getProductCode());
        WebElement searchBox = this.waitUntilPageIsLoadedById(SEARCH_BOX_ID);
        String searchItem = item.getBrand() + " " + item.getProductCode();
        searchBox.clear();
        searchBox.sendKeys(searchItem);
        sleep(1000);
        this.findElementByCssSelector(SEARCH_BUTTON_CSS).click();
        this.sleep(800);
    }

    private String searchForItemByDescription(Item item) {
        this.sleep(800);
        String name = item.getName();
        String rawName = name.split(",")[0];
        String[] rawNameArray = rawName.split(" ");
        int brandIndex = 0;
        for (int i = 0; i < rawNameArray.length; i++) {
            if (rawNameArray[i].equalsIgnoreCase(item.getBrand())) {
                brandIndex = i;
                break;
            }
        }

        StringBuilder searchString = new StringBuilder();
        int counter = 0;
        for (int i = brandIndex; i < rawNameArray.length; i++) {
            searchString.append(rawNameArray[i]).append(" ");
            counter++;
        }
        if (searchString.length() > 0) {
            if (counter == 1) {
                searchString.append(item.getProductCode());
            }
            System.out.println("Searching Compari.ro for: " + searchString);
            WebElement searchBox = this.waitUntilPageIsLoadedById(SEARCH_BOX_ID);
            searchBox.clear();
            searchBox.sendKeys(searchString);
            sleep(1000);
            this.findElementByCssSelector(SEARCH_BUTTON_CSS).click();
            this.sleep(800);
        }
        return String.valueOf(searchString);
    }

    private void saveBetterOffer(Item item) {
        List<WebElement> top3OfferPriceElements = this.findElementsByCssSelector(TOP_OFFERED_PRICES_CSS);
        List<WebElement> top3OfferUrlElements = this.findElementsByXpath(TOP_OFFERED_URL_XPATH);
        for (int i = 0; i < top3OfferPriceElements.size(); i++) {
            String rawPrice = top3OfferPriceElements.get(i).getAttribute("content");
            double compariPrice = Double.parseDouble(rawPrice);
            double priceMargin = item.getPrice() - item.getPrice() * 0.2;
            if (item.getPrice() > compariPrice && compariPrice >= priceMargin) {
                System.out.println("Setting new price and URL... for " + item.getBrand() + " " + item.getProductCode());
                System.out.println("Old price = " + item.getPrice());
                item.setPrice(compariPrice);
                System.out.println("New price = " + item.getPrice());
                String compariUrl = top3OfferUrlElements.get(i).getAttribute("href");
                System.out.println("Old URL = " + item.getUrl());
                item.setUrl(compariUrl);
                System.out.println("New URL = " + item.getUrl());
            }
        }
    }

    private boolean isItemFound(String description) {
        boolean status = false;
        try {
            status = this.findElementByXpath("//a[contains(@title,'" + description + "')]").isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Item not found on compari");
        }
        return status;
    }

}
