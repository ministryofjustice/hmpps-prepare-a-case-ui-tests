package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.test.pages.CaseSummaryPage;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.pacfs.test.pages.MyCourtsPage;
import org.testng.Assert;

import java.awt.*;

public class caseSummaryPageStepdefs extends Base {

    @Given("^I navigate to \"([^\"]*)\" tab$")
    public void caseSummaryPageStepdefs(String value) {

        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).GetHearingOutcomeStillToBeAdded(),value);
    }


    @When("I select a defendant from the case list")
    public void iSelectADefendantFromTheCaseList() {

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @Then("I should be navigated to the Case Summary page")
    public void iShouldBeNavigatedToTheCaseSummaryPage() {

        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CaseSummaryPage.class).GetCaseSummary(),"Case summary");
    }

    @And("the defendant name should match the value from the cases page")
    public void theDefendantNameShouldMatchTheValueFromTheCasesPage() {

        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class).ValidateDefendantNameMatches();
    }

    @And("the probation status should match the value from the cases page")
    public void theProbationStatusShouldMatchTheValueFromTheCasesPage() {

        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class).ValidateProbationStatusMatches2();
    }

    @Given("I am on the Case Summary page")
    public void iAmOnTheCaseSummaryPage() {

        DriverContext.waitForPageToLoad();
        //CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertEquals(CurrentPage.as(CaseSummaryPage.class).GetCaseSummary(),"Case summary");
    }

    @When("I am on Case Summary page")
    public void iAmOnCaseSummaryPage() {

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertEquals(CurrentPage.as(CaseSummaryPage.class).GetCaseSummary(),"Case summary");
    }

    @When("I add a comment with notes and observations about the case")
    public void iAddACommentWithNotesAndObservationsAboutTheCase() {

        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class).addHearingNote("Suggested Assertion Example");
    }

    @Then("the comment should be saved and visible to colleagues")
    public void theCommentShouldBeSavedAndVisibleToColleagues() {

        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CaseSummaryPage.class).getSavedHearingNoteText(),"Suggested Assertion Example");
    }

    @And("the Edit and Cancel buttons are displayed.")
    public void theEditAndCancelButtonsAreDisplayed() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CaseSummaryPage.class).isEditLinkDisplayed());
        Assert.assertTrue(CurrentPage.as(CaseSummaryPage.class).isDeleteLinkDisplayed());
    }

    @And("I delete the hearing note that was just created")
    public void iDeleteTheHearingNoteThatWasJustCreated() {

        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class).clickDeleteHearingNote();
    }

    @And("the success message {string} should be displayed")
    public void theSuccessMessageShouldBeDisplayed(String successfullyMsg) {

        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CaseSummaryPage.class).getDeleteSuccessMessage(), successfullyMsg);
    }

    @When("I update the hearing note by clicking the Edit button")
    public void iUpdateTheHearingNoteByClickingTheEditButton() {

        CurrentPage.as(CaseSummaryPage.class).editHearingNote();
    }

    @When("I upload the file {string}")
    public void iUploadTheFile(String fileName) throws AWTException, InterruptedException {

        CurrentPage.as(CaseSummaryPage.class).uploadFile(fileName);
    }

    @Then("the uploaded file {string} should be displayed")
    public void uploadedFileShouldBeDisplayed(String fileName) {

        CurrentPage.as(CaseSummaryPage.class).isUploadedFileDisplayed(fileName);
    }
}