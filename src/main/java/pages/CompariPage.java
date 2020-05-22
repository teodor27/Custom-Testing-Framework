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
                    System.out.println("Trying by first item found....");
                    if (isFirstItemFoundByBrand(item.getBrand())) {
                        String brandName = item.getBrand();
                        String capitalizedBrandName = brandName.substring(0, 1).toUpperCase() + brandName.substring(1).toLowerCase();
                        this.findElementByXpath("(//h2/a[contains(@title,'" + capitalizedBrandName + "')])[1]").click();
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

    private void saveBetterOffer(Item item) {
        List<WebElement> top3OfferPriceElements = this.findElementsByCssSelector(TOP_OFFERED_PRICES_CSS);
        System.out.println("lista preturi = " + top3OfferPriceElements.size());
        List<WebElement> top3OfferUrlElements = this.findElementsByXpath(TOP_OFFERED_URL_XPATH);
        System.out.println("lista url-uri = " + top3OfferUrlElements.size());
        for (int i = 0; i < top3OfferPriceElements.size(); i++) {
            String rawPrice = top3OfferPriceElements.get(i).getAttribute("content");
            double compariPrice = Double.parseDouble(rawPrice);
            if (item.getPrice() > compariPrice) {
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

    private boolean isFirstItemFoundByBrand(String brand) {
        String capitalized = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        boolean status = false;
        try {
            status = this.findElementByXpath("(//h2/a[contains(@title,'" + capitalized + "')])[1]").isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("FIRST Item not found on compari");
        }
        return status;
    }

    private boolean isItemFound(String productCode) {
        boolean status = false;
        try {
            status = this.findElementByXpath("//a[contains(@title,'" + productCode + "')]").isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Item not found on compari");
        }
        return status;
    }

}
