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
import org.pacfs.test.pages.*;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sortCasesUnderOutcomeStepdefs extends Base {

    @Given("I am logged in as a Case Admin")
    public void iAmLoggedInAsACaseAdmin() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }

    @And("I navigate to the Cases to Result page under the Outcomes flow")
    public void iNavigateToTheCasesToResultPageUnderTheOutcomesFlow() {

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();

        DriverContext.waitForPageToLoad();
    }

    @And("the Cases to Result table has loaded successfully")
    public void theCasesToResultTableHasLoadedSuccessfully() {

        //no code
    }

    @Given("the Cases to Result page has loaded")
    public void theCasesToResultPageHasLoaded() {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class).isCasesToResultHeaderDisplayed(),
                "Cases to Result page heading is not displayed"
        );
        Settings.logs.write("the Cases to Result page has loaded");
        System.out.println("the Cases to Result page has loaded");
    }

    @When("the results are displayed")
    public void theResultsAreDisplayed() {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class).isResultsTableDisplayed(),
                "Cases to Result table is not displayed"
        );
    }

    @Then("the cases should be sorted by Hearing date with the oldest hearing first")
    public void theCasesShouldBeSortedByHearingDateWithTheOldestHearingFirst() {

        CurrentPage.as(HearingOutcomesPage.class).validateCasesAreSortedByOldestHearingDate();
    }

    @And("the column heading should display {string}")
    public void theColumnHeadingShouldDisplay(String expectedHeading) {

        if(expectedHeading.equalsIgnoreCase("Hearing date (oldest)")){

            Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).getHearingDateColumnHeading(),expectedHeading);

        } else if(expectedHeading.equalsIgnoreCase("Hearing date (newest)")){

            Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).getHearingDateColumnHeading(),expectedHeading);

        } else if (expectedHeading.equalsIgnoreCase("Defendant (A-Z)") || expectedHeading.equalsIgnoreCase("Defendant (Z-A)")) {

            Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).getDefendantLastNameColumnHeading(),expectedHeading);

        } else if (expectedHeading.equalsIgnoreCase("Probation Status")) {

            Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).getProbationStatusColumnHeading(),expectedHeading);
        }

    }

    @And("cases are sorted by Hearing date oldest first")
    public void casesAreSortedByHearingDateOldestFirst() {

//        List<LocalDate> actualDates = CurrentPage.as(HearingOutcomesPage.class).getAllHearingDates();
//
//        List<LocalDate> expectedDates = new ArrayList<>(actualDates);
//
//        expectedDates.sort(Collections.reverseOrder());
//
//        Assert.assertEquals(
//                actualDates,
//                expectedDates,
//                "Cases are NOT sorted by Hearing Date (Newest -> Oldest)");

        List<LocalDate> actualDates =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllHearingDates();

        List<LocalDate> expectedDates =
                new ArrayList<>(actualDates);

        Collections.sort(expectedDates);

        Assert.assertEquals(
                actualDates,
                expectedDates,
                "Cases are NOT sorted by Hearing Date (Oldest -> Newest)");
    }

    @When("I click the sorting arrow on the Hearing date column")
    public void iClickTheSortingArrowOnTheHearingDateColumn() {

        CurrentPage.as(HearingOutcomesPage.class).clickHearingDateSortingArrow();
    }

    @Then("the cases should be sorted by the most recent hearing date first")
    public void theCasesShouldBeSortedByTheMostRecentHearingDateFirst() {

//        List<LocalDate> actualDates = CurrentPage.as(HearingOutcomesPage.class).getAllHearingDates2();
//
//        List<LocalDate> expectedDates = new ArrayList<>(actualDates);
//
//        expectedDates.sort(Collections.reverseOrder());
//
//        Assert.assertEquals(
//                actualDates,
//                expectedDates,
//                "Cases are NOT sorted by Hearing Date (Newest -> Oldest)");

        List<LocalDate> actualDates =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllHearingDates();

        List<LocalDate> expectedDates =
                new ArrayList<>(actualDates);

        expectedDates.sort(Collections.reverseOrder());

        Assert.assertEquals(
                actualDates,
                expectedDates,
                "Cases are NOT sorted by Hearing Date (Newest -> Oldest)");
    }

    @And("the oldest hearings should appear at the bottom of the list")
    public void theOldestHearingsShouldAppearAtTheBottomOfTheList() {

        List<LocalDate> hearingDates = CurrentPage.as(HearingOutcomesPage.class).getAllHearingDates2();

        Assert.assertFalse(
                hearingDates.isEmpty(),
                "No hearing dates found");

        LocalDate bottomDate =
                hearingDates.get(hearingDates.size() - 1);

        LocalDate oldestDate =
                Collections.min(hearingDates);

        Assert.assertEquals(
                bottomDate,
                oldestDate,
                "Oldest hearing date is not displayed at the bottom of the list");

        Settings.logs.write(
                "Oldest hearing date correctly displayed at bottom: "
                        + bottomDate);
    }

    @When("I click the sorting arrow on the Defendant column")
    public void iClickTheSortingArrowOnTheDefendantColumn() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickDefendantSortingArrow();
        Settings.logs.write("I click the sorting arrow on the Defendant column");
        System.out.println("I click the sorting arrow on the Defendant column");
    }

    @Then("the cases should be sorted alphabetically from A to Z using the defendant last name")
    public void theCasesShouldBeSortedAlphabeticallyFromAToZUsingTheDefendantLastName() {

        Settings.logs.write("trying to sort alphabetically from A to Z by the defendant last name");
        System.out.println("trying to sort alphabetically from A to Z by the defendant last name");
        List<String> actualLastNames =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllDefendantLastNames();

        List<String> expectedLastNames = new ArrayList<>(actualLastNames);

        expectedLastNames.sort(String.CASE_INSENSITIVE_ORDER);

        Settings.logs.write("Now sorted alphabetically from A to Z by the defendant last name");
        System.out.println("Now sorted alphabetically from A to Z by the defendant last name");

        Assert.assertEquals(
                actualLastNames,
                expectedLastNames,
                "Cases are NOT sorted alphabetically by Defendant Last Name (A-Z)");
    }

    @Given("cases are currently sorted by Defendant last name from A to Z")
    public void casesAreCurrentlySortedByDefendantLastNameFromAToZ() {

        DriverContext.waitForPageToLoad();
        theCasesToResultPageHasLoaded();
        iClickTheSortingArrowOnTheDefendantColumn();
        theCasesShouldBeSortedAlphabeticallyFromAToZUsingTheDefendantLastName();
    }

    @Then("the cases should be sorted alphabetically from Z to A using the defendant last name")
    public void theCasesShouldBeSortedAlphabeticallyFromZToAUsingTheDefendantLastName() {

        List<String> actualLastNames =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllDefendantLastNames();

        List<String> expectedLastNames = new ArrayList<>(actualLastNames);

        expectedLastNames.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

        Assert.assertEquals(
                actualLastNames,
                expectedLastNames,
                "Cases are NOT sorted alphabetically by Defendant Last Name (Z-A)");
    }

    @When("I click the sorting arrow on the Probation Status column")
    public void iClickTheSortingArrowOnTheProbationStatusColumn() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickProbationStatusSortingArrow();
        System.out.println("I click the sorting arrow on the Probation Status column");
    }

    @Then("the cases should be sorted in the following order:")
    public void theCasesShouldBeSortedInTheFollowingOrder(DataTable dataTable) {

        System.out.println("trying to sort in the following order: Probation Status, Current, Previously known, No record");
        List<String> expectedOrder = new ArrayList<>(dataTable.asList());

        // Remove the header row
        expectedOrder.remove(0);

        List<String> actualStatuses =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllProbationStatuses();

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

    @Given("cases are currently sorted by Probation Status in the following order:")
    public void casesAreCurrentlySortedByProbationStatusInTheFollowingOrder(DataTable dataTable) {

        theCasesToResultPageHasLoaded();
        iClickTheSortingArrowOnTheProbationStatusColumn();
        theCasesShouldBeSortedInTheFollowingOrder(dataTable);
    }

    @Then("the cases should be sorted in the following reverse order:")
    public void theCasesShouldBeSortedInTheFollowingReverseOrder(DataTable dataTable) {

        List<String> expectedOrder = dataTable.asList(String.class);

        // Remove table header
        expectedOrder.remove(0);

        List<String> actualStatuses =
                CurrentPage.as(HearingOutcomesPage.class)
                        .getAllProbationStatuses();

        // Remove any blank values
        actualStatuses.removeIf(String::isEmpty);

        // Create expected sequence based only on available statuses
        List<String> filteredExpectedOrder = expectedOrder.stream()
                .filter(actualStatuses::contains)
                .toList();


        List<String> sortedExpectedStatuses = new ArrayList<>();

        for (String expectedStatus : filteredExpectedOrder) {

            for (String actualStatus : actualStatuses) {

                if (actualStatus.equalsIgnoreCase(expectedStatus)) {
                    sortedExpectedStatuses.add(actualStatus);
                }
            }
        }

        Assert.assertEquals(
                actualStatuses,
                sortedExpectedStatuses,
                "Cases are NOT sorted by Probation Status in reverse order (No record -> Previously known -> Current)"
        );
    }

    @Given("I have sorted the cases by Defendant from A to Z")
    public void iHaveSortedTheCasesByDefendantFromAToZ() {

        theCasesToResultPageHasLoaded();
        iClickTheSortingArrowOnTheDefendantColumn();
        theCasesShouldBeSortedAlphabeticallyFromAToZUsingTheDefendantLastName();

    }

    @When("I navigate back to the previous page")
    public void iNavigateBackToThePreviousPage() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickPreviousPage();
    }

    @When("I navigate to the next page of results")
    public void iNavigateToTheNextPageOfResults() {

        CurrentPage.as(HearingOutcomesPage.class)
                .clickNextPage();
    }

    @Then("the Defendant sorting order should remain A to Z")
    public void theDefendantSortingOrderShouldRemainAToZ() {

        theCasesShouldBeSortedAlphabeticallyFromAToZUsingTheDefendantLastName();
    }

    @Given("I have sorted the cases by Hearing date newest first")
    public void iHaveSortedTheCasesByHearingDateNewestFirst() {

        iClickTheSortingArrowOnTheHearingDateColumn();
        theCasesShouldBeSortedByTheMostRecentHearingDateFirst();
    }

    @When("I open a case from the results list")
    public void iOpenACaseFromTheResultsList() {

        CurrentPage = CurrentPage.as(HearingOutcomesPage.class)
                .selectFirstCaseFromResults();

        CurrentPage.as(CaseSummaryPage.class)
                .clickViewWithoutAssigningIfDisplayed();
    }

    @And("I return to the Cases to Result page")
    public void iReturnToTheCasesToResultPage() {

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).clickBackArrow();
    }

    @Then("the Hearing date sorting should remain as newest first")
    public void theHearingDateSortingShouldRemainAsNewestFirst() {

        theOldestHearingsShouldAppearAtTheBottomOfTheList();
    }

    @Given("I have sorted the cases by Defendant A-Z")
    public void iHaveSortedTheCasesByDefendantAZ() {

        theCasesToResultPageHasLoaded();
        iClickTheSortingArrowOnTheDefendantColumn();
        theCasesShouldBeSortedAlphabeticallyFromAToZUsingTheDefendantLastName();
    }

    @Then("the Defendant sorting should remain A-Z")
    public void theDefendantSortingShouldRemainAZ() {

        theCasesShouldBeSortedAlphabeticallyFromAToZUsingTheDefendantLastName();
    }
}
