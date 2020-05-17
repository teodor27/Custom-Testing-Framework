package pages;

import model.Item;
import org.openqa.selenium.JavascriptExecutor;

public class EmagProductPage extends BasePage {

    private static final String PRODUCT_CODE_CSS = ".product-code-display";
    private static final String BRAND_CSS = ".disclaimer-section a";


    public void openNewTab() {
        String link = "window.open('https://www.emag.ro/homepage');";
        ((JavascriptExecutor) getDriver()).executeScript(link);
    }


    public void collectProductInfo() {
        this.openNewTab();
        this.switchToTab(1);
        for (Item item : itemList) {
            this.sleep(1000);
            System.out.println("Accessing URL: " + item.getUrl());
            getDriver().navigate().to(item.getUrl());
            this.waitUntilPageIsLoadedByCss(PRODUCT_CODE_CSS);
            String productCodeText = this.findElementByCssSelector(PRODUCT_CODE_CSS).getText().split(": ")[1];
            item.setProductCode(productCodeText);
            String brand = this.findElementByCssSelector(BRAND_CSS).getText();
            item.setBrand(brand);
        }
    }
}
