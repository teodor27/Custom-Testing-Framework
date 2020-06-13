package steps;

import net.thucydides.core.annotations.Step;
import pages.EmagProductPage;

public class EmagProductSteps extends BaseSteps {
    EmagProductPage emagProductPage;

    @Step
    public void collectProductInformation() {
        emagProductPage.collectProductInfo();
    }

}
