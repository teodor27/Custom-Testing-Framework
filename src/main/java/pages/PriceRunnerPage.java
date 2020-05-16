package pages;

import model.Item;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PriceRunnerPage extends BasePage {

    private static final String SEARCH_BOX_ID = "search";
    private static final String REVIEW_CSS = "a > div > div > div > span > div > div > span";

    public void openNewTab() {
        String link = "window.open('https://www.pricerunner.com/t/1/Sound-and-Vision');";
        ((JavascriptExecutor) getDriver()).executeScript(link);
    }

    public void collectProductInformation() {
        String searchItem;
        for (Item item : itemList) {
            this.sleep(800);
            System.out.println("Searching PriceRunner for: " + item.getBrand() + " " + item.getProductCode());
            WebElement searchBox = this.waitUntilPageIsLoadedById(SEARCH_BOX_ID);
            searchItem = item.getBrand() + " " + item.getProductCode();
            searchBox.sendKeys(Keys.CONTROL + "a");
            searchBox.sendKeys(Keys.DELETE);
            sleep(1500);
            searchBox.sendKeys(searchItem);
            sleep(1500);
            searchBox.sendKeys(Keys.ENTER);
            this.sleep(800);
            if (isItemFound(item.getProductCode())) {
                String ratingText = this.findElementByCssSelector(REVIEW_CSS).getText();
                double rating = Double.parseDouble(ratingText) * 1000;
                item.incrementSelectionPoints((int) rating);
            } else {
                System.out.println("Trying by first item found....");
                if (isFirstItemFoundByBrand(item.getBrand()) && isReviewDisplayed()) {
                    String ratingText = this.findElementByCssSelector(REVIEW_CSS).getText();
                    System.out.println("Star rating FIRST FOUND = " + ratingText);
                    double rating = Double.parseDouble(ratingText) * 1000;
                    item.incrementSelectionPoints((int) rating);
                }
            }
            if (item.getSelectionPoints() != 0)
                System.out.println("CALCULATED SELECTION POINT FOR " + item.getName() + " " + item.getSelectionPoints());
        }
    }

    private boolean isFirstItemFoundByBrand(String brand) {
        String capitalBrandName = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        boolean status = false;
        try {
            status = this.findElementByXpath("(//div[contains(text(),'" + capitalBrandName + "')])[1]").isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Item not found on price runner");
        }
        return status;
    }

    private boolean isItemFound(String productCode) {
        boolean status = false;
        try {
            status = this.findElementByXpath("//div[contains(@title,'" + productCode + "')]").isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Item not found on price runner");
        }
        return status;
    }

    private boolean isReviewDisplayed() {
        boolean status;
        try {
            status = findElementByCssSelector(REVIEW_CSS).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Price Runner review not displayed");
            status = false;
        }
        return status;
    }

}
