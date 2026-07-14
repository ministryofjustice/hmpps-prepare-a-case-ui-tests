package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

import java.util.List;

public class MyCourtsPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//h1[text()='My courts']")
    private WebElement MyCourtsTxt;

    @FindBy(how = How.XPATH, using = "//header[@class='moj-header']/child::div/child::div[position()=2]/child::nav/child::ul/child::li[position()=3]/child::a")
    private WebElement SignOutLnk;


    public CourtCasesDetailsPage clickLinkByText(String expectedLinkText) {

        List<WebElement> links = LocalDriverContext.getRemoteWebDriver()
                .findElements(By.xpath("//main[@id='main-content']/child::nav/child::p/child::a"));

        boolean linkFound = false;

        for (WebElement link : links) {

            String actualLinkText = link.getText().trim();

            System.out.println("Available link: " + actualLinkText);

            if (actualLinkText.equalsIgnoreCase(expectedLinkText.trim())) {

                link.click();

                System.out.println("Clicked link: " + actualLinkText);

                linkFound = true;
                break;
            }
        }

        if (!linkFound) {
            throw new NoSuchElementException(
                    "Link with text '" + expectedLinkText + "' was not found.");
        }

        DriverContext.waitForPageToLoad();
        return getInstance(CourtCasesDetailsPage.class);
    }

    public String GetMyCourtsText() {

        DriverContext.waitForPageToLoad();
        DriverContext.waitForElementVisible(MyCourtsTxt);
        return MyCourtsTxt.getText();
    }

    public void NavigateThroughAllAvailableCourts() {

        By courtLinksLocator = By.xpath("//main[@id='main-content']/child::nav/child::p/child::a");
        By myCourtsLink = By.xpath("//a[text()='My courts']");

        // Get total number of courts first
        List<WebElement> courts = LocalDriverContext.getRemoteWebDriver()
                .findElements(courtLinksLocator);

        int totalCourts = courts.size();

        System.out.println("Total available courts: " + totalCourts);

        for (int i = 0; i < totalCourts; i++) {

            // Re-fetch elements every iteration to avoid stale element exception
            courts = LocalDriverContext.getRemoteWebDriver()
                    .findElements(courtLinksLocator);

            WebElement selectedCourt = courts.get(i);

            String courtName = selectedCourt.getText().trim();

            System.out.println("Selecting court: " + courtName);

            // Click selected court
            DriverContext.waitForElementToBeClickable(selectedCourt);
            //selectedCourt.click();

            // Wait for navigation
            DriverContext.waitForPageToLoad();

            // Verify user is on court cases page
            boolean isOnCourtCasesPage =
                    LocalDriverContext.getRemoteWebDriver()
                            .getCurrentUrl()
                            .contains("/cases");

            if (!isOnCourtCasesPage) {

                throw new AssertionError (
                        "Failed to navigate to Court Cases page for court: " + courtName);
            }

            System.out.println("Successfully navigated to cases page for: " + courtName);

            // Return back to My Courts page
            WebElement myCourts =
                    LocalDriverContext.getRemoteWebDriver()
                            .findElement(myCourtsLink);

            DriverContext.waitForElementToBeClickable(myCourts);

            //myCourts.click();

            // Wait for My Courts page to reload
            DriverContext.waitForPageToLoad();

            System.out.println("Returned back to My Courts page");
        }

        System.out.println("Completed navigation through all available courts");
    }

    public boolean IsMyCourtsTextPresent() {

        return DriverContext.isElementPresent(MyCourtsTxt);
    }

    public boolean IsSignOutLinkPresent() {

        return DriverContext.isElementPresent(SignOutLnk);
    }
}