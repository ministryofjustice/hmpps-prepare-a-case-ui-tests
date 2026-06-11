package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

public class CourtCasesDetailsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//header[@class='moj-header']/following-sibling::div[position()=2]/child::div/child::div[position()=2]/descendant::span")
    private WebElement MyCourtDetailsTxt;

    @FindBy(how = How.XPATH, using = "//header[@class='moj-header']/following-sibling::div[position()=2]/child::div/child::div[position()=1]/descendant::a[position()=1]")
    private WebElement IsCasesTabSel;

    @FindBy(how = How.XPATH, using = "//header[@class='moj-header']/following-sibling::div[position()=2]/child::div/child::div[position()=1]/descendant::a[position()=2]")
    private WebElement IsOutcomeTabSel;

    @FindBy(how = How.XPATH, using = "//header[@class='moj-header']/child::div/child::div[position()=2]/child::nav/child::ul/child::li[position()=3]/child::a")
    private WebElement SignOutLnk;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'There is a problem with this service')]")
    private WebElement serviceErrMsg;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Hearing outcome still to be added')]")
    private WebElement HearingOutcomeStillToBeAdded;


    public String GetNameOfCourt(){

        DriverContext.WaitForPageToLoad();
        return MyCourtDetailsTxt.getText();
    }

    public boolean CheckCasesTabSelected(){

        return DriverContext.IsCasesTabSelected(IsCasesTabSel);
    }

    public HearingOutcomesPage ClickOutcomeTab(){

        DriverContext.WaitForElementToBeClickable(IsOutcomeTabSel);

        return GetInstance(HearingOutcomesPage.class);
    }

    public boolean IsSignOutLinkPresent(){

        return DriverContext.IsElementPresent(SignOutLnk);
    }

    public boolean ValidateSessionStability() {

        // Check 1: Ensure error message is NOT displayed
        boolean isServiceErrorDisplayed =
                DriverContext.IsElementPresent(serviceErrMsg);

        return !isServiceErrorDisplayed;
    }

    public boolean ValidateSessionUnexpectedLogout() {

        // Check 2: Ensure user is still logged in (session active indicator)
        boolean isSessionActive = DriverContext.IsElementPresent(IsCasesTabSel);

        return isSessionActive;
    }

    public String GetHearingOutcomeStillToBeAdded(){

        return HearingOutcomeStillToBeAdded.getText();
    }

    // Store defendant name so it can be used in other methods
    public static String selectedDefendantName;

    public CaseSummaryPage selectFirstDefendantName() {

        WebElement defendantNameElement = LocalDriverContext.getRemoteWebDriver().findElement(
                By.xpath("//table/tbody/tr[1]/td[position()=1]/a"));

        selectedDefendantName = defendantNameElement.getText().trim();

        System.out.println("Selected Defendant Name: " + selectedDefendantName);

        defendantNameElement.click();

        System.out.println("Clicked Defendant Link: " + selectedDefendantName);
        DriverContext.WaitForPageToLoad();
        return GetInstance(CaseSummaryPage.class);
    }

}
