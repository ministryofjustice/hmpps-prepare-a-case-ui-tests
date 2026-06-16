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
}
