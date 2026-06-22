package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.test.pages.CaseSummaryPage;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.testng.Assert;

public class caseSummaryPageStepdefs extends Base {

    @Given("^I navigate to \"([^\"]*)\" tab$")
    public void caseSummaryPageStepdefs(String value) {

        Assert.assertEquals(CurrentPage.As(CourtCasesDetailsPage.class).GetHearingOutcomeStillToBeAdded(),value);
    }


    @When("I select a defendant from the case list")
    public void iSelectADefendantFromTheCaseList() {

        CurrentPage = CurrentPage.As(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @Then("I should be navigated to the Case Summary page")
    public void iShouldBeNavigatedToTheCaseSummaryPage() {

        Assert.assertEquals(CurrentPage.As(CaseSummaryPage.class).GetCaseSummary(),"Case summary");
    }

    @And("the defendant name should match the value from the cases page")
    public void theDefendantNameShouldMatchTheValueFromTheCasesPage() {

        CurrentPage.As(CaseSummaryPage.class).ValidateDefendantNameMatches();
    }

    @And("the probation status should match the value from the cases page")
    public void theProbationStatusShouldMatchTheValueFromTheCasesPage() {

        CurrentPage.As(CaseSummaryPage.class).ValidateProbationStatusMatches2();
    }

    @Given("I am on the Case Summary page")
    public void iAmOnTheCaseSummaryPage() {

        CurrentPage = CurrentPage.As(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertEquals(CurrentPage.As(CaseSummaryPage.class).GetCaseSummary(),"Case summary");
    }

    @When("I add a comment with notes and observations about the case")
    public void iAddACommentWithNotesAndObservationsAboutTheCase() {

        CurrentPage.As(CaseSummaryPage.class).addHearingNote("Suggested Assertion Example");
    }

    @Then("the comment should be saved and visible to colleagues")
    public void theCommentShouldBeSavedAndVisibleToColleagues() {

        Assert.assertEquals(CurrentPage.As(CaseSummaryPage.class).getSavedHearingNoteText(),"Suggested Assertion Example");
    }

    @And("the Edit and Cancel buttons are displayed.")
    public void theEditAndCancelButtonsAreDisplayed() {

        Assert.assertTrue(CurrentPage.As(CaseSummaryPage.class).isEditLinkDisplayed());
        Assert.assertTrue(CurrentPage.As(CaseSummaryPage.class).isDeleteLinkDisplayed());
    }

    @And("I delete the hearing note that was just created")
    public void iDeleteTheHearingNoteThatWasJustCreated() {

        CurrentPage.As(CaseSummaryPage.class).clickDeleteHearingNote();
    }

    @And("the success message {string} should be displayed")
    public void theSuccessMessageShouldBeDisplayed(String successfullyMsg) {

        Assert.assertEquals(CurrentPage.As(CaseSummaryPage.class).getDeleteSuccessMessage(), successfullyMsg);
    }

    @When("I update the hearing note by clicking the Edit button")
    public void iUpdateTheHearingNoteByClickingTheEditButton() {

        CurrentPage.As(CaseSummaryPage.class).editHearingNote();
    }
}
