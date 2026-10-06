package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
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

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();
    }

    @When("the page content is rendered")
    public void thePageContentIsRendered() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).CheckCasesTabSelected());
    }

    @Then("the service name {string} should be displayed as a caption")
    public void theServiceNameShouldBeDisplayedAsACaption(String service) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).isPrepareCaseForSentenceDisplayed(service));
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {

        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class)
                        .getBrowserPageTitle(),
                expectedTitle);
    }

    @When("I navigate between Case List tabs")
    public void iNavigateBetweenCaseListTabs() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).CheckCasesTabSelected());
    }

    @Then("the service name {string} should remain displayed as the caption")
    public void theServiceNameShouldRemainDisplayedAsTheCaption(String expectedCaption) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the page heading {string} should remain displayed as the H{int}")
    public void thePageHeadingShouldRemainDisplayedAsTheH(String value, int arg1) {

        DriverContext.waitForPageToLoad();
        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).verifyCasesHeading(),value);
    }

    @Given("I am viewing a defendant Case Summary page")
    public void iAmViewingADefendantCaseSummaryPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @Then("the service name {string} should be displayed as the caption")
    public void theServiceNameShouldBeDisplayedAsTheCaption(String expectedCaption) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(CaseSummaryPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the defendant name should be displayed as the H{int}")
    public void theDefendantNameShouldBeDisplayedAsTheH(int arg0) {

        DriverContext.waitForPageToLoad();
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

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickProbationRecord();

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();
    }

    @When("the Probation Record page content is rendered")
    public void theProbationRecordPageContentIsRendered() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(ProbationRecordPage.class).isEnvironmentLabelDisplayedInHeader());
    }

    @Then("the service name {string} should be displayed as the caption in Probation Record page")
    public void theServiceNameShouldBeDisplayedAsTheCaptionInProbationRecordPage(String expectedCaption) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(ProbationRecordPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the defendant name should be displayed as the H{int} in Probation Record page")
    public void theDefendantNameShouldBeDisplayedAsTheHInProbationRecordPage(int arg0) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(ProbationRecordPage.class)
                        .isDefendantNameDisplayedAsH1()
        );
    }

    @Then("the browser Probation Record page title should be {string}")
    public void theBrowserProbationRecordPageTitleShouldBe(String expectedTitle) {

        DriverContext.waitForPageToLoad();
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

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        DriverContext.waitForPageToLoad();

        DriverContext.waitFor(1);

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

        DriverContext.waitForPageToLoad();

        DriverContext.waitFor(1);

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickRiskRegister();

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();
    }

    @Then("the service name {string} should be displayed as the caption in Risk Register page")
    public void theServiceNameShouldBeDisplayedAsTheCaptionInRiskRegisterPage(String expectedCaption) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(RiskRegisterPage.class).isServiceCaptionDisplayed(expectedCaption));
    }

    @And("the defendant name should be displayed as the H{int} in Risk Register page")
    public void theDefendantNameShouldBeDisplayedAsTheHInRiskRegisterPage(int arg0) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(RiskRegisterPage.class)
                        .isDefendantNameDisplayedAsH1()
        );
    }

    @Then("the browser PRisk Register page title should be {string}")
    public void theBrowserPRiskRegisterPageTitleShouldBe(String expectedTitle) {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(RiskRegisterPage.class)
                        .getBrowserPageTitle()
                        .contains(expectedTitle)
        );
    }

    @Then("the service name should be displayed as a caption")
    public void theServiceNameShouldBeDisplayedAsACaption() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );
    }

    @And("only one H{int} heading should exist on the page")
    public void onlyOneHHeadingShouldExistOnThePage(int arg0) {

        DriverContext.waitForPageToLoad();
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

        DriverContext.waitFor(1);

        DriverContext.waitForPageToLoad();
    }

    @When("the Risk Register page content is rendered")
    public void theRiskRegisterPageContentIsRendered() {

        DriverContext.waitForPageToLoad();
        Assert.assertTrue(CurrentPage.as(RiskRegisterPage.class).isEnvironmentLabelDisplayedInHeader());
    }
}
