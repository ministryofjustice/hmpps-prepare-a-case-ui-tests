package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class CaseSummaryPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//main[@id='main-content']/child::div[position()=1]/child::div/child::h2")
    private WebElement CaseSummaryTitle;

    @FindBy(how = How.XPATH, using = "//table/descendant::form/descendant::summary/child::span")
    private WebElement expandIcon;

    @FindBy(how = How.XPATH, using = "//table/descendant::form/descendant::div/child::textarea")
    private WebElement textBox;

    @FindBy(how = How.XPATH, using = "//table/tbody/child::tr[position()=1]/descendant::form/child::textarea")
    private WebElement editTextBox;

    @FindBy(how = How.XPATH, using = "//table/descendant::form/descendant::div/child::div/child::button")
    private WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//table/tbody/child::tr[position()=1]/descendant::form/child::div/child::button")
    private WebElement editSaveButton;

    @FindBy(how = How.XPATH, using = "//table/descendant::form/descendant::div/child::div/child::a")
    private WebElement cancelButton;

    @FindBy(how = How.XPATH, using = "//tbody/child::tr[position()=1]/child::td/child::div[position()=1]/child::div[position()=1]/child::span")
    private WebElement savedText;

    @FindBy(how = How.LINK_TEXT, using = "Edit")
    private WebElement linkTextEdit;

    @FindBy(how = How.LINK_TEXT, using = "Delete")
    private WebElement linkTextDelete;

    @FindBy(how = How.XPATH, using = "//main/child::form/child::div[position()=3]/descendant::button")
    private WebElement bntDeleteNote;

    @FindBy(how = How.XPATH, using = "//main/child::div[position()=1]/child::div[position()=2]/child::p")
    private WebElement hearingNoteSuccessMessage;

    @FindBy(how = How.XPATH, using = "//form[@action='summary/files']/descendant::button")
    private WebElement chooseFileButton;

    @FindBy(how = How.XPATH, using = "//form[@action='summary/files']/child::fieldset/following-sibling::div/child::input")
    private WebElement uploadFileButton;

    @FindBy(how = How.XPATH, using = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'moj-sub-navigation__list')]//a[contains(text(),'Probation record')]")
    private WebElement probationRecord;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'moj-sub-navigation__list')]//a[contains(text(),'Risk register')]")
    private WebElement riskRegister;

    @FindBy(how = How.XPATH, using = "//main[contains(@id,'main-content')]//h2[contains(text(),'Case summary')]")
    private WebElement CasesSummary;

    @FindBy(how = How.LINK_TEXT, using = "Back")
    private WebElement backArrow;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),\"Oxford and Southern Oxfordshire Magistrates' Court\")]")
    private WebElement activeCourtOxfordAndSouthern;

    @FindBy(how = How.LINK_TEXT, using = "Probation Digital Services")
    private WebElement probationDigitalServicesLink;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement defendantNameHeading;



    public String GetCaseSummary(){

        DriverContext.waitForPageToLoad();
        return CaseSummaryTitle.getText();
    }

