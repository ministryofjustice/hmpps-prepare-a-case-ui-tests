package org.pacfs.test.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.config.Settings;
import org.pacfs.test.pages.*;

import java.util.List;
import java.util.Map;

public class accessibilityHeadingHierarchyStepdefs extends Base {

    @Given("I am viewing the Cases page")
    public void iAmViewingTheCasesPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @When("the page is rendered")
    public void thePageIsRendered() {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @When("the My courts page is rendered")
    public void theMyCourtsPageIsRendered() {

        Assert.assertEquals(CurrentPage.as(MyCourtsPage.class).GetMyCourtsText(), "My courts");
    }

    @When("the Outcomes page is rendered")
    public void theOutcomesPageIsRendered() {

        Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).GetNameOfCourt(), "Oxford and Southern Oxfordshire Magistrates' Court");
    }

    @Then("the page heading {string} should be displayed as the H{int}")
    public void thePageHeadingShouldBeDisplayedAsTheH(String value, int arg1) {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).verifyCasesHeading(),value);
    }

    @And("the service name {string} should be displayed as the page caption")
    public void theServiceNameShouldBeDisplayedAsThePageCaption(String value) {

        Assert.assertEquals(CurrentPage.as(CourtCasesDetailsPage.class).verifyPageCaption(), value);
    }

    @Then("the service name {string} should be displayed as the My courts page caption")
    public void theServiceNameShouldBeDisplayedAsTheMyCourtsPageCaption(String value) {

        Assert.assertEquals(CurrentPage.as(MyCourtsPage.class).verifyPageCaption(), value);
    }

    @Then("the service name {string} should be displayed as the Outcomes page caption")
    public void theServiceNameShouldBeDisplayedAsTheOutcomesPageCaption(String value) {

        Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).verifyPageCaption(), value);
    }

    @And("any date or contextual information should be displayed as an H{int}")
    public void anyDateOrContextualInformationShouldBeDisplayedAsAnH(int headingLevel) {

        Assert.assertTrue(CurrentPage.as(CourtCasesDetailsPage.class).isContextHeadingDisplayedAsH2());
    }

    @And("the page should contain only one H{int}")
    public void thePageShouldContainOnlyOneH(int value) {

        Assert.assertEquals(
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .getHeadingCount("h1"),
                value
        );
    }

    @And("the My courts page should contain only one H{int}")
    public void theMyCourtsPageShouldContainOnlyOneH(int value) {

        Assert.assertEquals(
                CurrentPage.as(MyCourtsPage.class)
                        .getHeadingCount("h1"),
                value
        );
    }

    @Then("the page should follow the heading hierarchy:")
    public void thePageShouldFollowTheHeadingHierarchy(DataTable headingHierarchy) {

        List<Map<String, String>> headings = headingHierarchy.asMaps(String.class, String.class);

        Assert.assertTrue(
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .verifyHeadingHierarchy(headings)
        );
    }

    @Given("I am viewing the Hearing Outcomes page")
    public void iAmViewingTheHearingOutcomesPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).ClickOutcomeTab();
    }

    @And("{string} should be displayed as the H{int}")
    public void shouldBeDisplayedAsTheH(String value, int arg1) {

        if(value.equalsIgnoreCase("My courts")){

            Assert.assertEquals(CurrentPage.as(MyCourtsPage.class).verifyOutcomesHeading(),value);

        }else {

            Assert.assertEquals(CurrentPage.as(HearingOutcomesPage.class).verifyOutcomesHeading(),value);

        }

    }

    @And("the Cases to Result section should be displayed as an H{int}")
    public void theCasesToResultSectionShouldBeDisplayedAsAnH(int arg0) {

        Assert.assertTrue(
                CurrentPage.as(HearingOutcomesPage.class)
                        .isCasesToResultHeadingDisplayedAsH2()
        );
    }

    @And("any remaining tab headings should be displayed as H{int}")
    public void anyRemainingTabHeadingsShouldBeDisplayedAsH(int arg0) {

        Assert.assertTrue(CurrentPage.as(HearingOutcomesPage.class).isContextHeadingDisplayedAsH2());
    }

    @And("the page should contain only one H{int} in Hearing outcomes page")
    public void thePageShouldContainOnlyOneHInHearingOutcomesPage(int value) {

        Assert.assertEquals(
                CurrentPage.as(HearingOutcomesPage.class)
                        .getHeadingCount("h1"),
                value
        );
    }

    @Then("the browser page title should be {string}")
    public void theBrowserPageTitleShouldBe(String expectedTitle) {

        Assert.assertEquals(
                CurrentPage.as(HearingOutcomesPage.class)
                        .getBrowserPageTitle(),
                expectedTitle
        );
    }

    @Given("I am viewing the Search Results for all courts page")
    public void iAmViewingTheSearchResultsForAllCourtsPage() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();
    }


    @Then("the browser My courts page title should be {string}")
    public void theBrowserMyCourtsPageTitleShouldBe(String expectedTitle) {

        Assert.assertEquals(
                CurrentPage.as(MyCourtsPage.class)
                        .getBrowserPageTitle(),
                expectedTitle
        );
    }

    @And("headings should follow the correct hierarchy")
    public void headingsShouldFollowTheCorrectHierarchy() {

        Assert.assertTrue(CurrentPage.as(MyCourtsPage.class).verifyHeadingHierarchy());
    }

    @Given("I navigate to the {string} page from Cases")
    public void iNavigateToThePageFromCases(String arg0) {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickProbationRecord();

        CurrentPage = CurrentPage.as(ProbationRecordPage.class).ClickViewRecord();
    }

    @When("the Adult Custody page is rendered")
    public void theAdultCustodyPageIsRendered() {

        Assert.assertTrue(
                CurrentPage.as(AdultCustodyPage.class)
                        .isViewContactListLinkDisplayedAndClickable()
        );
    }

    @Then("the browser Adult Custody page title should be {string}")
    public void theBrowserAdultCustodyPageTitleShouldBe(String expectedTitle) {

        Assert.assertTrue(
                CurrentPage.as(AdultCustodyPage.class)
                        .getBrowserPageTitle()
                        .contains(expectedTitle)
        );
    }

    @Given("I navigate to the {string} page from Case Summary")
    public void iNavigateToThePageFromCaseSummary(String arg0) {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();
    }

    @When("the Case summary page is rendered")
    public void theCaseSummaryPageIsRendered() {

        Assert.assertTrue(CurrentPage.as(CaseSummaryPage.class).IsCaseSummaryPresent());
    }

    @Then("the browser Case summary page title should be {string}")
    public void theBrowserCaseSummaryPageTitleShouldBe(String expectedTitle) {

        Assert.assertTrue(
                CurrentPage.as(CaseSummaryPage.class)
                        .getBrowserPageTitle()
                        .contains(expectedTitle)
        );
    }

    @Given("I am viewing a Probation Record")
    public void iAmViewingAProbationRecord() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Oxford and Southern Oxfordshire Magistrates' Court");

        CurrentPage = CurrentPage.as(CourtCasesDetailsPage.class).selectFirstDefendantName();

        CurrentPage = CurrentPage.as(CaseSummaryPage.class).ClickProbationRecord();
    }

    @When("I select {string}")
    public void iSelect(String footerLink) {

        CurrentPage = CurrentPage.as(ProbationRecordPage.class).ClickViewRecord();
    }
}
