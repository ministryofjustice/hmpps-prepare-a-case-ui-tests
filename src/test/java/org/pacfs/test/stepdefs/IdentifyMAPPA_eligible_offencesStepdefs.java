package org.pacfs.test.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.pacfs.framework.base.Base;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.config.Settings;
import org.pacfs.framework.utilities.AuthClient;
import org.pacfs.framework.utilities.CourtCaseServiceClient;
import org.pacfs.framework.utilities.MappaOffenceCodeReader;
import org.pacfs.test.pages.*;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class IdentifyMAPPA_eligible_offencesStepdefs extends Base {

    @When("^I open the hearing in PACFS$")
    public void IdentifyMAPPA_eligible_offencesStepdefs() {

        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class).EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class).EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class).ClickSignInButton();

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Bath Law Courts");

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();

    }

    @Then("the hearing should be identified as a potential MAPPA case")
    public void theHearingShouldBeIdentifiedAsAPotentialMAPPACase() {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean mappaPresent =
                CurrentPage.as(CourtCasesDetailsPage.class).isPossibleMappaPresent(defendantName);

        boolean sfoPresent =
                CurrentPage.as(CourtCasesDetailsPage.class).isPossibleSfoPresent(defendantName);


        assertTrue(
                mappaPresent,
                "Expected defendant '" + defendantName +
                        "' to have a Possible MAPPA flag."
        );

        assertTrue(
                sfoPresent,
                "Expected defendant '" + defendantName +
                        "' to have a Possible SFO flag."
        );
    }

    private String generateUniqueCaseNumber() {
        return String.valueOf(
                System.currentTimeMillis()
        );
    }

    private String generateUniqueCrn() {
        return "B" + System.currentTimeMillis();
    }


    @Given("I create PACFS cases for all MAPPA CJS offence codes")
    public void iCreatePACFSCasesForAllMAPPACJSOffenceCodes() {

        List<String> offenceCodes =
                MappaOffenceCodeReader.getOffenceCodes();

        // Token access
        AuthClient authClient = new AuthClient();
        String token = authClient.generateToken();

        CourtCaseServiceClient courtCaseServiceClient =
                new CourtCaseServiceClient(token);

        // Login to PACFS once
        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class)
                .EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class)
                .EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class)
                .ClickSignInButton();

        DriverContext.waitForPageToLoad();

        // =================================================
        // 3. OPEN THE COURT IN PACFS
        // =================================================

        CurrentPage = CurrentPage.as(MyCourtsPage.class)
                .clickLinkByText(
                        "Bath Law Courts"
                );

        DriverContext.waitForPageToLoad();


        // Process each offence code one at a time
        for (String offenceCode : offenceCodes) {

            System.out.println(
                    "================================================="
            );

            System.out.println(
                    "Processing MAPPA offence code: "
                            + offenceCode
            );

            System.out.println(
                    "================================================="
            );


            // =================================================
            // 1. CREATE CASE FOR THIS OFFENCE CODE
            // =================================================

            Map<String, String> data = new HashMap<>();

            data.put("clean", "true");
            //data.put("caseNo", generateUniqueCaseNumber());
            data.put("caseNo", "1234576544");

            // Keep this consistent with the UI assertion
            data.put("defendantName", "IbiTest Automation");

            data.put("sex", "MALE");
            data.put("title", "Mr");
            data.put("forename1", "IbiTest");
            data.put("surname", "Automation");
            data.put("line1", "56 Example St");
            data.put("dateOfBirth", "1980-04-17");
            //data.put("crn", generateUniqueCrn());
            data.put("crn", "B125455");
            data.put("probationStatus", "CURRENT");
            data.put("awaitingPsr", "true");
            data.put("breach", "false");

            // THIS IS THE CURRENT OFFENCE CODE
            data.put("offenceCode", offenceCode);

            data.put(
                    "day",
                    LocalDate.now()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );


            Response response =
                    courtCaseServiceClient.createScenario(data);

            System.out.println(
                    "HTTP status: " + response.statusCode()
            );

            System.out.println(
                    "Response body: " + response.asString()
            );

            // =================================================
            // 2. ASSERT ENDPOINT CREATED THE CASE
            // =================================================
            CurrentPage.as(CourtCasesDetailsPage.class).refreshBrowser();

            assertEquals(
                    200,
                    response.statusCode());

            System.out.println(
                    "PACFS case created successfully for offence code: "
                            + offenceCode
            );

            // =================================================
            // 4. ASSERT POSSIBLE MAPPA ON THE UI
            // =================================================

            String defendantName = "Ibi Automation";

            boolean mappaPresent =
                    CurrentPage.as(CourtCasesDetailsPage.class)
                            .isPossibleMappaPresent(defendantName);

//        boolean sfoPresent =
//                CurrentPage.as(CourtCasesDetailsPage.class)
//                        .isPossibleSfoPresent(defendantName);

            assertTrue(
                    mappaPresent,
                    "Expected defendant '"
                            + defendantName
                            + "' to have a Possible MAPPA flag "
                            + "for offence code: "
                            + offenceCode
            );

//        assertTrue(
//                sfoPresent,
//                "Expected defendant '"
//                        + defendantName
//                        + "' to have a Possible SFO flag "
//                        + "for offence code: "
//                        + offenceCode
//        );


            System.out.println(
                    "PASSED - Possible MAPPA found for offence code: "
                            + offenceCode
            );


            // =================================================
            // 5. CONTINUE TO NEXT OFFENCE CODE
            // =================================================

            System.out.println(
                    "Completed offence code: "
                            + offenceCode
            );
        }
    }

    @Given("the MAPPA CJS offence code list is configured in the PACFS backend")
    public void theMAPPACJSOffenceCodeListIsConfiguredInThePACFSBackend() {

        // Login to PACFS once
        CurrentPage = getInstance(SignInPage.class);

        CurrentPage.as(SignInPage.class)
                .EnterUsername(Settings.UserName);

        CurrentPage.as(SignInPage.class)
                .EnterPassword(Settings.Password);

        CurrentPage = CurrentPage.as(SignInPage.class)
                .ClickSignInButton();

        DriverContext.waitForPageToLoad();

        // =================================================
        // 3. OPEN THE COURT IN PACFS
        // =================================================

        CurrentPage = CurrentPage.as(MyCourtsPage.class)
                .clickLinkByText(
                        "Bath Law Courts"
                );

        DriverContext.waitForPageToLoad();
    }

    @And("the hearing contains offences with CJS codes that are not included in the MAPPA offence code list")
    public void theHearingContainsOffencesWithCJSCodesThatAreNotIncludedInTheMAPPAOffenceCodeList() {

        // Token access
        AuthClient authClient = new AuthClient();
        String token = authClient.generateToken();

        CourtCaseServiceClient courtCaseServiceClient =
                new CourtCaseServiceClient(token);
        // =================================================
        // 1. CREATE CASE FOR THIS OFFENCE CODE
        // =================================================

        Map<String, String> data = new HashMap<>();

        data.put("clean", "true");
        //data.put("caseNo", generateUniqueCaseNumber());
        data.put("caseNo", "1234576544");

        // Keep this consistent with the UI assertion
        data.put("defendantName", "IbiTest Automation");

        data.put("sex", "MALE");
        data.put("title", "Mr");
        data.put("forename1", "IbiTest");
        data.put("surname", "Automation");
        data.put("line1", "56 Example St");
        data.put("dateOfBirth", "1980-04-17");
        //data.put("crn", generateUniqueCrn());
        data.put("crn", "B125455");
        data.put("probationStatus", "CURRENT");
        data.put("awaitingPsr", "true");
        data.put("breach", "false");

        // THIS IS THE CURRENT OFFENCE CODE WRONG VALUE
        data.put("offenceCode", "0000000");

        data.put(
                "day",
                LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );

        Response response =
                courtCaseServiceClient.createScenario(data);

        System.out.println(
                "HTTP status: " + response.statusCode()
        );

        System.out.println(
                "Response body: " + response.asString()
        );

        // =================================================
        // 2. ASSERT ENDPOINT CREATED THE CASE
        // =================================================
        CurrentPage.as(CourtCasesDetailsPage.class).refreshBrowser();
    }

    @Then("the hearing should not be identified as a potential MAPPA case")
    public void theHearingShouldNotBeIdentifiedAsAPotentialMAPPACase() {

        // =================================================
        // 1. ASSERT POSSIBLE MAPPA ON THE UI
        // =================================================
        String defendantName = "IbiTest Automation";

        boolean defendantPresent =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isDefendantPresent(defendantName);

        assertFalse(
                defendantPresent,
                "Defendant '" + defendantName +
                        "' should NOT be available in the defendant list."
        );
    }

    @And("I am viewing the PACFS case list")
    public void iAmViewingThePACFSCaseList() {

        DriverContext.waitForPageToLoad();

        CurrentPage = CurrentPage.as(MyCourtsPage.class).clickLinkByText("Bath Law Courts");

        DriverContext.waitForPageToLoad();

    }

    private String token;
    private CourtCaseServiceClient courtCaseServiceClient;

    @Given("a hearing has an offence that matches a CJS MAPPA offence code")
    public void aHearingHasAnOffenceThatMatchesACJSMAPPAOffenceCode(DataTable dataTable) {

        Map<String, String> data =
                new HashMap<>(dataTable.asMap(String.class, String.class));

        // Replace TODAY with the actual current date
        if ("TODAY".equalsIgnoreCase(data.get("day"))) {

            String today = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            data.put("day", today);
        }

        var response =
                courtCaseServiceClient.createScenario(data);

        Assert.assertEquals(
                200,
                response.statusCode(),
                "PACFS test data creation failed: "
                        + response.asString()
        );
    }

    @Then("the hearing should have a light yellow background")
    public void theHearingShouldHaveALightYellowBackground() {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean highlighted =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isHearingHighlightedYellow(defendantName);

        assertTrue(
                highlighted,
                "Expected hearing row for "
                        + defendantName
                        + " to have a light yellow background");
    }

    @And("the defendant should have a {string} badge displayed in the Defendant column")
    public void theDefendantShouldHaveABadgeDisplayedInTheDefendantColumn(String badgeText) {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        if (badgeText.equalsIgnoreCase("POSSIBLE MAPPA")) {

            boolean mappaPresent =
                    CurrentPage.as(CourtCasesDetailsPage.class)
                            .isPossibleMappaPresent(defendantName);

            assertTrue(
                    mappaPresent,
                    "Expected badge '" + badgeText + "' to be displayed");
        } else if (badgeText.equalsIgnoreCase("POSSIBLE SFO")) {

            boolean sfoPresent =
                    CurrentPage.as(CourtCasesDetailsPage.class)
                            .isPossibleSfoPresent(defendantName);

            assertTrue(
                    sfoPresent,
                    "Expected badge '" + badgeText
                            + "' to be displayed");
        }
    }

    @And("the {string} badge should be displayed in red")
    public void theBadgeShouldBeDisplayedInRed(String badgeText) {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean redBadge =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isPossibleMappaBadgeRed(defendantName);

        assertTrue(
                redBadge,
                "Expected badge '" + badgeText + "' to be displayed in red");
    }

    @And("I select the defendant {string}")
    public void iSelectTheDefendant(String defendantName) {
        DriverContext.waitForPageToLoad();
        DriverContext.waitFor(1);
        CurrentPage =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .selectDefendant(defendantName);
        DriverContext.waitForPageToLoad();
    }

    @Then("the {string} badge should be displayed on the case details page")
    public void theBadgeShouldBeDisplayedOnTheCaseDetailsPage(String badgeText) {

        DriverContext.waitFor(1);
        if (badgeText.equalsIgnoreCase("POSSIBLE MAPPA")) {

            boolean displayed =
                    CurrentPage.as(CaseSummaryPage.class)
                            .isPossibleMappaBadgeDisplayed();

            assertTrue(
                    displayed,
                    "Expected " + badgeText
                            + " badge to be displayed on Case Details page");
        } else if (badgeText.equalsIgnoreCase("POSSIBLE SFO")) {

            boolean displayed =
                    CurrentPage.as(CaseSummaryPage.class)
                            .isPossibleSfoBadgeDisplayed();

            assertTrue(
                    displayed,
                    "Expected " + badgeText
                            + " badge to be displayed");
        }


    }

    @And("the {string} badge on the case details page should be displayed in red")
    public void theBadgeOnTheCaseDetailsPageShouldBeDisplayedInRed(String badgeText) {

        DriverContext.waitFor(1);
        boolean redBadge =
                CurrentPage.as(CaseSummaryPage.class)
                        .isPossibleMappaBadgeRed();

        assertTrue(
                redBadge,
                "Expected " + badgeText
                        + " badge to be displayed in red");
    }

    @Then("the {string} badge should be displayed before the {string} badge")
    public void theBadgeShouldBeDisplayedBeforeTheBadge(String firstBadge, String secondBadge) {

        DriverContext.waitFor(1);
        boolean correctOrder =
                CurrentPage.as(CaseSummaryPage.class)
                        .isMappaDisplayedBeforeSfo();

        assertTrue(
                correctOrder,
                "Expected " + firstBadge
                        + " badge to appear before "
                        + secondBadge);
    }

    @And("the {string} badge should be displayed in purple")
    public void theBadgeShouldBeDisplayedInPurple(String badgeText) {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean purpleBadge =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isPossibleSfoBadgePurple(defendantName);

        assertTrue(
                purpleBadge,
                "Expected badge '" + badgeText
                        + "' to be displayed in purple");
    }

    @And("the {string} badge on the case details page should be displayed in purple")
    public void theBadgeOnTheCaseDetailsPageShouldBeDisplayedInPurple(String badgeText) {

        DriverContext.waitFor(1);
        boolean purpleBadge =
                CurrentPage.as(CaseSummaryPage.class)
                        .isPossibleSfoBadgePurple();

        assertTrue(
                purpleBadge,
                "Expected " + badgeText
                        + " badge to be purple");
    }

    @Then("the PSR flag should be displayed as a blue tag")
    public void thePSRFlagShouldBeDisplayedAsABlueTag() {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean isBlue =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isPsrTagBlue(defendantName);

        assertTrue(
                isBlue,
                "Expected PSR to be displayed as a blue tag");
    }

    @Then("the Breach flag should be displayed as a purple tag")
    public void theBreachFlagShouldBeDisplayedAsAPurpleTag() {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean isPurple =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isBreachTagPurple(defendantName);

        assertTrue(
                isPurple,
                "Expected Breach to be displayed as a purple tag");
    }

    @Then("the Possible nDelius Record flag should be displayed as a red tag")
    public void thePossibleNDeliusRecordFlagShouldBeDisplayedAsARedTag() {

        String defendantName = "nDelius Automated";

        boolean isRed =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isPossibleNDeliusTagRed(defendantName);

        assertTrue(
                isRed,
                "Expected Possible nDelius Record to be displayed as a red tag");
    }

    @When("I search for the defendant {string}")
    public void iSearchForTheDefendant(String defendantName) {

        CurrentPage.as(CourtCasesDetailsPage.class)
                .searchForDefendant(defendantName);

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
    }

    @Then("the search result should have a light yellow background")
    public void theSearchResultShouldHaveALightYellowBackground() {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean highlighted =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isSearchResultHighlightedYellow(defendantName);

        assertTrue(
                highlighted,
                "Expected search result to have a yellow background"
        );
    }

    @Then("the {string} badge should be displayed below the CRN")
    public void theBadgeShouldBeDisplayedBelowTheCRN(String badgeText) {

        String defendantNameCrn = "B123000";

        DriverContext.waitFor(1);
        boolean displayedBelowCrn =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isMappaDisplayedBelowCrn(defendantNameCrn);

        assertTrue(
                displayedBelowCrn,
                "Expected " + badgeText
                        + " badge to be displayed below the CRN");
    }

    @Then("the {string} badge should be displayed")
    public void theBadgeShouldBeDisplayed(String badgeText) {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        if (badgeText.equalsIgnoreCase("POSSIBLE MAPPA")) {

            boolean badgeDisplayed =
                    CurrentPage.as(CourtCasesDetailsPage.class)
                            .isPossibleMappaPresent(defendantName);

            assertTrue(
                    badgeDisplayed,
                    "Expected badge '" + badgeText
                            + "' to be displayed");

        } else if (badgeText.equalsIgnoreCase("POSSIBLE SFO")) {

            boolean badgeDisplayed =
                    CurrentPage.as(CourtCasesDetailsPage.class)
                            .isPossibleSfoPresent(defendantName);

            assertTrue(
                    badgeDisplayed,
                    "Expected badge '" + badgeText
                            + "' to be displayed");
        }
    }

    @And("the {string} badge should appear above the {string} badge")
    public void theBadgeShouldAppearAboveTheBadge(String firstBadge, String secondBadge) {

        String defendantName = "Ibtt Automated";

        DriverContext.waitFor(1);
        boolean correctOrder =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .isMappaDisplayedAboveSfo(defendantName);

        assertTrue(
                correctOrder,
                "Expected "
                        + firstBadge
                        + " to appear above "
                        + secondBadge);
    }

    @And("I open the Outcomes tab")
    public void iOpenTheOutcomesTab() {

        CurrentPage =
                CurrentPage.as(CourtCasesDetailsPage.class)
                        .ClickOutcomeTab();

        DriverContext.waitForPageToLoad();
    }

    @And("I move the hearing for defendant {string} to Hearing Outcome Not Required")
    public void iMoveTheHearingForDefendantToHearingOutcomeNotRequired(String defendantName) {

        CurrentPage
                .as(CourtCasesDetailsPage.class)
                .moveToHearingOutcomeNotRequired(defendantName);

        DriverContext.waitForPageToLoad();
    }

    @And("I expand the hearing note section")
    public void iExpandTheHearingNoteSection() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class)
                .expandHearingNoteSection();

        DriverContext.waitForPageToLoad();
    }

    @And("I add the hearing note {string}")
    public void iAddTheHearingNote(String note) {

        CurrentPage.as(CaseSummaryPage.class)
                .addHearingNote2(note);

        DriverContext.waitForPageToLoad();
        DriverContext.waitFor(1);
    }

    @And("I send the outcome to admin")
    public void iSendTheOutcomeToAdmin() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class)
                .sendOutcomeToAdmin();

        DriverContext.waitForPageToLoad();
        DriverContext.waitFor(1);
    }

    @And("I choose {string} from the outcome type list")
    public void iChooseFromTheOutcomeTypeList(String outcomeType) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class)
                .selectOutcomeType(outcomeType);

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
    }

    @And("I send the selected outcome to admin")
    public void iSendTheSelectedOutcomeToAdmin() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(CaseSummaryPage.class)
                .clickSendToAdminInModal();

        DriverContext.waitForPageToLoad();
    }

    @Then("the outcome should be sent successfully")
    public void theOutcomeShouldBeSentSuccessfully() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        boolean displayed =
                CurrentPage.as(CaseSummaryPage.class)
                        .isOutcomeSuccessMessageDisplayed();

        assertTrue(
                displayed,
                "Expected success message to be displayed");
    }

    @And("I navigate to the Outcomes page")
    public void iNavigateToTheOutcomesPage() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage =
                CurrentPage.as(CaseSummaryPage.class)
                        .clickOutcomesTab();

        DriverContext.waitForPageToLoad();
    }

    @And("I assign the hearing for defendant {string} to myself")
    public void iAssignTheHearingForDefendantToMyself(String defendantName) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage
                .as(HearingOutcomesPage.class)
                .assignHearingToMyself(defendantName);

        DriverContext.waitForPageToLoad();
        DriverContext.waitFor(1);
    }

    @Then("I should see a successful assignment message for defendant {string}")
    public void iShouldSeeASuccessfulAssignmentMessageForDefendant(String defendantName) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        boolean messageDisplayed =
                CurrentPage
                        .as(HearingOutcomesPage.class)
                        .isAssignToMeSuccessMessageDisplayed(
                                defendantName);

        assertTrue(
                messageDisplayed,
                "Expected success message for defendant: "
                        + defendantName
        );
    }

    @And("I open the In Progress tab")
    public void iOpenTheInProgressTab() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(HearingOutcomesPage.class)
                .openInProgressTab();

        DriverContext.waitForPageToLoad();
        DriverContext.waitFor(1);
    }

    @And("I move the hearing for defendant {string} to Resulted")
    public void iMoveTheHearingForDefendantToResulted(String defendantName) {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage
                .as(HearingOutcomesPage.class)
                .moveHearingToResulted(defendantName);

        DriverContext.waitForPageToLoad();
        DriverContext.waitFor(1);
    }

    @And("I open the Resulted Cases tab")
    public void iOpenTheResultedCasesTab() {

        DriverContext.waitFor(1);
        DriverContext.waitForPageToLoad();
        CurrentPage.as(HearingOutcomesPage.class)
                .openResultedCasesTab();

        DriverContext.waitForPageToLoad();
    }

    @Then("the hearing should have a light yellow background in the Cases to Result tab")
    public void theHearingShouldHaveALightYellowBackgroundInTheCasesToResultTab() {

        DriverContext.waitForPageToLoad();
        String defendantName = "Tom Automated";

        DriverContext.waitFor(1);
        boolean highlighted =
                CurrentPage.as(HearingOutcomesPage.class)
                        .isPossibleMappaHighlightedInCasesToResult(defendantName);

        assertTrue(
                highlighted,
                "Expected hearing for "
                        + defendantName
                        + " to have a light yellow background in Cases to Result"
        );
    }

    @And("the defendant should have a {string} badge displayed in the Defendant column in the Cases to Result tab")
    public void theDefendantShouldHaveABadgeDisplayedInTheDefendantColumnInTheCasesToResultTab(String badgeText) {

        DriverContext.waitForPageToLoad();
        String defendantName = "Tom Automated";

        DriverContext.waitFor(1);
        boolean badgePresent =
                CurrentPage.as(HearingOutcomesPage.class)
                        .isPossibleMappaPresentInCasesToResult(defendantName);

        assertTrue(
                badgePresent,
                "Expected "
                        + badgeText
                        + " badge to be displayed in Cases to Result"
        );
    }

    @And("the {string} badge should be displayed in red in the Cases to Result tab")
    public void theBadgeShouldBeDisplayedInRedInTheCasesToResultTab(String badgeText) {

        DriverContext.waitForPageToLoad();
        String defendantName = "Tom Automated";

        DriverContext.waitFor(1);
        boolean redBadge =
                CurrentPage.as(HearingOutcomesPage.class)
                        .isPossibleMappaBadgeRedInCasesToResult(defendantName);

        assertTrue(
                redBadge,
                "Expected "
                        + badgeText
                        + " badge to be displayed in red"
        );
    }

    @Then("the hearing should be displayed in the In Progress tab")
    public void theHearingShouldBeDisplayedInTheInProgressTab() {

        String defendantName = "Tom Automated";

        DriverContext.waitFor(1);
        boolean displayed =
                CurrentPage.as(HearingOutcomesPage.class)
                        .isDefendantDisplayedInProgress(defendantName);

        assertTrue(
                displayed,
                "Expected defendant "
                        + defendantName
                        + " to be displayed in the In Progress tab"
        );
    }

    @And("the hearing should continue to display the {string} badge")
    public void theHearingShouldContinueToDisplayTheBadge(String badgeText) {

        String defendantName = "Tom Automated";

        DriverContext.waitFor(1);
        boolean displayed =
                CurrentPage.as(HearingOutcomesPage.class)
                        .isPossibleMappaPresentInProgress(defendantName);

        assertTrue(
                displayed,
                "Expected "
                        + badgeText
                        + " badge to continue displaying in the In Progress tab"
        );
    }

    @And("the hearing should continue to have a light yellow background")
    public void theHearingShouldContinueToHaveALightYellowBackground() {

        String defendantName = "Tom Automated";

        DriverContext.waitFor(1);
        boolean highlighted =
                CurrentPage.as(HearingOutcomesPage.class)
                        .isPossibleMappaHighlightedInProgress(defendantName);

        assertTrue(
                highlighted,
                "Expected hearing to remain highlighted in yellow"
        );
    }
}
