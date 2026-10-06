package org.pacfs.framework.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ibi on 08/05/2026.
 */
public class BasePage extends Base {

    protected static String worldwideRandomNumber;
    @FindBy(how = How.LINK_TEXT, using = "Probation Digital Services")
    private WebElement probationDigitalServicesLink;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'govuk-clearfix')]//strong[contains(text(),'DEV')]")
    private WebElement environmentLabel;

    public <TPage extends BasePage> TPage as(Class<TPage> pageInstance) {

        try {
            return (TPage) this;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    protected String getRandomString() {

        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public enum ElementStatus {
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }

    public static ElementStatus isElementVisible(By by, ElementStatus getStatus) {
        try {
            if (getStatus.equals(ElementStatus.ENABLED)) {
                if (LocalDriverContext.getRemoteWebDriver().findElement(by).isEnabled()) {
                    return ElementStatus.ENABLED;
                }
                return ElementStatus.NOTENABLED;
            }
            if (getStatus.equals(ElementStatus.VISIBLE)) {
                if (LocalDriverContext.getRemoteWebDriver().findElement(by).isDisplayed()) {
                    return ElementStatus.VISIBLE;
                }
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        } catch (org.openqa.selenium.NoSuchElementException nse) {
            return ElementStatus.NOTPRESENT;
        }
    }

    public void uIMandatoryChecks() {

        isElementVisible(By.id("Pequin-Hub-Click"), ElementStatus.PRESENT);
        isElementVisible(By.id("filter-and-fields"), ElementStatus.PRESENT);
        isElementVisible(By.id("token"), ElementStatus.PRESENT);
        isElementVisible(By.id("Table"), ElementStatus.PRESENT);
        isElementVisible(By.id("Notification_dbl"), ElementStatus.PRESENT);
        isElementVisible(By.id("Header"), ElementStatus.PRESENT);
        isElementVisible(By.id("Profile_dbl"), ElementStatus.PRESENT);
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

    /**
     * Footer
     */
    public boolean isMoJStandardFooterDisplayed() {

        DriverContext.waitForPageToLoad();

        WebElement footer = LocalDriverContext.getRemoteWebDriver()
                .findElement(By.tagName("footer"));

        WebElement accessibilityLink = footer.findElement(
                By.linkText("Accessibility"));

        WebElement cookiesPolicyLink = footer.findElement(
                By.linkText("Cookies policy"));

        WebElement privacyPolicyLink = footer.findElement(
                By.linkText("Privacy policy"));

        return footer.isDisplayed()
                && accessibilityLink.isDisplayed()
                && cookiesPolicyLink.isDisplayed()
                && privacyPolicyLink.isDisplayed();
    }

    public boolean isFooterLayoutConsistent() {

        DriverContext.waitForPageToLoad();

        WebElement footer = LocalDriverContext.getRemoteWebDriver()
                .findElement(By.tagName("footer"));

        List<WebElement> footerLinks =
                footer.findElements(By.tagName("a"));

        List<String> expectedLinks = Arrays.asList(
                "Accessibility",
                "Cookies policy",
                "Privacy policy"
        );

        for (String expected : expectedLinks) {

            boolean found = footerLinks.stream()
                    .anyMatch(link ->
                            link.isDisplayed()
                                    && link.getText().trim().equals(expected));

            if (!found) {
                return false;
            }
        }

        return true;
    }

    public boolean isLinkVisible(String linkText) {

        DriverContext.waitForPageToLoad();

        List<WebElement> links =
                LocalDriverContext.getRemoteWebDriver()
                        .findElements(By.linkText(linkText));

        return links.stream()
                .anyMatch(WebElement::isDisplayed);
    }

    public boolean isCorrespondingFooterPageDisplayed() {

        DriverContext.waitForPageToLoad();

        String heading = LocalDriverContext.getRemoteWebDriver()
                .findElement(By.tagName("h1"))
                .getText()
                .trim();

        List<String> expectedHeadings = Arrays.asList(
                "Cookies policy for Probation Digital Services",
                "Accessibility",
                "Privacy policy for Probation Digital Services"
        );

        return expectedHeadings.contains(heading);
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

    public boolean isPossibleMappaBadgeDisplayedBelowHeader() {

        WebElement badge =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(
                                By.xpath(
                                        "//span[contains(.,'Possible MAPPA')]"
                                )
                        );

        WebElement defendantHeader =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(
                                By.tagName("h1")
                        );

        return badge.getLocation().getY()
                > defendantHeader.getLocation().getY();
    }

    public boolean isMappaDisplayedBeforeSfo() {

        List<WebElement> badges =
                LocalDriverContext.getRemoteWebDriver()
                        .findElements(
                                By.xpath(
                                        "//span[contains(@class,'moj-badge')]"
                                )
                        );

        if (badges.size() < 2) {
            return false;
        }

        String firstBadge =
                badges.get(0).getText().trim();

        String secondBadge =
                badges.get(1).getText().trim();

        System.out.println(
                "Badge order: "
                        + firstBadge
                        + " -> "
                        + secondBadge);

        return firstBadge.contains("MAPPA")
                && secondBadge.contains("SFO");
    }

}
