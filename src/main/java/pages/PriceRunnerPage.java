package pages;

import model.Item;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PriceRunnerPage extends BasePage {

    private static final String PRICE_RUNNER_PAGE_TITLE = "PriceRunner UK - Compare Prices and find the best deals";
    private static final String SEARCH_BOX_ID = "search";
    private static final String REVIEW_XPATH = "//*[@id='content-below-header']/div/div/div[4]/div/div/div[1]/div/a/div/div/div[2]/span/div/div[2]/span";

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
                String ratingText = this.findElementByXpath(REVIEW_XPATH).getText();
                double rating = Double.parseDouble(ratingText) * 1000;
                item.setSelectionPoints((int) rating);
//            } else {
//                System.out.println("Trying by first item found....");
//                if (isFirstItemFoundByBrand(item.getBrand())) {
//                    String brandName = item.getBrand();
//                    String capitalizedBrandName = brandName.substring(0, 1).toUpperCase() + brandName.substring(1).toLowerCase();
//                    this.findElementByXpath("(//h2/a[contains(@title,'" + capitalizedBrandName + "')])[1]").click();
//                    String ratingText = this.findElementByCssSelector(REVIEW_CSS).getAttribute("title");
//                    System.out.println("Star rating FIRST FOUND = " + ratingText);
//                    double rating = Double.parseDouble(ratingText) * 1000;
//                    item.setSelectionPoints((int) rating);
//                }
            }
            if (item.getSelectionPoints() != 0)
                System.out.println("CALCULATED SELECTION POINT FOR " + item.getName() + " " + item.getSelectionPoints());
        }
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
            status = findElementByXpath(REVIEW_XPATH).isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Price Runner review not displayed");
            status = false;
        }
        return status;
    }

}
