package org.pacfs.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

public class ProbationRecordPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//ul[contains(@class,'govuk-summary-card__actions')]//a[contains(text(),'View record')]")
    private WebElement viewRecord;

    @FindBy(how = How.LINK_TEXT, using = "Probation Digital Services")
    private WebElement probationDigitalServicesLink;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement defendantNameHeading;


    public AdultCustodyPage ClickViewRecord(){

        viewRecord.click();
        DriverContext.waitForPageToLoad();
        return getInstance(AdultCustodyPage.class);
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

    public String getBrowserPageTitle() {

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver().getTitle().trim();
    }
}
