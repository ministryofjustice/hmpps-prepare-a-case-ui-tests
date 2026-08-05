package org.pacfs.test.stepdefs;

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

public class courtsSwitcherStepdefdefs extends Base {

    @Given("I am on the Cases page")
    public void iAmOnTheCasesPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

    }

    @When("I open the {string} switcher")
    public void iOpenTheSwitcher(String value) {

        Assert.assertEquals(CurrentPage.as(MyCourtsPage.class).GetMyCourtsText(),value);
    }

    @Then("the My Courts component should be displayed in the new location")
    public void theMyCourtsComponentShouldBeDisplayedInTheNewLocation() {

        //no code
    }

    @And("I should be able to select another court")
    public void iShouldBeAbleToSelectAnotherCourt() {

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @And("the selected court should become my active court")
    public void theSelectedCourtShouldBecomeMyActiveCourt() {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).ConfirmActiveCourtOxfordAndSouthern());
    }

    @Given("I have switched to another court using My Courts")
    public void iHaveSwitchedToAnotherCourtUsingMyCourts() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickMyCourts();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Guildford Magistrates' Court");
    }

    @When("I navigate to another PACFS page")
    public void iNavigateToAnotherPACFSPage() {

        //no code
    }

    @Then("the selected court should remain active")
    public void theSelectedCourtShouldRemainActive() {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).ConfirmActiveCourtGuildfordMagistrates());
    }

    @Given("I am on the Outcomes page")
    public void iAmOnTheOutcomesPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();

        DriverContext.waitForPageToLoad();
    }

    @Then("the My Courts component should be displayed in the outcome location")
    public void theMyCourtsComponentShouldBeDisplayedInTheOutcomeLocation() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(HearingOutcomesPage.class).ConfirmActiveCourtOxfordAndSouthern());
    }

    @And("I navigate to Cases tab")
    public void iNavigateToCasesTab() {

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickMyCourts();
        DriverContext.waitForPageToLoad();
    }

    @And("I should be able to select another new court")
    public void iShouldBeAbleToSelectAnotherNewCourt() {

        DriverContext.waitForPageToLoad();
        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Guildford Magistrates' Court");
    }

    @And("the selected new court should become my active court")
    public void theSelectedNewCourtShouldBecomeMyActiveCourt() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).ConfirmActiveCourtGuildfordMagistrates());
    }

    @And("the selected court in outcome page should become my active court")
    public void theSelectedCourtInOutcomePageShouldBecomeMyActiveCourt() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CaseSummaryPage.class).ConfirmActiveCourtOxfordAndSouthern());
    }
}