public static String expectedDefendantName;
    public boolean ValidateDefendantNameMatches() {

        // Locate defendant name on Case Summary page
        WebElement summaryDefendant =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(By.xpath(
                                "//main[@id='main-content']/child::div[position()=2]/child::div[position()=1]/child::h3[position()=1]/following-sibling::dl/child::div[position()=1]/child::dd"
                        ));

        String actualDefendantName = summaryDefendant.getText().trim();

        System.out.println("Expected Defendant Name: " + CourtCasesDetailsPage.selectedDefendantName);
        System.out.println("Actual Defendant Name: " + actualDefendantName);

        return actualDefendantName.equalsIgnoreCase(CourtCasesDetailsPage.selectedDefendantName.trim());
    }

    public static String expectedProbationStatus;
    public boolean ValidateProbationStatusMatches() {

        // Locate Probation Status on Case Summary page
        WebElement probationStatusElement =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(By.xpath(
                                "//section[@class='pac-key-details-bar']/div/div[1]/div[2]/div/span"
                        ));

        String rawProbationStatusText = probationStatusElement.getText().trim();

        // Remove label text if present (e.g. "Probation status: No record")
        String actualProbationStatus = rawProbationStatusText;

        if (rawProbationStatusText.toLowerCase().contains("probation status:")) {
            actualProbationStatus = rawProbationStatusText
                    .replace("Probation status:", "")
                    .trim();
        }

        System.out.println("Expected Probation Status: " + expectedProbationStatus);
        System.out.println("Actual Probation Status: " + actualProbationStatus);

        return actualProbationStatus.equalsIgnoreCase(expectedProbationStatus.trim());
    }

    public boolean ValidateProbationStatusMatches2() {

        // Locate Probation Status on Case Summary page
        WebElement probationStatusElement =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(By.xpath(
                                "//section[@class='pac-key-details-bar']/div/div[1]/div[2]/div/span"
                        ));

        String rawProbationStatusText = probationStatusElement.getText().trim();

        // ==============================
        // Extract ONLY actual value (after :)
        // ==============================

        String actualProbationStatus = rawProbationStatusText;

        if (rawProbationStatusText.contains(":")) {
            actualProbationStatus = rawProbationStatusText.split(":")[1].trim();
        }

        System.out.println("Expected Probation Status: " + CourtCasesDetailsPage.selectedProbationStatus);
        System.out.println("Actual Probation Status: " + actualProbationStatus);

        // ==============================
        // VALIDATION
        // ==============================

        return actualProbationStatus.equalsIgnoreCase(CourtCasesDetailsPage.selectedProbationStatus.trim());
    }

    public void expandHearingNote() {

        //expandIcon.click();
        DriverContext.waitForElementToBeClickable(expandIcon);
    }

    public void enterHearingNote(String noteText) {

        //DriverContext.WaitForElementToBePresenceLocated(textBox);
        textBox.clear();
        textBox.sendKeys(noteText);
    }

    public void clickSaveHearingNote() {

        //saveButton.click();
        DriverContext.waitForElementToBeClickable(saveButton);
    }

    public void clickCancelHearingNote() {

        cancelButton.click();
    }

    public String getSavedHearingNoteText() {

        DriverContext.waitForPageToLoad();
        return savedText.getText();
    }

    public boolean isEditLinkDisplayed() {

        DriverContext.waitForPageToLoad();
        return linkTextEdit.isDisplayed();
    }

    public boolean isDeleteLinkDisplayed() {

        DriverContext.waitForPageToLoad();
        return linkTextDelete.isDisplayed();
    }

    public void addHearingNote(String noteText) {
        expandHearingNote();
        enterHearingNote(noteText);
        clickSaveHearingNote();
    }

    public void clickDeleteHearingNote() {

        //linkTextDelete.click();
        DriverContext.waitForElementToBeClickable(linkTextDelete);

        DriverContext.waitForElementToBeClickable(bntDeleteNote);
    }

    public String getDeleteSuccessMessage() {

        DriverContext.waitForPageToLoad();
        return hearingNoteSuccessMessage.getText();
    }

    public void editHearingNote() {
        // Click Edit link
        DriverContext.waitForElementToBeClickable(linkTextEdit);

        // Get current value
        //DriverContext.WaitForElementToBePresenceLocated(editTextBox);
        String existingText = editTextBox.getAttribute("value");

        // Append update
        String updatedText = "Updated " + existingText;

        editTextBox.clear();
        editTextBox.sendKeys(updatedText);

        // Click Save button
        DriverContext.waitForElementToBeClickable(editSaveButton);
    }

    public void uploadFile(String fileName) throws AWTException, InterruptedException {


        String filePath = new File(
                System.getProperty("user.dir")
                        + "/src/main/resources/files/"
                        + fileName
        ).getAbsolutePath();


        chooseFileButton.click();

        // Upload file directly
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(selection, null);

        Robot robot = new Robot();

        robot.delay(1000);

        // macOS file dialog: Go to location shortcut
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);

        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        Thread.sleep(1000);


        // Paste path
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);

        // Press Enter to select file
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(2000);



        // Click upload button
        DriverContext.waitForElementToBeClickable(uploadFileButton);

        uploadFileButton.click();

    }

    public boolean isUploadedFileDisplayed(String fileName) {

        try {
            WebElement uploadedFile = LocalDriverContext.getRemoteWebDriver()
                    .findElement(By.linkText(fileName));

            DriverContext.waitForElementVisible(uploadedFile);

            return uploadedFile.isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ProbationRecordPage ClickProbationRecord(){

        probationRecord.click();
        DriverContext.waitForPageToLoad();
        return getInstance(ProbationRecordPage.class);
    }

    public RiskRegisterPage ClickRiskRegister(){

        riskRegister.click();
        return getInstance(RiskRegisterPage.class);
    }

    public Boolean IsCaseSummaryPresent(){

        return CasesSummary.isDisplayed();
    }

    public String getBrowserPageTitle() {

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver().getTitle().trim();
    }

    public CourtCasesDetailsPage ClickBackArrow(){

        backArrow.click();
        DriverContext.waitForPageToLoad();
        return getInstance(CourtCasesDetailsPage.class);
    }

    public boolean ConfirmActiveCourtOxfordAndSouthern(){

        return DriverContext.isElementPresent(activeCourtOxfordAndSouthern);
    }

    public boolean isServiceCaptionDisplayed(String expectedCaption) {

        DriverContext.waitForElementVisible(probationDigitalServicesLink);

        return probationDigitalServicesLink.isDisplayed()
                && probationDigitalServicesLink.getText()
                .trim()
                .equals(expectedCaption);
    }

    public boolean isDefendantNameDisplayedAsH1() {

        DriverContext.waitForElementVisible(defendantNameHeading);

        return defendantNameHeading.isDisplayed()
                && defendantNameHeading.getTagName().equalsIgnoreCase("h1")
                && !defendantNameHeading.getText().trim().isEmpty();
    }

    public void clickViewWithoutAssigningIfDisplayed() {

        WebDriverWait wait = new WebDriverWait(
                LocalDriverContext.getRemoteWebDriver(),
                Duration.ofSeconds(5)
        );


        List<WebElement> viewWithoutAssigning =
                LocalDriverContext.getRemoteWebDriver().findElements(
                        By.linkText("View without assigning")
                );


        if (!viewWithoutAssigning.isEmpty()
                && viewWithoutAssigning.get(0).isDisplayed()) {

            System.out.println("'View without assigning' popup displayed. Clicking...");

            WebElement link = viewWithoutAssigning.get(0);

            wait.until(ExpectedConditions.elementToBeClickable(link));

            link.click();

            System.out.println("'View without assigning' clicked successfully.");

            DriverContext.waitForPageToLoad();

        } else {

            System.out.println("'View without assigning' popup not displayed. Continuing...");
        }
    }

    public HearingOutcomesPage clickBackArrow() {

        WebDriverWait wait = new WebDriverWait(
                LocalDriverContext.getRemoteWebDriver(),
                Duration.ofSeconds(10)
        );


        WebElement backLink =
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.linkText("Back")
                ));


        System.out.println("Clicking Back arrow");

        backLink.click();


        DriverContext.waitForPageToLoad();

        System.out.println("Returned to previous page successfully");
        return getInstance(HearingOutcomesPage.class);
    }
}