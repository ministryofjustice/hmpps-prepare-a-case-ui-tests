package org.pacfs.test.stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.config.Settings;
import org.pacfs.test.pages.CourtCasesDetailsPage;
import org.pacfs.test.pages.HearingOutcomesPage;
import org.pacfs.test.pages.MyCourtsPage;
import org.pacfs.test.pages.SignInPage;
import org.testng.Assert;

public class outcomesPaginationStepdefs extends Base {

    @Given("I am logged into the application")
    public void iAmLoggedIntoTheApplication() {

        CurrentPage = GetInstance(SignInPage.class);

        CurrentPage.As(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.As(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.As(SignInPage.class).ClickSignInButton();
    }

    @And("I am on the {string} page")
    public void iAmOnThePage(String value) {

        Assert.assertEquals(CurrentPage.As(MyCourtsPage.class).GetMyCourtsText(),value);
    }

    @When("I select the {string} court")
    public void iSelectTheCourt(String NameOfCourt) {

        CurrentPage = CurrentPage.As(MyCourtsPage.class).clickLinkByText(NameOfCourt);
    }

    @Then("I should be taken to the selected court page")
    public void iShouldBeTakenToTheSelectedCourtPage() {

       //Assert.assertEquals(CurrentPage.As(CourtCasesDetailsPage.class).GetNameOfCourt(), "Guildford Magistrates' Court");
    }

    @And("the {string} tab should be selected by default")
    public void theTabShouldBeSelectedByDefault(String selectedVal) {

        if(selectedVal.equalsIgnoreCase("Cases")){

            Assert.assertTrue(CurrentPage.As(CourtCasesDetailsPage.class).CheckCasesTabSelected());
        }
    }

    @Given("I navigate to the {string} tab")
    public void iNavigateToTheTab(String selectedVal) {

        if(selectedVal.equalsIgnoreCase("Outcomes")){

            CurrentPage = CurrentPage.As(CourtCasesDetailsPage.class).ClickOutcomeTab();

        }else if(selectedVal.equalsIgnoreCase("In progress")){

            CurrentPage.As(HearingOutcomesPage.class).ClickInProgressTab();

        }else if(selectedVal.equalsIgnoreCase("Resulted cases")){

            CurrentPage.As(HearingOutcomesPage.class).ClickResultedCasesTab();
        }

    }

    @Then("I verify that defendant names across all pagination pages in the Outcomes tab are unique and consistently ordered")
    public void iVerifyThatDefendantNamesAcrossAllPaginationPagesInTheOutcomesTabAreUniqueAndConsistentlyOrdered() {

        CurrentPage.As(HearingOutcomesPage.class).validateDuplicateDefendantNamesAcrossPages2();
    }

    @When("I select the defendant name from the case results")
    public void iSelectTheDefendantNameFromTheCaseResults() {

        CurrentPage.As(HearingOutcomesPage.class).selectFirstDefendantCheckbox();
    }

    @And("I assign the case to myself using the {string} button")
    public void iAssignTheCaseToMyselfUsingTheButton(String arg0) {

        CurrentPage.As(HearingOutcomesPage.class).assignSelectedCaseToMe();
    }

    @Then("I should see the success message {string}")
    public void iShouldSeeTheSuccessMessage(String mgs) {

        if(mgs.equalsIgnoreCase("You are assigned to result <selectedDefendantName>. Their case has moved to the in progress tab.")){

            CurrentPage.As(HearingOutcomesPage.class).validateAssignToMeSuccessMessage();

        }else if(mgs.equalsIgnoreCase("You have moved <selectedDefendantName> case to resulted cases.")){

            CurrentPage.As(HearingOutcomesPage.class).validateMoveToResultedSuccessMessage();
        }
    }

    @And("the defendant name should no longer appear in the Outcomes case results list")
    public void theDefendantNameShouldNoLongerAppearInTheOutcomesCaseResultsList() {

        CurrentPage.As(HearingOutcomesPage.class).validateSelectedDefendantNameNoLongerExistsAcrossPages();
    }

    @Then("I should see the defendant name in the in progress cases list")
    public void iShouldSeeTheDefendantNameInTheInProgressCasesList() {

        CurrentPage.As(HearingOutcomesPage.class).validateSelectedDefendantExistsAcrossPages();
    }

    @When("I select the {string} action for defendant")
    public void iSelectTheActionForDefendant(String arg0) {

        CurrentPage.As(HearingOutcomesPage.class).clickMoveToResultedForSelectedDefendant();
    }

    @Then("I should see the defendant name in the resulted cases list")
    public void iShouldSeeTheDefendantNameInTheResultedCasesList() {

        CurrentPage.As(HearingOutcomesPage.class).validateSelectedDefendantExistsAcrossPages();
    }
}
