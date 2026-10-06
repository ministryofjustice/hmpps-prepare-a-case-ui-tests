package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;
import org.pacfs.framework.controls.internals.Control;
import org.pacfs.framework.utilities.MappaOffenceCodeReader;

import java.time.Duration;
import java.util.*;

public class CourtCasesDetailsPage extends BasePage {

    //@FindBy(how = How.XPATH, using = "//header[@class='moj-header']/following-sibling::div[position()=2]/child::div/child::div[position()=2]/descendant::span")
    @FindBy(how = How.XPATH, using = "//header[@class='probation-common-header govuk-!-display-none-print ']/following-sibling::div[position()=2]/child::div/child::div[position()=2]/descendant::span")
    private WebElement MyCourtDetailsTxt;

    @FindBy(how = How.LINK_TEXT, using = "My courts")
    private WebElement MyCourtDetailsLinkTxt;

    @FindBy(how = How.XPATH, using = "//header[@class='probation-common-header govuk-!-display-none-print ']/following-sibling::div[position()=2]/child::div/child::div[position()=1]/descendant::a[position()=1]")
    //@FindBy(how = How.XPATH, using = "//main[@id='main-content']/child::div[position()=1]/child::h1")
    private WebElement IsCasesTabSel;

    @FindBy(how = How.XPATH, using = "//header[@class='probation-common-header govuk-!-display-none-print ']/following-sibling::div[position()=2]/child::div/child::div[position()=1]/descendant::a[position()=2]")
    private WebElement IsOutcomeTabSel;

