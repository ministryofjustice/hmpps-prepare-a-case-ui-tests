package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HearingOutcomesPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'In progress')]")
    private WebElement InProgressTab;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Resulted cases')]")
    private WebElement ResultedCasesTab;

    @FindBy(how = How.XPATH, using = "//header[@class='moj-header']/following-sibling::div[position()=2]/child::div/child::div[position()=1]/descendant::a[position()=1]")
    private WebElement CasesTab;


    public void validateDuplicateDefendantNamesAcrossPages2() {

        List<String> allDefendantNames = new ArrayList<>();

        int currentPage = 1;

        while (true) {

            System.out.println("========== PAGE " + currentPage + " ==========");

            // ==============================
            // CAPTURE DEFENDANT NAMES
            // ==============================

            List<WebElement> defendantRows = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//tr[@class='govuk-table__row']/child::td[position()=2]/child::a"));

            for (WebElement row : defendantRows) {

                String defendantName = row.getText().trim();

                // Remove 'Unknown'
                defendantName = defendantName.replace("Unknown", "").trim();

                System.out.println(defendantName);

                allDefendantNames.add(defendantName);
            }

            // ==============================
            // FIND NEXT BUTTON
            // ==============================

            List<WebElement> nextButtons = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//nav[contains(@class,'moj-pagination')]//a[contains(text(),'Next')]"));

            // No next button found
            if (nextButtons.isEmpty()) {

                System.out.println("No Next button found. Pagination finished.");

                break;
            }

            WebElement nextButton = nextButtons.get(0);

            // Next button not clickable
            if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {

                System.out.println("Next button is not clickable.");

                break;
            }

            // ==============================
            // CLICK NEXT BUTTON
            // ==============================

            System.out.println("Clicking Next button...");

            nextButton.click();

            // Wait for next page to load
            WebDriverWait wait = new WebDriverWait(
                    LocalDriverContext.getRemoteWebDriver(),
                    Duration.ofSeconds(10));

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[@class='govuk-table__row']/child::td[position()=2]/child::a")));

            currentPage++;
        }

        // ==============================
        // CHECK FOR DUPLICATES
        // ==============================

        System.out.println("========== CHECKING DUPLICATES ==========");

        Set<String> uniqueNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        for (String name : allDefendantNames) {

            if (!uniqueNames.add(name)) {

                duplicateNames.add(name);
            }
        }

        // ==============================
        // ASSERT RESULTS
        // ==============================

        if (!duplicateNames.isEmpty()) {

            System.out.println("Duplicate defendant names found:");

            for (String duplicate : duplicateNames) {

                System.out.println("Duplicate: " + duplicate);
            }

            Assert.fail("Duplicate defendant names found: " + duplicateNames);

        } else {

            System.out.println("No duplicate defendant names found.");
        }
    }

    // Store defendant name so it can be used in other methods
    public static String selectedDefendantName;

    public void selectFirstDefendantCheckbox() {

        // Capture defendant name from first row
        WebElement defendantNameElement = LocalDriverContext.getRemoteWebDriver().findElement(

                By.xpath("//table/child::tbody/child::tr[position()=1]/child::td[position()=2]/child::a"));

        selectedDefendantName = defendantNameElement.getText().trim();

        System.out.println("Selected Defendant Name: " + selectedDefendantName);

        // Locate checkbox from same row
        WebElement checkbox = LocalDriverContext.getRemoteWebDriver().findElement(

                By.xpath("//table/child::tbody/child::tr[position()=1]/child::td[position()=1]/child::div/child::div/child::div/child::input"));

        // Click checkbox if not already selected
        if (!checkbox.isSelected()) {

            checkbox.click();

            System.out.println("Checkbox selected for: " + selectedDefendantName);
        }
    }

    public void assignSelectedCaseToMe() {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));

        // ==============================
        // CLICK ACTIONS BUTTON
        // ==============================

        WebElement actionsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[text()='Actions']")));

        actionsButton.click();

        System.out.println("Clicked Actions button");

        // ==============================
        // HOVER OVER 'ASSIGN TO ME'
        // ==============================

        WebElement assignToMeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@value='assign']")));

        Actions actions = new Actions(LocalDriverContext.getRemoteWebDriver());

        actions.moveToElement(assignToMeButton).perform();

        System.out.println("Hovered over Assign to me");

        // ==============================
        // CLICK 'ASSIGN TO ME'
        // ==============================

        wait.until(ExpectedConditions.elementToBeClickable(assignToMeButton));

        assignToMeButton.click();

        System.out.println("Clicked Assign to me");
    }

    public void validateAssignToMeSuccessMessage() {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));

        // ==============================
        // VALIDATE SUCCESS MESSAGE TITLE
        // ==============================

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//main/child::div/child::div/child::h2")));

        String actualSuccessMessage = successMessage.getText().trim();

        System.out.println("Success Message: " + actualSuccessMessage);

        Assert.assertTrue(actualSuccessMessage.contains("Success"));

        // ==============================
        // VALIDATE SUCCESS DESCRIPTION
        // ==============================

        WebElement successDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//main/child::div/child::div[position()=2]/child::p")));

        String actualDescription = successDescription.getText().trim();

        System.out.println("Actual Description: " + actualDescription);

        // Validate beginning text
        Assert.assertTrue(
                actualDescription.startsWith("You are assigned to result "));

        // Validate ending text
        Assert.assertTrue(
                actualDescription.endsWith(". Their case has moved to the in progress tab."));
    }

    public void validateSelectedDefendantNameNoLongerExistsAcrossPages() {

        List<String> allDefendantNames = new ArrayList<>();

        int currentPage = 1;

        while (true) {

            System.out.println("========== PAGE " + currentPage + " ==========");

            // ==============================
            // CAPTURE DEFENDANT NAMES
            // ==============================

            List<WebElement> defendantRows = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//tr[@class='govuk-table__row']/child::td[position()=2]/child::a"));

            for (WebElement row : defendantRows) {

                String defendantName = row.getText().trim();

                System.out.println(defendantName);

                allDefendantNames.add(defendantName);
            }

            // ==============================
            // FIND NEXT BUTTON
            // ==============================

            List<WebElement> nextButtons = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//nav[contains(@class,'moj-pagination')]//a[contains(text(),'Next')]"));

            // No next button found
            if (nextButtons.isEmpty()) {

                System.out.println("No Next button found. Pagination finished.");

                break;
            }

            WebElement nextButton = nextButtons.get(0);

            // Next button not clickable
            if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {

                System.out.println("Next button is not clickable.");

                break;
            }

            // ==============================
            // CLICK NEXT BUTTON
            // ==============================

            System.out.println("Clicking Next button...");

            nextButton.click();

            // Wait for next page to load
            WebDriverWait wait = new WebDriverWait(
                    LocalDriverContext.getRemoteWebDriver(),
                    Duration.ofSeconds(10));

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[@class='govuk-table__row']/child::td[position()=2]/child::a")));

            currentPage++;
        }

        // ==============================
        // VALIDATE DEFENDANT NAME
        // ==============================

        System.out.println("========== VALIDATING DEFENDANT NAME ==========");

        System.out.println("Selected Defendant Name: " + selectedDefendantName);

        boolean defendantStillExists = false;

        for (String storedDefendantName : allDefendantNames) {

            //if (storedDefendantName.equalsIgnoreCase(selectedDefendantName)) {
            if (selectedDefendantName.contains(storedDefendantName)) {

                defendantStillExists = true;

                break;
            }
        }

        // ==============================
        // ASSERT RESULTS
        // ==============================

        Assert.assertFalse(
                defendantStillExists,
                "Defendant name still exists in the list: " + selectedDefendantName);

        System.out.println("Defendant name no longer exists in the list.");
    }

    public void ClickInProgressTab(){

        DriverContext.WaitForElementToBeClickable(InProgressTab);
    }

    public void validateSelectedDefendantExistsAcrossPages() {

        List<String> allDefendantNames = new ArrayList<>();

        int currentPage = 1;

        while (true) {

            System.out.println("========== PAGE " + currentPage + " ==========");

            // ==============================
            // CAPTURE DEFENDANT NAMES
            // ==============================

            List<WebElement> defendantRows = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//tr[@class='govuk-table__row']/child::td[position()=1]/child::a"));

            for (WebElement row : defendantRows) {

                String defendantName = row.getText().trim();

                System.out.println(defendantName);

                allDefendantNames.add(defendantName);
            }

            // ==============================
            // FIND NEXT BUTTON
            // ==============================

            List<WebElement> nextButtons = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//nav[contains(@class,'moj-pagination')]//a[contains(text(),'Next')]"));

            if (nextButtons.isEmpty()) {

                System.out.println("No Next button found. Pagination finished.");
                break;
            }

            WebElement nextButton = nextButtons.get(0);

            if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {

                System.out.println("Next button is not clickable.");
                break;
            }

            System.out.println("Clicking Next button...");
            nextButton.click();

            WebDriverWait wait = new WebDriverWait(
                    LocalDriverContext.getRemoteWebDriver(),
                    Duration.ofSeconds(10));

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tr[@class='govuk-table__row']/child::td[position()=1]/child::a")));

            currentPage++;
        }

        // ==============================
        // VALIDATE SELECTED NAME EXISTS
        // ==============================

        System.out.println("========== VALIDATING SELECTED DEFENDANT ==========");

        System.out.println("Selected Defendant: " + selectedDefendantName);

        boolean found = false;

        for (String name : allDefendantNames) {

            //if (name.equalsIgnoreCase(selectedDefendantName)) {
            if (selectedDefendantName.contains(name)) {

                found = true;
                break;
            }
        }

        Assert.assertTrue(
                found,
                "Selected defendant name NOT found in case list: " + selectedDefendantName);

        System.out.println("Selected defendant exists in the list ✔");
    }

    public void clickMoveToResultedForSelectedDefendant() {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));

        int currentPage = 1;

        boolean defendantFound = false;

        while (true) {

            System.out.println("========== PAGE " + currentPage + " ==========");

            // ==============================
            // GET ALL ROWS
            // ==============================

            List<WebElement> tableRows = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//tbody[@class='govuk-table__body']/child::tr"));

            // ==============================
            // LOOP THROUGH EACH ROW
            // ==============================

            for (WebElement row : tableRows) {

                // Capture defendant name from current row
                WebElement defendantElement = row.findElement(
                        By.xpath("./child::td/child::a"));

                String defendantName = defendantElement.getText().trim();

                System.out.println("Defendant Found: " + defendantName);

                // ==============================
                // MATCH DEFENDANT NAME
                // ==============================

                //if (defendantName.equalsIgnoreCase(selectedDefendantName)) {
                if (selectedDefendantName.contains(defendantName)) {

                    System.out.println("Matched Defendant: " + selectedDefendantName);

                    // Locate Move to resulted button on same row
                    WebElement moveToResultedButton = row.findElement(
                            By.xpath("./child::td[position()=6]/child::a"));

                    wait.until(ExpectedConditions.elementToBeClickable(moveToResultedButton));

                    moveToResultedButton.click();

                    System.out.println("Clicked 'Move to resulted' button");

                    defendantFound = true;

                    break;
                }
            }

            // ==============================
            // STOP IF DEFENDANT FOUND
            // ==============================

            if (defendantFound) {

                break;
            }

            // ==============================
            // FIND NEXT BUTTON
            // ==============================

            List<WebElement> nextButtons = LocalDriverContext.getRemoteWebDriver().findElements(
                    By.xpath("//nav[contains(@class,'moj-pagination')]//a[contains(text(),'Next')]"));

            if (nextButtons.isEmpty()) {

                System.out.println("No Next button found.");

                break;
            }

            WebElement nextButton = nextButtons.get(0);

            if (!nextButton.isDisplayed() || !nextButton.isEnabled()) {

                System.out.println("Next button is not clickable.");

                break;
            }

            // ==============================
            // CLICK NEXT PAGE
            // ==============================

            System.out.println("Clicking Next button...");

            nextButton.click();

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//tbody[@class='govuk-table__body']/child::tr")));

            currentPage++;
        }

        // ==============================
        // ASSERT DEFENDANT FOUND
        // ==============================

        Assert.assertTrue(
                defendantFound,
                "Selected defendant name not found: " + selectedDefendantName);

        System.out.println("Successfully processed defendant: " + selectedDefendantName);
    }

    public void validateMoveToResultedSuccessMessage() {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(10));

        // ==============================
        // VALIDATE SUCCESS MESSAGE TITLE
        // ==============================

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//main/child::div/child::div/child::h2")));

        String actualSuccessMessage = successMessage.getText().trim();

        System.out.println("Success Message: " + actualSuccessMessage);

        Assert.assertTrue(actualSuccessMessage.contains("Success"));

        // ==============================
        // VALIDATE SUCCESS DESCRIPTION
        // ==============================

        WebElement successDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//main/child::div/child::div[position()=2]/child::p")));

        String actualDescription = successDescription.getText().trim();

        System.out.println("Actual Description: " + actualDescription);

        // Validate beginning text
        Assert.assertTrue(
                actualDescription.startsWith("You have moved "));

        // Validate ending text
        Assert.assertTrue(
                actualDescription.endsWith("case to resulted cases."));
    }

    public void ClickResultedCasesTab(){

        DriverContext.WaitForElementToBeClickable(ResultedCasesTab);
    }

    public CourtCasesDetailsPage ClickCasesTab(){

        DriverContext.WaitForElementToBeClickable(CasesTab);
        DriverContext.WaitForPageToLoad();
        return GetInstance(CourtCasesDetailsPage.class);
    }

}
