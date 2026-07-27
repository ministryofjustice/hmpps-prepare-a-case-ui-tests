package org.pacfs.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

import java.time.Duration;

public class AdultCustodyPage extends BasePage {

    @FindBy(how = How.LINK_TEXT, using = "View contact list (opens in NDelius)")
    private WebElement viewContactListLink;

    @FindBy(how = How.LINK_TEXT, using = "Risk register")
    private WebElement riskRegisterLink;



    public boolean isViewContactListLinkDisplayedAndClickable() {

        DriverContext.waitForElementVisible(viewContactListLink);

        WebDriverWait wait = new WebDriverWait(
                LocalDriverContext.getRemoteWebDriver(),
                Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(viewContactListLink));

        return viewContactListLink.isDisplayed()
                && viewContactListLink.isEnabled()
                && viewContactListLink.getText().trim()
                .equals("View contact list (opens in NDelius)");
    }

    public String getBrowserPageTitle() {

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver().getTitle().trim();
    }

    public RiskRegisterPage ClickRiskRegister(){

        riskRegisterLink.click();
        return getInstance(RiskRegisterPage.class);
    }
}
