package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.pacfs.framework.base.Base;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.pacfs.test.pages.HearingOutcomesPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sortResultedCasesStepdefs extends Base {

    @And("the Resulted Cases tab is displayed")
    public void theResultedCasesTabIsDisplayed() {

        CurrentPage.as(HearingOutcomesPage.class).ClickResultedCasesTab();
    }

    @And("the Resulted Cases table contains multiple cases")
    public void theResultedCasesTableContainsMultipleCases() {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class)
                        .isTextDisplayed()
        );
    }

    @Given("the Resulted Cases page has loaded")
    public void theResultedCasesPageHasLoaded() {

        theResultedCasesTabIsDisplayed();
    }

    @Then("cases should be sorted alphabetically by Defendant Last Name from Z to A")
    public void casesShouldBeSortedAlphabeticallyByDefendantLastNameFromZToA() {

        List<String> actualLastNames =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllInProgressDefendantLastNames();

        List<String> expectedLastNames = new ArrayList<>(actualLastNames);

        expectedLastNames.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        Assert.assertEquals(
                actualLastNames,
                expectedLastNames,
                "Resulted Cases are NOT sorted alphabetically by Defendant Last Name (Z-A)");
    }
}
