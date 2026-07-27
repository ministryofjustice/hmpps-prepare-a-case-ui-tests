package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.config.Settings;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.pacfs.test.pages.MyCourtsPage;
import org.pacfs.test.pages.SignInPage;
import org.pacfs.test.pages.UserGuideSharepointPacfsPage;
import org.testng.Assert;

public class userGuideStepdefs extends Base {

    @Given("I am viewing the PACFS Cases page")
    public void iAmViewingThePACFSCasesPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @Then("the User Guide link should be displayed in the approved location outside the footer")
    public void theUserGuideLinkShouldBeDisplayedInTheApprovedLocationOutsideTheFooter() {

        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isUserGuideLinkDisplayedOutsideFooter()
        );
    }

    @Given("I am viewing the PACFS Outcomes page")
    public void iAmViewingThePACFSOutcomesPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
    }

    @Given("I am viewing a PACFS page containing the User Guide link")
    public void iAmViewingAPACFSPageContainingTheUserGuideLink() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @When("I select the User Guide link")
    public void iSelectTheUserGuideLink() {

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class)
                .selectUserGuideLink();
    }

    @Then("the User Guide should open in a new browser tab")
    public void theUserGuideShouldOpenInANewBrowserTab() {

        Assert.assertTrue(
                CurrentPage.as(UserGuideSharepointPacfsPage.class)
                        .isUserGuideOpenedInNewTab()
        );
    }

    @And("the original PACFS page should remain open in the previous tab")
    public void theOriginalPACFSPageShouldRemainOpenInThePreviousTab() {

        Assert.assertTrue(
                CurrentPage.as(UserGuideSharepointPacfsPage.class)
                        .isOriginalPACFSPageStillOpen()
        );
    }

    @Given("I am viewing the User Guide link in PACFS")
    public void iAmViewingTheUserGuideLinkInPACFS() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @Then("I should be redirected to the approved User Guide location")
    public void iShouldBeRedirectedToTheApprovedUserGuideLocation() {

        Assert.assertTrue(
                CurrentPage.as(UserGuideSharepointPacfsPage.class)
                        .isUserGuideRedirectedToApprovedLocation()
        );

    }

    @And("the User Guide content should be displayed successfully {string}")
    public void theUserGuideContentShouldBeDisplayedSuccessfully(String expectedUrl) {

        Assert.assertTrue(
                CurrentPage.as(UserGuideSharepointPacfsPage.class)
                        .isUserGuideContentDisplayed(expectedUrl)
        );
    }

    @Then("the User Guide link should not be displayed in the footer")
    public void theUserGuideLinkShouldNotBeDisplayedInTheFooter() {

        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isUserGuideLinkDisplayedOutsideFooter()
        );
    }

    @When("I scroll to the footer")
    public void iScrollToTheFooter() {

        //no code
    }
}
