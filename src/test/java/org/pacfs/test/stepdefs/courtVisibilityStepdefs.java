package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.config.Settings;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.pacfs.test.pages.HearingOutcomesPage;
import org.pacfs.test.pages.MyCourtsPage;
import org.pacfs.test.pages.SignInPage;

public class courtVisibilityStepdefs extends Base {

    @Given("I am working within the PACFS service")
    public void iAmWorkingWithinThePACFSService() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @When("the Cases page loads")
    public void theCasesPageLoads() {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @Then("the currently selected court should be clearly visible above the Cases heading")
    public void theCurrentlySelectedCourtShouldBeClearlyVisibleAboveTheCasesHeading() {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @And("the court name should be displayed within the user's main view area")
    public void theCourtNameShouldBeDisplayedWithinTheUserSMainViewArea() {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @When("the Outcomes page loads")
    public void theOutcomesPageLoads() {

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
    }

    @Then("the currently selected court should be clearly visible above the Outcomes heading")
    public void theCurrentlySelectedCourtShouldBeClearlyVisibleAboveTheOutcomesHeading() {

        Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @And("the court name should remain visible on the page")
    public void theCourtNameShouldRemainVisibleOnThePage() {

        Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }
}