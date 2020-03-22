package flow;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.MainEmagSteps;
import util.BaseTestClass;

@RunWith(SerenityRunner.class)
public class MainEmagPageTest extends BaseTestClass {

    @Steps
    MainEmagSteps mainEmagSteps;


    @Title("Go to Emag Main Page")
    @Test()
    public void testSearchEmag() {
        mainEmagSteps.selectSearchBox();
        //TODO implement inpunt collection form file
        mainEmagSteps.searchForItem("Masina de spalat");
        //TODO make check that the search has relevant results
        mainEmagSteps.checkItemsAreFound();

    }

}
