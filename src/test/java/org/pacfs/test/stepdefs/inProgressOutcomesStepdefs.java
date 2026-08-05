package org.pacfs.test.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.config.Settings;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.pacfs.test.pages.HearingOutcomesPage;
import org.pacfs.test.pages.MyCourtsPage;
import org.pacfs.test.pages.SignInPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class inProgressOutcomesStepdefs extends Base {

    @Given("the Case Admin is logged into the application")
    public void theCaseAdminIsLoggedIntoTheApplication() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }

    @And("the Case Admin has navigated to the Outcomes flow")
    public void theCaseAdminHasNavigatedToTheOutcomesFlow() {

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
    }

    @And("the In Progress tab is displayed")
    public void theInProgressTabIsDisplayed() {

        CurrentPage.as(HearingOutcomesPage.class).ClickInProgressTab();
    }

    @And("the In Progress table contains multiple cases")
    public void theInProgressTableContainsMultipleCases() {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class)
                        .isTextDisplayed()
        );
    }

    @Given("the In Progress page has loaded")
    public void theInProgressPageHasLoaded() {

        theInProgressTableContainsMultipleCases();
    }

    @When("the In Progress table is displayed")
    public void theInProgressTableIsDisplayed() {

        theInProgressTableContainsMultipleCases();
    }

    @Then("the cases should be sorted by Hearing Date from oldest to newest")
    public void theCasesShouldBeSortedByHearingDateFromOldestToNewest() {

        CurrentPage.as(HearingOutcomesPage.class).validateInProgressAreSortedByOldestHearingDate();
    }

    @When("the Case Admin clicks the Defendant sorting arrow")
    public void theCaseAdminClicksTheDefendantSortingArrow() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickDefendantSortingArrow();
        Settings.logs.write("I click the sorting arrow on the Defendant column");
        System.out.println("I click the sorting arrow on the Defendant column");
    }

    @Then("the cases should be sorted alphabetically by Defendant Last Name from A to Z")
    public void theCasesShouldBeSortedAlphabeticallyByDefendantLastNameFromAToZ() {

        Settings.logs.write("trying to sort alphabetically from A to Z by the defendant last name");
        System.out.println("trying to sort alphabetically from A to Z by the defendant last name");
        List<String> actualLastNames =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllInProgressDefendantLastNames();

        List<String> expectedLastNames = new ArrayList<>(actualLastNames);

        expectedLastNames.sort(String.CASE_INSENSITIVE_ORDER);

        Settings.logs.write("Now sorted alphabetically from A to Z by the defendant last name");
        System.out.println("Now sorted alphabetically from A to Z by the defendant last name");

        Assert.assertEquals(
                actualLastNames,
                expectedLastNames,
                "In progress are NOT sorted alphabetically by Defendant Last Name (A-Z)");
    }

    @Given("the Defendant column is sorted from A to Z")
    public void theDefendantColumnIsSortedFromAToZ() {

        theCaseAdminClicksTheDefendantSortingArrow();
        theCasesShouldBeSortedAlphabeticallyByDefendantLastNameFromAToZ();
    }

    @When("the Case Admin clicks the Defendant sorting arrow again")
    public void theCaseAdminClicksTheDefendantSortingArrowAgain() {

        theCaseAdminClicksTheDefendantSortingArrow();
    }

    @Then("the cases should be sorted alphabetically by Defendant Last Name from Z to A")
    public void theCasesShouldBeSortedAlphabeticallyByDefendantLastNameFromZToA() {

        List<String> actualLastNames =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllInProgressDefendantLastNames();

        List<String> expectedLastNames = new ArrayList<>(actualLastNames);

        expectedLastNames.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        Assert.assertEquals(
                actualLastNames,
                expectedLastNames,
                "In progress are NOT sorted alphabetically by Defendant Last Name (Z-A)");
    }

    @When("the Case Admin clicks the Probation Status sorting arrow")
    public void theCaseAdminClicksTheProbationStatusSortingArrow() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickProbationStatusSortingArrow();
        System.out.println("I click the sorting arrow on the Probation Status column");
    }

    @Then("the Defendant sorting should be reset")
    public void theDefendantSortingShouldBeReset() {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class)
                        .isDefendantSortingReset()
        );
    }

    @And("the Probation Status sorting should become active")
    public void theProbationStatusSortingShouldBecomeActive() {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class)
                        .isProbationStatusSortingActive()
        );
    }

    @Then("the cases should be sorted in the following order")
    public void theCasesShouldBeSortedInTheFollowingOrder(DataTable dataTable) {

        System.out.println("trying to sort in the following order: Probation Status, Current, Previously known, No record");
        List<String> expectedOrder = new ArrayList<>(dataTable.asList());

        // Remove the header row
        expectedOrder.remove(0);

        List<String> actualStatuses =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllInProgressProbationStatuses();
                        //.getAllProbationStatuses();

        int previousIndex = -1;

        for (String status : actualStatuses) {

            int currentIndex = expectedOrder.indexOf(status);

            Assert.assertTrue(
                    currentIndex >= previousIndex,
                    "Probation Status is not in the expected order.\nExpected: "
                            + expectedOrder + "\nActual: " + actualStatuses);

            previousIndex = currentIndex;
        }
    }

    @And("the Hearing Date sorting should be reset")
    public void theHearingDateSortingShouldBeReset() {

        CurrentPage.as(HearingOutcomesPage.class).verifyHearingDateSortingIsReset();
    }

    @Given("the Probation Status column is sorted ascending")
    public void theProbationStatusColumnIsSortedAscending() {

        theInProgressPageHasLoaded();
        theCaseAdminClicksTheProbationStatusSortingArrow();
    }

    @When("the Case Admin clicks the Probation Status sorting arrow again")
    public void theCaseAdminClicksTheProbationStatusSortingArrowAgain() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickProbationStatusSortingArrow();
        System.out.println("I click the sorting arrow on the Probation Status column again");
    }

    @Given("the Probation Status column is sorted")
    public void theProbationStatusColumnIsSorted() {

        theInProgressPageHasLoaded();
        theCaseAdminClicksTheProbationStatusSortingArrow();

    }

    @When("the Case Admin sorts by Defendant Last Name")
    public void theCaseAdminSortsByDefendantLastName() {

        theCaseAdminClicksTheDefendantSortingArrow();
    }

    @Then("the Probation Status sorting should be reset")
    public void theProbationStatusSortingShouldBeReset() {

        CurrentPage.as(HearingOutcomesPage.class).verifyProbationStatusSortingIsReset();
    }

    @And("the Defendant sorting should become active")
    public void theDefendantSortingShouldBecomeActive() {

        CurrentPage.as(HearingOutcomesPage.class).verifyDefendantSortingIsActive();
    }
}
