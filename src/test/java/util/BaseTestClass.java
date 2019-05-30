package util;

import net.thucydides.core.annotations.Managed;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class BaseTestClass {

    @Managed()
    WebDriver webDriver;

    @Before()
    public void openBrowser() {
        webDriver.navigate().to("https://ro.yahoo.com");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.cssSelector(".btn.primary")).click(); //close popup
    }

    @After()
    public void quit() {
        webDriver.quit();
    }

}