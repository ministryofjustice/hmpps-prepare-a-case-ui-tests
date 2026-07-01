package org.pacfs.test.stepdefs;

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

public class SessionStabilityAndNavigationReliabilityStepdefsStepdefs extends Base {

    @Given("^the user is logged into the application via \"([^\"]*)\"$")
    public void the_user_is_logged_into_the_application_via(String NameOfCourt) {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText(NameOfCourt);
    }


    @When("the user navigates to the Outcomes flow multiple times")
    public void theUserNavigatesToTheOutcomesFlowMultipleTimes() {

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
    }

    @Then("the user should remain logged in")
    public void theUserShouldRemainLoggedIn() {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).IsSignOutLinkPresent());
    }

    @And("the system should not display {string}")
    public void theSystemShouldNotDisplay(String arg0) {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).ValidateSessionStability());
    }

    @And("the session should remain active without unexpected logout")
    public void theSessionShouldRemainActiveWithoutUnexpectedLogout() {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).ValidateSessionUnexpectedLogout());
    }

    @When("the user navigates to {string}")
    public void theUserNavigatesTo(String value) {

        Assert.assertEquals(CurrentPage.as(MyCourtsPage.class).GetMyCourtsText(),value);
    }

    @And("the user changes the court multiple times consecutively")
    public void theUserChangesTheCourtMultipleTimesConsecutively() {

        CurrentPage.as(MyCourtsPage.class).NavigateThroughAllAvailableCourts();
    }

    @Then("the court should update successfully")
    public void theCourtShouldUpdateSuccessfully() {

        Assert.assertTrue(CurrentPage.as(MyCourtsPage.class).IsMyCourtsTextPresent());
    }

    @And("no service error should be displayed")
    public void noServiceErrorShouldBeDisplayed() {

        Assert.assertTrue(CurrentPage.as(MyCourtsPage.class).IsSignOutLinkPresent());
    }
}