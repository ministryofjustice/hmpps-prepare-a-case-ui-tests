package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.config.Settings;
import org.pacfs.test.pages.*;
import org.testng.Assert;

public class headerAndFooterStepdefs extends Base {

    @Given("I have logged into PACFS")
    public void iHaveLoggedIntoPACFS() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }

    @When("I navigate to any PACFS page")
    public void iNavigateToAnyPACFSPage() {

        //no need
    }

    @Then("the MoJ standard header should be displayed")
    public void theMoJStandardHeaderShouldBeDisplayed() {

        //no code
    }

    @And("the header should remain visible throughout the user journey")
    public void theHeaderShouldRemainVisibleThroughoutTheUserJourney() {

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );

        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );
    }

    @And("the header layout should remain consistent across all pages")
    public void theHeaderLayoutShouldRemainConsistentAcrossAllPages() {

        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelVisuallyDistinct()
        );

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickBackArrow();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelVisuallyDistinct()
        );

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelVisuallyDistinct()
        );

        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickMyCourts();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelVisuallyDistinct()
        );
    }

    @Then("the MoJ standard footer should be displayed")
    public void theMoJStandardFooterShouldBeDisplayed() {

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );

        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
    }

    @And("the footer layout should remain consistent across all pages")
    public void theFooterLayoutShouldRemainConsistentAcrossAllPages() {

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickBackArrow();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isFooterLayoutConsistent()
        );


        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isFooterLayoutConsistent()
        );

        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isFooterLayoutConsistent()
        );
    }

    @Given("I am on any PACFS page")
    public void iAmOnAnyPACFSPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }

    @When("I click the {string} link in the header")
    public void iClickTheLinkInTheHeader(String arg0) {

        CurrentPage = CurrentPage.as(MyCourtsPage.class).ClickProbationDigitalServicesLink();
    }

    @Then("I should be redirected to the PACFS Home page")
    public void iShouldBeRedirectedToThePACFSHomePage() {

        Assert.fail();
    }

    @Given("I am logged into PACFS")
    public void iAmLoggedIntoPACFS() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }

    @When("the page loads")
    public void thePageLoads() {

        //no code
    }

    @Then("my user name should be displayed in the header")
    public void myUserNameShouldBeDisplayedInTheHeader() {

        Assert.assertTrue(
                CurrentPage.as(MyCourtsPage.class)
                        .isUserNameDisplayedInHeader()
        );
    }

    @When("I click the Cookies link in the footer")
    public void iClickTheCookiesLinkInTheFooter() {

        CurrentPage = CurrentPage.as(MyCourtsPage.class)
                .clickCookiesPolicyLink();
    }

    @Then("the latest Cookies policy page should be displayed")
    public void theLatestCookiesPolicyPageShouldBeDisplayed() {

        Assert.assertTrue(
                CurrentPage.as(CookiesPolicyPage.class)
                        .isCookiesPolicyPageDisplayed()
        );
    }

    @When("the footer is displayed")
    public void theFooterIsDisplayed() {

        //no code
    }

    @Then("the {string} link should not be visible")
    public void theLinkShouldNotBeVisible(String linkText) {

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
        Assert.assertFalse(
                CurrentPage.as(BasePage.class)
                        .isLinkVisible(linkText)
        );

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
        Assert.assertFalse(
                CurrentPage.as(BasePage.class)
                        .isLinkVisible(linkText)
        );

        CurrentPage = CurrentPage.as(HearingOutcomesPage.class).ClickCasesTab();
        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isMoJStandardFooterDisplayed()
        );
        Assert.assertFalse(
                CurrentPage.as(BasePage.class)
                        .isLinkVisible(linkText)
        );
    }

    @When("I select {string} from My Courts")
    public void iSelectFromMyCourts(String footerLink) {

        CurrentPage.as(MyCourtsPage.class)
                .selectFooterLink(footerLink);
    }

    @Then("the corresponding page should be displayed")
    public void theCorrespondingPageShouldBeDisplayed() {

        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isCorrespondingFooterPageDisplayed()
        );
    }

    @When("I refresh the page")
    public void iRefreshThePage() {

        CurrentPage.as(MyCourtsPage.class)
                .refreshPage();
    }

    @Then("the header should still be displayed")
    public void theHeaderShouldStillBeDisplayed() {

        Assert.assertTrue(CurrentPage.as(BasePage.class).isEnvironmentLabelDisplayedInHeader());
        Assert.assertTrue(CurrentPage.as(BasePage.class).isEnvironmentLabelVisuallyDistinct());
    }

    @And("the footer should still be displayed")
    public void theFooterShouldStillBeDisplayed() {

        Assert.assertTrue(CurrentPage.as(BasePage.class).isMoJStandardFooterDisplayed());
        Assert.assertTrue(CurrentPage.as(BasePage.class).isFooterLayoutConsistent());
    }
}
