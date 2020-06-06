package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTestCase extends ScenarioSteps {

    @Managed(driver = "chrome", uniqueSession = true)
    protected WebDriver webDriver;

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
    }

}
