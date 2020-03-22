package example;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ExampleSteps;
import util.BaseTestClass;

@RunWith(SerenityRunner.class)
public class ExampleTest extends BaseTestClass {

    @Steps
    ExampleSteps exampleSteps;


    @Title("Go to mail")
    @Test()
    public void test1RedirectToMail() {
        exampleSteps.selectSearchBox();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        exampleSteps.checkMailRedirect();
    }

    @Title("Go to Sign In Page")
    @Test
    public void test2Login() {
        exampleSteps.selectSearchBox();
//        exampleSteps.clickSignInPageButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
