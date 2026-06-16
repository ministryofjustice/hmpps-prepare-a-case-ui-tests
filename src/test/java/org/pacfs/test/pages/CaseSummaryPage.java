package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.LocalDriverContext;

public class CaseSummaryPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//main[@id='main-content']/child::div[position()=1]/child::div/child::h2")
    private WebElement CaseSummaryTitle;



    public String GetCaseSummary(){

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
}
