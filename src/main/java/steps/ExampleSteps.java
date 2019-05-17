package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import static org.assertj.core.api.Assertions.assertThat;


import pages.BasePage;

public class ExampleSteps extends ScenarioSteps {
    BasePage basePage;

    @Step()
    public void clickMail() {
        basePage.clickMail();
    }

    @Step()
    public void checkMailRedirect() {
        assertThat(basePage.isPreSignInPageDisplayed()).isTrue();
    }

    @Step()
    public void clickSignInPageButton() {
        basePage.clickSignIn();
    }
}

