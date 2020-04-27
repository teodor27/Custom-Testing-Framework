package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.GoogleSearchPage;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSteps extends ScenarioSteps {
    GoogleSearchPage googleSearchPage;

    @Step
    public void openNewGoogleTab() {
        googleSearchPage.openNewTab();
        googleSearchPage.switchToTab(1);
        googleSearchPage.navigateToGoogleSearchPage();
        assertThat(googleSearchPage.isGooglePageOpen()).isTrue();
    }

}
