package util;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSourceImpl implements DriverSource {


    @Override
    public WebDriver newDriver() {
        DesiredCapabilities cap = new DesiredCapabilities();

        WebDriver driver = null;
        driver = new ChromeDriver();

        return driver;
    }

    @Override
    public boolean takesScreenshots() {
        return false;
    }

}
