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

public class environmentBannerStepdefs extends Base {


    @Given("I am using the PACFS {string} environment")
    public void iAmUsingThePACFSEnvironment(String arg0) {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        DriverContext.waitFor(1);

    }

    @Then("the environment label {string} should be displayed next to {string} in the header")
    public void theEnvironmentLabelShouldBeDisplayedNextToInTheHeader(String environment, String serviceName) {

        DriverContext.waitFor(1);
        Assert.assertTrue(
                CurrentPage.as(MyCourtsPage.class)
                        .isEnvironmentLabelDisplayedNextToService(environment, serviceName)
        );
    }

    @And("the environment label should be visually distinct from the service name")
    public void theEnvironmentLabelShouldBeVisuallyDistinctFromTheServiceName() {

        DriverContext.waitFor(1);
        Assert.assertTrue(
                CurrentPage.as(MyCourtsPage.class)
                        .isEnvironmentLabelVisuallyDistinct()
        );

    }

    @And("the environment label is displayed in the header")
    public void theEnvironmentLabelIsDisplayedInTheHeader() {

        DriverContext.waitFor(1);
        Assert.assertTrue(
                CurrentPage.as(MyCourtsPage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );

    }

    @When("I navigate between PACFS pages")
    public void iNavigateBetweenPACFSPages() {

        DriverContext.waitFor(1);
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

    @Then("the same environment label should remain displayed in the header")
    public void theSameEnvironmentLabelShouldRemainDisplayedInTheHeader() {

        //executed in the previous step
    }

    @And("the label should remain next to {string}")
    public void theLabelShouldRemainNextTo(String serviceName) {

        DriverContext.waitFor(1);
        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickBackArrow();

        Assert.assertTrue(
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isEnvironmentLabelDisplayedNextToService("DEV", serviceName)
        );
    }

    @When("I navigate to different areas of the PACFS service")
    public void iNavigateToDifferentAreasOfThePACFSService() {

        DriverContext.waitFor(1);
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

    @Then("the environment label should remain visible")
    public void theEnvironmentLabelShouldRemainVisible() {

        DriverContext.waitFor(1);
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickBackArrow();
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
        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickProbationRecord();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );

        CurrentPage = CurrentPage.as(ProbationRecordPage.class).ClickViewRecord();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );

        CurrentPage = CurrentPage.as(AdultCustodyPage.class).ClickRiskRegister();
        Assert.assertTrue(
                CurrentPage.as(BasePage.class)
                        .isEnvironmentLabelDisplayedInHeader()
        );
    }

    @And("the environment label should not change unexpectedly")
    public void theEnvironmentLabelShouldNotChangeUnexpectedly() {

        //Covered in the previous steps
    }

}
