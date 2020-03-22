package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleSteps extends ScenarioSteps {
        BasePage basePage;

        @Step()
        public void selectSearchBox() {
//            basePage.clickSearchBox();
        }

//    @Step()
//    public void checkMailRedirect() {
//        assertThat(basePage.isPreSignInPageDisplayed()).isTrue();
//    }

//    @Step()
//    public void clickSignInPageButton() {
//        basePage.clickSignIn();
//    }
}

