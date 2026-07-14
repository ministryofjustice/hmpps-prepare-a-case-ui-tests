package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

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
}