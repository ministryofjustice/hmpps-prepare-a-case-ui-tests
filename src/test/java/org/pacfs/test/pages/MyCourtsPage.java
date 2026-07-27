package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(how = How.XPATH, using = "//span[@data-qa='probation-common-header-user-name']")
    //@FindBy(how = How.XPATH, using = "//header[@class='moj-header']/child::div/child::div[position()=2]/child::nav/child::ul/child::li[position()=3]/child::a")
    private WebElement AccountSignInLnk;

    @FindBy(how = How.XPATH, using = "//main[@id='main-content']/child::div/child::span")
    private WebElement pageCaption;

    @FindBy(xpath = "//main[@id='main-content']/descendant::span")
    private WebElement PageTitle;

    @FindBy(xpath = "//h1[contains(text(),'My courts')]")
    private WebElement myCourtsHeading;

    @FindBy(xpath = "//a[contains(text(),'Edit my courts')]")
    private WebElement editMyCourts;

    @FindBy(xpath = "//p[contains(text(),'Select a court to view the case list.')]")
    private WebElement courtInstruction;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement pageHeading;

    @FindBy(how = How.LINK_TEXT, using = "Probation Digital Services")
    private WebElement probationDigitalServicesLink;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'govuk-clearfix')]//strong[contains(text(),'DEV')]")
    private WebElement environmentLabel;


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

        return DriverContext.isElementPresent(AccountSignInLnk);
    }

    public String verifyPageCaption() {

        DriverContext.waitForElementVisible(pageCaption);

        return pageCaption.getText().trim();
    }

    public int getHeadingCount(String tagName) {

        return LocalDriverContext.getRemoteWebDriver()
                .findElements(By.tagName(tagName))
                .size();
    }

    public String getBrowserPageTitle() {

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver().getTitle().trim();
    }

    public boolean verifyHeadingHierarchy() {

        return PageTitle.getTagName().equalsIgnoreCase("span")
                && myCourtsHeading.getTagName().equalsIgnoreCase("h1")
                && editMyCourts.getTagName().equalsIgnoreCase("a")
                && courtInstruction.getTagName().equalsIgnoreCase("p")
                && isElementBefore(PageTitle, myCourtsHeading)
                && isElementBefore(myCourtsHeading, editMyCourts)
                && isElementBefore(editMyCourts, courtInstruction);
    }

    private boolean isElementBefore(WebElement first, WebElement second) {

        JavascriptExecutor js =
                (JavascriptExecutor) LocalDriverContext.getRemoteWebDriver();

        Long result = (Long) js.executeScript(
                "return arguments[0].compareDocumentPosition(arguments[1]);",
                first,
                second
        );

        return (result & 4L) != 0;
    }

    public String verifyOutcomesHeading() {

        DriverContext.waitForElementVisible(pageHeading);

        return pageHeading.getText().trim();
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

    public boolean isEnvironmentLabelVisuallyDistinct() {

        DriverContext.waitForPageToLoad();

        JavascriptExecutor js =
                (JavascriptExecutor) LocalDriverContext.getRemoteWebDriver();

        String serviceClass = probationDigitalServicesLink
                .getAttribute("class");

        String environmentClass = environmentLabel
                .getAttribute("class");

        String serviceBackground = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).backgroundColor;",
                probationDigitalServicesLink
        );

        String environmentBackground = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).backgroundColor;",
                environmentLabel
        );

        String serviceBorder = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).border;",
                probationDigitalServicesLink
        );

        String environmentBorder = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0]).border;",
                environmentLabel
        );

        return probationDigitalServicesLink.isDisplayed()
                && environmentLabel.isDisplayed()
                && (
                !serviceClass.equals(environmentClass)
                        || !serviceBackground.equals(environmentBackground)
                        || !serviceBorder.equals(environmentBorder)
        );
    }

    public boolean isEnvironmentLabelDisplayedInHeader() {

        DriverContext.waitForPageToLoad();

        WebElement header =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(By.xpath(
                                "//div[contains(@class,'govuk-clearfix')]"
                        ));

        WebElement devLabel =
                header.findElement(By.xpath(
                        ".//strong[contains(text(),'DEV')]"
                ));

        return header.isDisplayed()
                && devLabel.isDisplayed()
                && devLabel.getText()
                .trim()
                .equals("DEV");
    }

    public HomePage ClickProbationDigitalServicesLink(){

        probationDigitalServicesLink.click();

        return getInstance(HomePage.class);
    }

    public boolean isUserNameDisplayedInHeader() {

        DriverContext.waitForPageToLoad();

        WebElement header = LocalDriverContext.getRemoteWebDriver()
                .findElement(By.xpath("//div[contains(@class,'govuk-clearfix')]"));

        WebElement userName = header.findElement(
                By.xpath(".//span[contains(@data-qa,'probation-common-header-user-name')]")
        );

        return header.isDisplayed()
                && userName.isDisplayed()
                && !userName.getText().trim().isEmpty();
    }

    public CookiesPolicyPage clickCookiesPolicyLink() {

        DriverContext.waitForPageToLoad();

        WebElement cookiesLink =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(By.linkText("Cookies policy"));

        cookiesLink.click();

        DriverContext.waitForPageToLoad();

        return getInstance(CookiesPolicyPage.class);
    }

    public void selectFooterLink(String footerLink) {

        DriverContext.waitForPageToLoad();

        WebElement link = LocalDriverContext.getRemoteWebDriver()
                .findElement(By.linkText(footerLink));

        DriverContext.waitForElementVisible(link);

        link.click();
    }

    public void refreshPage() {

        LocalDriverContext.getRemoteWebDriver().navigate().refresh();

        DriverContext.waitForPageToLoad();
    }
}