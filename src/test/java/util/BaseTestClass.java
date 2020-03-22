package util;

import net.thucydides.core.annotations.Managed;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;


public class BaseTestClass {

    @Managed()
    WebDriver webDriver;

    @Before()
    public void openBrowser() {
        webDriver.navigate().to("https://emag.ro");
        webDriver.manage().window().maximize();
    }

    @After()
    public void quit() {
        webDriver.quit();
    }

}