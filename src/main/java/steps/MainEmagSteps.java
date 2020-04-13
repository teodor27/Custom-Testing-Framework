package steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.SoftAssertions;
import pages.MainEmagPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.*;

public class MainEmagSteps extends ScenarioSteps {
    MainEmagPage mainEmagPage;
    SoftAssertions softAssertions;

    @Step()
    public void selectSearchBox() {
        mainEmagPage.clickSearchBox();
        assertThat(mainEmagPage.isSearchBoxOpen()).isTrue();
    }

    @Step()
    public void searchForItem(String input) {
        mainEmagPage.typeIntoSearchBox(input);
        mainEmagPage.pressSearch();
    }

    @Step()
    public void checkItemsAreFound() {
        assertThat(mainEmagPage.areAnyItemsFound()).isTrue();
    }

    @Step
    public void sortByNumberOfReviews() {
        softAssertions = new SoftAssertions();
        mainEmagPage.clickSortButton();
        mainEmagPage.sortByReviewNumber();
        softAssertions.assertThat(mainEmagPage.checkPageIsSortedByNumberOfReviews()).isTrue();
        softAssertions.assertAll();
    }
}
