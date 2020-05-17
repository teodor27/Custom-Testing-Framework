package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import pages.EmagProductPage;

public class EmagProductSteps extends ScenarioSteps {
    EmagProductPage emagProductPage;

    @Step
    public void collectProductInformation() {
        emagProductPage.collectProductInfo();
    }

}
