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

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public MyCourtsPage ClickMyCourts(){

        MyCourtDetailsLinkTxt.click();

        DriverContext.waitForPageToLoad();
        return getInstance(MyCourtsPage.class);
    }

    public boolean ConfirmActiveCourtOxfordAndSouthern(){

        return DriverContext.isElementPresent(activeCourtOxfordAndSouthern);
    }

    public boolean ConfirmActiveCourtGuildfordMagistrates(){

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
}