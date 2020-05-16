package pages;

import model.Item;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CompariPage extends BasePage {

    private static final String SEARCH_BOX_ID = "st";
    private static final String REVIEW_CSS = ".hidden-xs [data-tab-name='pareri']";
    private static final String SEARCH_BUTTON_CSS = ".btn-primary.button-search";

    public void openNewTab() {
        String link = "window.open('https://www.compari.ro');";
        ((JavascriptExecutor) getDriver()).executeScript(link);
    }

    public void collectProductInformation() {
        String searchItem;
        for (Item item : itemList) {
            this.sleep(800);
            System.out.println("Searching Compari.ro for: " + item.getBrand() + " " + item.getProductCode());
            WebElement searchBox = this.waitUntilPageIsLoadedById(SEARCH_BOX_ID);
            searchItem = item.getBrand() + " " + item.getProductCode();
            searchBox.clear();
            searchBox.sendKeys(searchItem);
            sleep(1000);
            this.findElementByCssSelector(SEARCH_BUTTON_CSS).click();
            this.sleep(800);
            if (isItemFound(item.getProductCode())) {
                this.findElementByXpath("//a[contains(@title,'" + item.getProductCode() + "')]").click();
                String ratingText = this.findElementByCssSelector(REVIEW_CSS).getAttribute("title");
                System.out.println("Star rating ELEMENT FOUND = " + ratingText);
                double rating = Double.parseDouble(ratingText) * 1000;
                item.setSelectionPoints((int) rating);
            } else {
                System.out.println("Trying by first item found....");
                if (isFirstItemFoundByBrand(item.getBrand())) {
                    String brandName = item.getBrand();
                    String capitalizedBrandName = brandName.substring(0, 1).toUpperCase() + brandName.substring(1).toLowerCase();
                    this.findElementByXpath("(//h2/a[contains(@title,'" + capitalizedBrandName + "')])[1]").click();
                    String ratingText = this.findElementByCssSelector(REVIEW_CSS).getAttribute("title");
                    System.out.println("Star rating FIRST FOUND = " + ratingText);
                    double rating = Double.parseDouble(ratingText) * 1000;
                    item.setSelectionPoints((int) rating);
                }
            }
            if (item.getSelectionPoints() != 0)
                System.out.println("CALCULATED SELECTION POINT FOR " + item.getName() + " " + item.getSelectionPoints());
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

    private boolean isReviewDisplayed() {
        boolean status;
        try {
            status = findElementByXpath(REVIEW_CSS).isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Price Runner review not displayed");
            status = false;
        }
        return status;
    }

}
