package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTestClass extends ScenarioSteps {

    @Managed(driver = "chrome", uniqueSession = true)
    protected WebDriver webDriver;

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();

//        System.setProperty("webDriver.chrome.driver" , "");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        options.addArguments("disable-infobars");
//        options.addArguments("headless");

//        webDriver = new ChromeDriver(options);
    }

//    @After()
//    public void quit() {
//        webDriver.quit();
//    }

}