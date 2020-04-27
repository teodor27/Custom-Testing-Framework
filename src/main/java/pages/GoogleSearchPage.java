package pages;

import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

public class GoogleSearchPage extends BasePage {

    private static final String SEARCH_BOX_ID = "searchboxTrigger";
    private static final String GOOGLE_PAGE_TITLE = "Google";

    public void openNewTab() {

        String link = "window.open('https://www.google.com');";
        ((JavascriptExecutor)getDriver()).executeScript(link);
    }

    public void navigateToGoogleSearchPage() {
        getDriver().get("https://www.google.com");
    }

    public void switchToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(tabIndex));
    }

    public boolean isGooglePageOpen() {
        return getDriver().getTitle().equals(GOOGLE_PAGE_TITLE);
    }

}
