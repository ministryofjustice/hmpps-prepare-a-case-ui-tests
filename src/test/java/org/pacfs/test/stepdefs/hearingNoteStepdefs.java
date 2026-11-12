package org.pacfs.test.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import org.pacfs.framework.base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.utilities.CourtCaseServiceClient;
import org.pacfs.test.pages.CaseSummaryPage;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;


public class hearingNoteStepdefs extends Base {

    @Given("I open a defendant with a hearing scheduled for today")
    public void IOpenADefendantWithAHearingScheduledForToday() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @And("I navigate to the Case Summary page")
    public void iNavigateToTheCaseSummaryPage() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CaseSummaryPage.class).GetCaseSummary(),"Case summary");
    }

    @And("a hearing note exists for the hearing")
    public void aHearingNoteExistsForTheHearing() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class).ensureHearingNoteExists("Automation Testing");
    }

    @Then("the {string} button should be displayed")
    public void theButtonShouldBeDisplayed(String buttonName) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class).isSendOutcomeToAdminButtonDisplayed(),
                buttonName + " button is not displayed"
        );
    }

    @And("the button should be displayed below the hearing details")
    public void theButtonShouldBeDisplayedBelowTheHearingDetails() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class).isButtonDisplayedBelowHearingDetails(),
                "Button is not displayed below hearing details"
        );
    }

    @And("the button should be displayed above the hearing notes")
    public void theButtonShouldBeDisplayedAboveTheHearingNotes() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class).isButtonDisplayedAboveHearingNotes(),
                "Button is not displayed above hearing notes"
        );
    }

    @When("I open a defendant with a hearing scheduled in the past")
    public void iOpenADefendantWithAHearingScheduledInThePast() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectPreviousDate();
        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

    }

    @And("the previous date button should be displayed above the hearing notes")
    public void thePreviousDateButtonShouldBeDisplayedAboveTheHearingNotes() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class).isButtonDisplayedAboveHearingNotesPrevious(),
                "Button is not displayed above hearing notes"
        );
    }

    @When("I open a defendant with a hearing scheduled in the future")
    public void iOpenADefendantWithAHearingScheduledInTheFuture() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectNextDate();
        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @Given("I create a future PACFS case with the following details:")
    public void iCreateAFuturePACFSCaseWithTheFollowingDetails(DataTable dataTable) {

        authClientStepdefs ref = new authClientStepdefs();
        ref.generateAuthenticationToken();
        //ref.token =null;

        Map<String, String> data =
                new HashMap<>(dataTable.asMap(String.class, String.class));

        // Replace TODAY with the actual current date
        if ("TODAY".equalsIgnoreCase(data.get("day"))) {

            String tomorrow = LocalDate.now()
                    .plusDays(1)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            data.put("day", tomorrow);

        }

        var response =
                ref.courtCaseServiceClient.createScenario(data);

        assertEquals(
                200,
                response.statusCode(),
                "PACFS test data creation failed: "
                        + response.asString()
        );
    }

    @Then("the {string} tag should be displayed in the upper right corner")
    public void theTagShouldBeDisplayedInTheUpperRightCorner(String tagName) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        if(tagName.equalsIgnoreCase("Future hearing")){

            Assert.assertTrue(
                    CurrentPage.as(CaseSummaryPage.class)
                            .isTFutureHearingTagDisplayed(),
                    tagName + " tag is not displayed"
            );
        }else {
            Assert.assertTrue(
                    CurrentPage.as(CaseSummaryPage.class)
                            .isTodaysHearingTagDisplayed(),
                    tagName + " tag is not displayed"
            );
        }
    }

    @And("the tag should be displayed as a green label")
    public void theTagShouldBeDisplayedAsAGreenLabel() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .isTodaysHearingTagGreen(),
                "Today's hearing tag is not displayed as a green label"
        );
    }

    @When("the hearing details are displayed")
    public void theHearingDetailsAreDisplayed() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .isHearingDetailsDisplayed(),
                "Hearing details are not displayed"
        );
    }

    @Then("the hearing note prompt should display {string}")
    public void theHearingNotePromptShouldDisplay(String addNote) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        Assert.assertEquals(
                CurrentPage.as(CaseSummaryPage.class)
                        .getAddNoteForTodayHearing(),
                addNote
        );
    }

    @When("I send an outcome to admin")
    public void iSendAnOutcomeToAdmin() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        CurrentPage.as(CaseSummaryPage.class).sendOutcomeToAdmin();
        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        CurrentPage.as(CaseSummaryPage.class).selectOutcomeType("PROBATION_SENTENCE");
        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        CurrentPage.as(CaseSummaryPage.class).clickSendToAdminInModal();
        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
    }

    @Then("the outcome should be displayed on a green background")
    public void theOutcomeShouldBeDisplayedOnAGreenBackground() {

        DriverContext.waitForPageToLoad();

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .isOutcomeDisplayedOnGreenBackground(),
                "Outcome success banner is not displayed on a green background"
        );

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .isOutcomeSentToAdminDisplayed(),
                "Outcome sent to admin message is not displayed"
        );
    }

    @Then("the {string} button should not be displayed")
    public void theButtonShouldNotBeDisplayed(String buttonName) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        Assert.assertFalse(
                CurrentPage.as(CaseSummaryPage.class)
                        .isSendOutcomeToAdminButtonDisplayed(),
                buttonName + " button is displayed"
        );
    }

    @And("I should not be able to submit an outcome for the hearing")
    public void iShouldNotBeAbleToSubmitAnOutcomeForTheHearing() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        Assert.assertFalse(
                CurrentPage.as(CaseSummaryPage.class)
                        .isSendOutcomeToAdminButtonDisplayed(),
                "User is able to submit an outcome for the hearing"
        );
    }

    @And("the tag should be displayed as a blue label")
    public void theTagShouldBeDisplayedAsABlueLabel() {

        DriverContext.waitForPageToLoad();

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .isFutureHearingTagBlue(),
                "Future hearing tag is not displayed as a blue label"
        );
    }
}
