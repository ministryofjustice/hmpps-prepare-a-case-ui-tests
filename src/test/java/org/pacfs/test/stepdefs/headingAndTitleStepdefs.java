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

public class headingAndTitleStepdefs extends Base {

    @Given("I am viewing the PACFS Case List page")
    public void iAmViewingThePACFSCaseListPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @When("the page content is rendered")
    public void thePageContentIsRendered() {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).CheckCasesTabSelected());
    }

    @Then("the service name {string} should be displayed as a caption")
    public void theServiceNameShouldBeDisplayedAsACaption(String service) {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).isPrepareCaseForSentenceDisplayed(service));
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class)
                        .getBrowserPageTitle(),
                expectedTitle);
    }

    @When("I navigate between Case List tabs")
    public void iNavigateBetweenCaseListTabs() {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).CheckCasesTabSelected());
    }

    @Then("the service name {string} should remain displayed as the caption")
    public void theServiceNameShouldRemainDisplayedAsTheCaption(String expectedCaption) {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the page heading {string} should remain displayed as the H{int}")
    public void thePageHeadingShouldRemainDisplayedAsTheH(String value, int arg1) {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).verifyCasesHeading(),value);
    }

    @Given("I am viewing a defendant Case Summary page")
    public void iAmViewingADefendantCaseSummaryPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @Then("the service name {string} should be displayed as the caption")
    public void theServiceNameShouldBeDisplayedAsTheCaption(String expectedCaption) {

        Assert.assertTrue(CurrentPage.as(CaseSummaryPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the defendant name should be displayed as the H{int}")
    public void theDefendantNameShouldBeDisplayedAsTheH(int arg0) {

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .isDefendantNameDisplayedAsH1()
        );
    }

    @Given("I am viewing a defendant Probation Record page")
    public void iAmViewingADefendantProbationRecordPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickProbationRecord();
    }

    @When("the Probation Record page content is rendered")
    public void theProbationRecordPageContentIsRendered() {

        Assert.assertTrue(CurrentPage.as(ProbationRecordPage.class).isEnvironmentLabelDisplayedInHeader());
    }

    @Then("the service name {string} should be displayed as the caption in Probation Record page")
    public void theServiceNameShouldBeDisplayedAsTheCaptionInProbationRecordPage(String expectedCaption) {

        Assert.assertTrue(CurrentPage.as(ProbationRecordPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the defendant name should be displayed as the H{int} in Probation Record page")
    public void theDefendantNameShouldBeDisplayedAsTheHInProbationRecordPage(int arg0) {

        Assert.assertTrue(
                CurrentPage.as(ProbationRecordPage.class)
                        .isDefendantNameDisplayedAsH1()
        );
    }

    @Then("the browser Probation Record page title should be {string}")
    public void theBrowserProbationRecordPageTitleShouldBe(String expectedTitle) {

        Assert.assertTrue(
                CurrentPage.as(ProbationRecordPage.class)
                        .getBrowserPageTitle()
                        .contains(expectedTitle)
        );
    }

    @Given("I am viewing a defendant Risk Register page")
    public void iAmViewingADefendantRiskRegisterPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickRiskRegister();
    }

    @Then("the service name {string} should be displayed as the caption in Risk Register page")
    public void theServiceNameShouldBeDisplayedAsTheCaptionInRiskRegisterPage(String expectedCaption) {

        Assert.assertTrue(CurrentPage.as(RiskRegisterPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the defendant name should be displayed as the H{int} in Risk Register page")
    public void theDefendantNameShouldBeDisplayedAsTheHInRiskRegisterPage(int arg0) {

        Assert.assertTrue(
                CurrentPage.as(RiskRegisterPage.class)
                        .isDefendantNameDisplayedAsH1()
        );
    }

    @Then("the browser PRisk Register page title should be {string}")
    public void theBrowserPRiskRegisterPageTitleShouldBe(String expectedTitle) {

        Assert.assertTrue(
                CurrentPage.as(RiskRegisterPage.class)
                        .getBrowserPageTitle()
                        .contains(expectedTitle)
        );
    }

    @Then("the service name should be displayed as a caption")
    public void theServiceNameShouldBeDisplayedAsACaption() {

        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );
    }

    @And("only one H{int} heading should exist on the page")
    public void onlyOneHHeadingShouldExistOnThePage(int arg0) {

        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelVisuallyDistinct()
        );
    }

    @Given("I am on the My courts page")
    public void iAmOnTheMyCourtsPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }

    @When("the Risk Register page content is rendered")
    public void theRiskRegisterPageContentIsRendered() {

        Assert.assertTrue(CurrentPage.as(RiskRegisterPage.class).isEnvironmentLabelDisplayedInHeader());
    }
}