    @FindBy(how = How.XPATH, using = "//span[@data-qa='probation-common-header-user-name']")
    //@FindBy(how = How.XPATH, using = "//header[@class='moj-header']/child::div/child::div[position()=2]/child::nav/child::ul/child::li[position()=3]/child::a")
    private WebElement AccountSignInLnk;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'There is a problem with this service')]")
    private WebElement serviceErrMsg;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Hearing outcome still to be added')]")
    private WebElement HearingOutcomeStillToBeAdded;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement pageHeading;

    @FindBy(how = How.XPATH, using = "//main[@id='main-content']/child::div/child::span")
    private WebElement pageCaption;

    @FindBy(how = How.TAG_NAME, using = "h2")
    //private List<WebElement> h2Headings;
    private List<Control> h2Headings;

    @FindBy(how = How.TAG_NAME, using = "h1")
    //private List<WebElement> h1Headings;
    private List<Control> h1Headings;

    @FindBy(how = How.LINK_TEXT, using = "Probation Digital Services")
    private WebElement probationDigitalServicesLink;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'govuk-clearfix')]//strong[contains(text(),'DEV')]")
    private WebElement environmentLabel;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),\"Oxford and Southern Oxfordshire Magistrates' Court\")]")
    private WebElement activeCourtOxfordAndSouthern;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),\"Guildford Magistrates' Court\")]")
    private WebElement activeCourtGuildford;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'govuk-width-container')]//span[contains(text(),'Prepare a case for sentence')]")
    private WebElement prepareCaseForSentence;


    public String GetNameOfCourt() {

        DriverContext.waitForPageToLoad();
        return MyCourtDetailsTxt.getText();
    }

    public boolean CheckCasesTabSelected() {

        DriverContext.waitForPageToLoad();
        return DriverContext.isCasesTabSelected(IsCasesTabSel);
    }

    public HearingOutcomesPage ClickOutcomeTab() {

        DriverContext.waitForElementToBeClickable(IsOutcomeTabSel);

        DriverContext.waitForPageToLoad();
        return getInstance(HearingOutcomesPage.class);
    }

    public boolean IsSignOutLinkPresent() {

        return DriverContext.isElementPresent(AccountSignInLnk);
    }

    public boolean ValidateSessionStability() {

        // Check 1: Ensure error message is NOT displayed
        boolean isServiceErrorDisplayed =
                DriverContext.isElementPresent(serviceErrMsg);

        return !isServiceErrorDisplayed;
    }

    public boolean ValidateSessionUnexpectedLogout() {

        // Check 2: Ensure user is still logged in (session active indicator)
        boolean isSessionActive = DriverContext.isElementPresent(IsCasesTabSel);

        return isSessionActive;
    }

    public String GetHearingOutcomeStillToBeAdded() {

        DriverContext.waitForPageToLoad();
        return HearingOutcomeStillToBeAdded.getText();
    }

    // Store defendant name so it can be used in other methods
    public static String selectedDefendantName;
    public static String selectedProbationStatus;

    public CaseSummaryPage selectFirstDefendantName() {

        // ==============================
        // CAPTURE DEFENDANT NAME
        // ==============================
        WebElement defendantNameElement = LocalDriverContext.getRemoteWebDriver().findElement(
                By.xpath("//table/tbody/tr[1]/td[position()=1]/a"));

        selectedDefendantName = defendantNameElement.getText().trim();

        System.out.println("Selected Defendant Name: " + selectedDefendantName);

        // ==============================
        // CAPTURE PROBATION STATUS (SAME ROW)
        // ==============================
        WebElement probationStatusElement = LocalDriverContext.getRemoteWebDriver().findElement(
                By.xpath("//table/tbody/tr[1]/td[position()=2]"));

        selectedProbationStatus = probationStatusElement.getText().trim();

        System.out.println("Selected Probation Status: " + selectedProbationStatus);


        // ==============================
        // CLICK DEFENDANT
        // ==============================
        defendantNameElement.click();

        System.out.println("Clicked Defendant Link: " + selectedDefendantName);
        DriverContext.waitForPageToLoad();
        return getInstance(CaseSummaryPage.class);
    }

    public String verifyCasesHeading() {

        DriverContext.waitForElementVisible(pageHeading);

        return pageHeading.getText().trim();
    }

    public String verifyPageCaption() {

        DriverContext.waitForElementVisible(pageCaption);

        return pageCaption.getText().trim();
    }

    public boolean isContextHeadingDisplayedAsH2() {

        DriverContext.waitForPageToLoad();

        List<WebElement> h2Headings = LocalDriverContext.getRemoteWebDriver()
                .findElements(By.tagName("h2"));

        List<String> expectedHeadings = Arrays.asList(
                "Cookies on Prepare a case for sentence",
                "Search",
                "Filter the case list",
                "(Today)"
        );

        for (String expected : expectedHeadings) {

            boolean found = false;

            for (WebElement heading : h2Headings) {

                if (heading.isDisplayed()
                        && heading.getText().trim().contains(expected)) {

                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }

    public int getHeadingCount(String tagName) {

        return LocalDriverContext.getRemoteWebDriver()
                .findElements(By.tagName(tagName))
                .size();
    }

    public boolean verifyHeadingHierarchy(List<Map<String, String>> headingHierarchy) {

        DriverContext.waitForPageToLoad();

        for (Map<String, String> heading : headingHierarchy) {

            String headingText = heading.get("Heading").trim();
            String headingLevel = heading.get("Level").trim().toLowerCase();

            String xpath = "//" + headingLevel +
                    "[contains(normalize-space(),'" + headingText + "')]";

            List<WebElement> elements =
                    LocalDriverContext.getRemoteWebDriver()
                            .findElements(By.xpath(xpath));

            boolean headingFound = elements.stream()
                    .anyMatch(WebElement::isDisplayed);

            System.out.println(
                    "Checking " + headingLevel +
                            " : " + headingText +
                            " Found: " + headingFound
            );

            if (!headingFound) {
                return false;
            }
        }

        return true;
    }

    public boolean isEnvironmentLabelDisplayedNextToService(String environment, String serviceName) {

        DriverContext.waitForPageToLoad();

        String serviceText = probationDigitalServicesLink.getText().trim();
        String environmentText = environmentLabel.getText().trim();

        return probationDigitalServicesLink.isDisplayed()
                && environmentLabel.isDisplayed()
                && serviceText.contains(serviceName)
                && environmentText.contains(environment);
    }

    public MyCourtsPage ClickMyCourts() {

        MyCourtDetailsLinkTxt.click();

        DriverContext.waitForPageToLoad();
        return getInstance(MyCourtsPage.class);
    }

    public boolean ConfirmActiveCourtOxfordAndSouthern() {

        return DriverContext.isElementPresent(activeCourtOxfordAndSouthern);
    }

    public boolean ConfirmActiveCourtGuildfordMagistrates() {

        return DriverContext.isElementPresent(activeCourtGuildford);
    }

    public boolean isProbationDigitalServiceDisplayed(String serviceName) {

        DriverContext.waitForPageToLoad();

        String serviceText = probationDigitalServicesLink.getText().trim();

        return probationDigitalServicesLink.isDisplayed()
                && serviceText.contains(serviceName);
    }

    public String getBrowserPageTitle() {

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver()
                .getTitle()
                .trim();
    }

    public boolean isServiceCaptionDisplayed(String expectedCaption) {

        DriverContext.waitForElementVisible(probationDigitalServicesLink);

        return probationDigitalServicesLink.isDisplayed()
                && probationDigitalServicesLink.getText()
                .trim()
                .equals(expectedCaption);
    }

    public boolean isPrepareCaseForSentenceDisplayed(String serviceName) {

        DriverContext.waitForPageToLoad();

        String serviceText = prepareCaseForSentence.getText().trim();

        return prepareCaseForSentence.isDisplayed()
                && serviceText.contains(serviceName);
    }

    public boolean isUserGuideLinkDisplayedOutsideFooter() {

        DriverContext.waitForPageToLoad();

        WebElement userGuideLink = LocalDriverContext.getRemoteWebDriver().findElement(
                By.linkText("View user guide"));

        new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(userGuideLink));

        boolean displayed = userGuideLink.isDisplayed();
        boolean enabled = userGuideLink.isEnabled();

        // Check the link is not inside the footer
        List<WebElement> footerLinks = LocalDriverContext.getRemoteWebDriver().findElement(By.tagName("footer"))
                .findElements(By.linkText("View user guide"));

        boolean outsideFooter = footerLinks.isEmpty();

        return displayed
                && enabled
                && outsideFooter;
    }

    private String originalWindowHandle;

    public UserGuideSharepointPacfsPage selectUserGuideLink() {

        DriverContext.waitForPageToLoad();

        originalWindowHandle = LocalDriverContext.getRemoteWebDriver().getWindowHandle();

        WebElement userGuideLink = LocalDriverContext.getRemoteWebDriver().findElement(
                By.linkText("View user guide"));

        DriverContext.waitForElementVisible(userGuideLink);

        userGuideLink.click();
        DriverContext.waitForPageToLoad();
        return getInstance(UserGuideSharepointPacfsPage.class);
    }

    public boolean isUserGuideOpenedInNewTab() {

        DriverContext.waitForPageToLoad();

        Set<String> windowHandles = LocalDriverContext.getRemoteWebDriver().getWindowHandles();

        if (windowHandles.size() != 2) {
            return false;
        }

        for (String handle : windowHandles) {

            if (!handle.equals(originalWindowHandle)) {

                LocalDriverContext.getRemoteWebDriver().switchTo().window(handle);

                return true;
            }
        }

        return false;
    }

    public boolean isOriginalPACFSPageStillOpen() {

        LocalDriverContext.getRemoteWebDriver().switchTo().window(originalWindowHandle);

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver().getTitle().contains("Prepare a case for sentence");
    }

    public boolean isUserGuideRedirectedToApprovedLocation() {

        DriverContext.waitForPageToLoad();

        String currentUrl = LocalDriverContext.getRemoteWebDriver().getCurrentUrl();

        WebElement heading = LocalDriverContext.getRemoteWebDriver().findElement(By.tagName("h2"));

        return currentUrl.contains("https://justiceuk.sharepoint.com/sites/HMPPS_Group_CSA/")
                && heading.isDisplayed()
                && heading.getText().trim().equals("Get the most out of Prepare a case for sentence in and out of court");
    }

    public boolean isUserGuideContentDisplayed(String expectedUrl) {

        DriverContext.waitForPageToLoad();

        String currentUrl = LocalDriverContext.getRemoteWebDriver().getCurrentUrl();

        WebElement heading = LocalDriverContext.getRemoteWebDriver().findElement(By.tagName("h2"));

        return currentUrl.contains(expectedUrl)
                && heading.isDisplayed()
                && heading.getText().trim()
                .equals("Get the most out of Prepare a case for sentence in and out of court");
    }

    /**
     * Finds a defendant in the case list across all available pages.
     *
     * @param defendantName defendant name to search for
     * @return the table row containing the defendant
     */
    private WebElement findDefendantRowAcrossPages(String defendantName) {

        WebDriverWait wait = new WebDriverWait(
                LocalDriverContext.getRemoteWebDriver(),
                Duration.ofSeconds(10)
        );

        while (true) {

            // Find defendant on the current page
            List<WebElement> defendants =
                    LocalDriverContext.getRemoteWebDriver().findElements(
                            By.xpath(
                                    "//a[contains(@class,'pac-defendant-link')" +
                                            " and normalize-space()=" +
                                            xpathText(defendantName) +
                                            "]"
                            )
                    );

            // Defendant found
            if (!defendants.isEmpty()) {

                WebElement defendant =
                        defendants.get(0);

                System.out.println(
                        "Defendant found: " + defendantName
                );

                // Return the <tr> containing the defendant
                return defendant.findElement(
                        By.xpath("./ancestor::tr")
                );
            }

            // Defendant not found on current page
            System.out.println(
                    "Defendant '" + defendantName +
                            "' not found on current page. Checking next page..."
            );

            // Find Next button
            List<WebElement> nextButtons =
                    LocalDriverContext.getRemoteWebDriver().findElements(
                            By.xpath(
                                    "//nav[@aria-label='Case list navigation']" +
                                            "//a[@rel='next']"
                            )
                    );

            // No Next button means this is the last page
            if (nextButtons.isEmpty()) {

                throw new AssertionError(
                        "Defendant '" + defendantName +
                                "' was not found in the case list."
                );
            }

            WebElement nextButton = nextButtons.get(0);

            // Make sure Next is usable
            if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {

                throw new AssertionError(
                        "Defendant '" + defendantName +
                                "' was not found. Next page button is not available."
                );
            }

            // Capture current URL before clicking Next
            String currentUrl =
                    LocalDriverContext.getRemoteWebDriver().getCurrentUrl();

            System.out.println("Clicking Next page...");

            nextButton.click();

            // Wait for the next page to load
            wait.until(driver ->
                    !driver.getCurrentUrl().equals(currentUrl)
            );

            DriverContext.waitForPageToLoad();
        }
    }


    /**
     * Checks whether the specified defendant has a Possible MAPPA flag.
     */
    public boolean isPossibleMappaPresent(String defendantName) {

        WebElement defendantRow =
                findDefendantRowAcrossPages(defendantName);

        List<WebElement> mappaFlags =
                defendantRow.findElements(
                        By.xpath(
                                ".//span[contains(@class,'pac-badge--flag')" +
                                        " and contains(normalize-space(.),'Possible MAPPA')]"
                        )
                );

        boolean mappaPresent =
                !mappaFlags.isEmpty();

        System.out.println(
                "Defendant: " + defendantName +
                        " | Possible MAPPA: " + mappaPresent
        );

        return mappaPresent;
    }


    /**
     * Checks whether the specified defendant has a Possible SFO flag.
     */
    public boolean isPossibleSfoPresent(String defendantName) {

        WebElement defendantRow =
                findDefendantRowAcrossPages(defendantName);

        List<WebElement> sfoFlags =
                defendantRow.findElements(
                        By.xpath(
                                ".//span[contains(@class,'pac-badge--flag')" +
                                        " and contains(normalize-space(.),'Possible SFO')]"
                        )
                );

        boolean sfoPresent =
                !sfoFlags.isEmpty();

        System.out.println(
                "Defendant: " + defendantName +
                        " | Possible SFO: " + sfoPresent
        );

        return sfoPresent;
    }


    /**
     * Safely creates an XPath text expression.
     * This allows defendant names containing apostrophes.
     */
    private String xpathText(String text) {

        if (!text.contains("'")) {
            return "'" + text + "'";
        }

        if (!text.contains("\"")) {
            return "\"" + text + "\"";
        }

        String[] parts = text.split("'");

        StringBuilder result =
                new StringBuilder("concat(");

        for (int i = 0; i < parts.length; i++) {

            if (i > 0) {
                result.append(", \"'\", ");
            }

            result.append("'")
                    .append(parts[i])
                    .append("'");
        }

        result.append(")");

        return result.toString();
    }

    public List<String> CreateMappaOffenceCodeReader(){

        return MappaOffenceCodeReader.getOffenceCodes();
    }

    public void refreshBrowser(){

        LocalDriverContext.getRemoteWebDriver().navigate().refresh();
    }

    public boolean isDefendantPresent(String defendantName) {

        try {
            findDefendantRowAcrossPages(defendantName);

            System.out.println(
                    "Defendant found: " + defendantName
            );

            return true;

        } catch (AssertionError e) {

            System.out.println(
                    "Defendant not found: " + defendantName
            );

            return false;
        }
    }

    public boolean isPossibleMappaBadgeRed(String defendantName) {

        String xpath =
                "//a[contains(text(),'" + defendantName + "')]" +
                        "/ancestor::tr//span[contains(text(),'Possible MAPPA')]";

        WebElement badge =
                LocalDriverContext.getRemoteWebDriver().findElement(By.xpath(xpath));

        String classes = badge.getAttribute("class");

        return classes.contains("moj-badge--red");
    }

    public boolean isHearingHighlightedYellow(String defendantName) {

        WebElement row =
                findDefendantRowAcrossPages(defendantName);

        System.out.println(
                "TR COLOUR = "
                        + row.getCssValue("background-color"));

        List<WebElement> cells =
                row.findElements(By.tagName("td"));

        for (int i = 0; i < cells.size(); i++) {

            System.out.println(
                    "TD[" + i + "] COLOUR = "
                            + cells.get(i).getCssValue("background-color"));

            System.out.println(
                    "TD[" + i + "] CLASS = "
                            + cells.get(i).getAttribute("class"));
        }

        return true;
    }

    public CaseSummaryPage selectDefendant(String defendantName) {

        WebElement defendantRow =
                findDefendantRowAcrossPages(defendantName);

        WebElement defendantLink =
                defendantRow.findElement(
                        By.xpath(".//a[contains(@class,'pac-defendant-link')]")
                );

        System.out.println(
                "Clicking defendant: " + defendantName
        );

        //DriverContext.waitForElementToBeClickable(defendantLink);

        defendantLink.click();

        DriverContext.waitForPageToLoad();

        return getInstance(CaseSummaryPage.class);
    }

    public boolean isPossibleSfoBadgePurple(String defendantName) {

        WebElement defendantRow =
                findDefendantRowAcrossPages(defendantName);

        WebElement badge =
                defendantRow.findElement(
                        By.xpath(
                                ".//span[contains(normalize-space(.),'Possible SFO')]"
                        )
                );

        String classes =
                badge.getAttribute("class");

        System.out.println(
                "SFO Badge Classes: "
                        + classes);

        return classes.contains("moj-badge--purple");
    }

    public boolean isPsrTagBlue(String defendantName) {

        WebElement defendantRow =
                findDefendantRowAcrossPages(defendantName);

        WebElement psrTag =
                defendantRow.findElement(
                        By.xpath(
                                ".//strong[contains(normalize-space(.),'PSR')]"
                        )
                                      );

        String classes =
          psrTag.getAttribute("class");

        System.out.println(
                      "PSR Classes: " + classes);
        return classes.contains("govuk-tag--blue");
    }

//    public boolean isBreachTagPurple(String defendantName) {
//
//        WebElement defendantRow =
//                findDefendantRowAcrossPages(defendantName);
//
//        WebElement breachTag =
//                defendantRow.findElement(
//                        By.xpath(
//                                ".//strong[contains(normalize-space(.),'Breach')]"
//                        )
//                );
//
//        String classes =
//                breachTag.getAttribute("class");
//
//        System.out.println(
//                "Breach Classes: " + classes);
//
//        return classes.contains("govuk-tag--purple");
//    }

    public boolean isBreachTagPurple(String defendantName) {

        WebElement breachTag =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(
                                By.xpath(
                                        "//tr[" +
                                                ".//a[contains(.,'" + defendantName + "')]" +
                                                " and " +
                                                ".//strong[normalize-space()='Breach']" +
                                                "]//strong[normalize-space()='Breach']"
                                )
                        );

        String classes =
                breachTag.getAttribute("class");

        System.out.println(
                "Breach Classes: " + classes);

        return classes.contains("govuk-tag--purple");
    }

    public boolean isPossibleNDeliusTagRed(String defendantName) {

        WebElement defendantRow =
                findDefendantRowAcrossPages(defendantName);

        WebElement ndeliusTag =
                defendantRow.findElement(
                        By.xpath(
                                ".//*[contains(normalize-space(.),'Possible nDelius Record')]"
                        )
                );

        String classes =
                ndeliusTag.getAttribute("class");

        System.out.println(
                "nDelius Classes: " + classes);

        return classes.contains("govuk-tag--red");
    }

    public void searchForDefendant(String defendantName) {

        WebElement searchBox =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(
                                By.id("search-term")
                        );

        searchBox.clear();

        searchBox.sendKeys(defendantName);

        LocalDriverContext.getRemoteWebDriver()
                .findElement(By.xpath("//button[contains(.,'Search')]"))
                .click();

        DriverContext.waitForPageToLoad();
    }

    public boolean isSearchResultHighlightedYellow(
            String defendantName) {

        WebElement row =
                findSearchResultRow(defendantName);

        List<WebElement> cells =
                row.findElements(By.tagName("td"));

        for (WebElement cell : cells) {

            String colour =
                    cell.getCssValue("background-color");

            if (!colour.equals("rgba(254, 251, 226, 1)")) {
                return false;
            }
        }

        return true;
    }

    private WebElement findSearchResultRow(String defendantName) {

        return LocalDriverContext.getRemoteWebDriver()
                .findElement(
                        By.xpath(
                                "//a[contains(@class,'pac-defendant-link')" +
                                        " and normalize-space()='" +
                                        defendantName +
                                        "']/ancestor::tr"
                        )
                );
    }

//    public boolean isMappaDisplayedBelowCrn(String defendantName) {
//
//        WebElement row =
//                findSearchResultRow(defendantName);
//
//        WebElement crn =
//                row.findElement(
//                        By.xpath(".//*[contains(text(),'B123000')]")
//                );
//
//        WebElement badge =
//                row.findElement(
//                        By.xpath(
//                                ".//span[contains(normalize-space(.),'POSSIBLE MAPPA')]"
//                        )
//                );
//
//        return badge.getLocation().getY()
//                > crn.getLocation().getY();
//    }

    public boolean isMappaDisplayedBelowCrn(String crnNumber) {

        WebElement row =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(
                                By.xpath("//tr[.//div[contains(@class,'pac-case-list-crn') and contains(normalize-space(.),'" + crnNumber + "')]]"));

        WebElement crn =
                row.findElement(
                        By.xpath(
                                ".//div[contains(@class,'pac-case-list-crn')]"
                        )
                );

        WebElement badge =
                row.findElement(
                        By.xpath(
                                ".//span[contains(normalize-space(.),'Possible MAPPA')]"
                        )
                );

        System.out.println("CRN TEXT = " + crn.getText());
        System.out.println("BADGE TEXT = " + badge.getText());

        System.out.println("CRN Y = " + crn.getLocation().getY());
        System.out.println("MAPPA Y = " + badge.getLocation().getY());

        return badge.getLocation().getY()
                > crn.getLocation().getY();
    }

    public boolean isMappaDisplayedAboveSfo(
            String defendantName) {

        WebElement row =
                findSearchResultRow(defendantName);

        WebElement mappaBadge =
                row.findElement(
                        By.xpath(
                                ".//span[contains(normalize-space(.),'Possible MAPPA')]"
                        )
                );

        WebElement sfoBadge =
                row.findElement(
                        By.xpath(
                                ".//span[contains(normalize-space(.),'Possible SFO')]"
                        )
                );

        int mappaY =
                mappaBadge.getLocation().getY();

        int sfoY =
                sfoBadge.getLocation().getY();

        System.out.println(
                "MAPPA Y = " + mappaY
        );

        System.out.println(
                "SFO Y = " + sfoY
        );

        return mappaY < sfoY;
    }

    public HearingOutcomesPage moveToHearingOutcomeNotRequired(
            String defendantName) {

        WebElement button =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(
                                By.xpath(
                                        "//tr[.//a[contains(@class,'pac-defendant-link')" +
                                                " and normalize-space()='" + defendantName + "']]" +
                                                "//a[contains(.,'Move to Hearing outcome not required')]"
                                )
                        );

        DriverContext.waitForElementToBeClickable(button);

        System.out.println(
                "Moving defendant to Hearing Outcome Not Required: "
                        + defendantName
        );

        button.click();

        DriverContext.waitForPageToLoad();

        return getInstance(HearingOutcomesPage.class);
    }
}