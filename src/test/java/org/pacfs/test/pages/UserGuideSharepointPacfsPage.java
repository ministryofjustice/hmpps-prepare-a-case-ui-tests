package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

import java.util.Set;

public class UserGuideSharepointPacfsPage extends BasePage {

    @FindBy(how = How.NAME, using = "username")
    private WebElement UsernameTxt;


    public boolean isUserGuideRedirectedToApprovedLocation() {

        DriverContext.waitForPageToLoad();

        String parentWindow = LocalDriverContext.getRemoteWebDriver().getWindowHandle();

        // Switch to the newly opened tab
        for (String windowHandle : LocalDriverContext.getRemoteWebDriver().getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                LocalDriverContext.getRemoteWebDriver().switchTo().window(windowHandle);
                break;
            }
        }

        DriverContext.waitForPageToLoad();

        String currentUrl = LocalDriverContext.getRemoteWebDriver().getCurrentUrl();

        WebElement heading = LocalDriverContext.getRemoteWebDriver().findElement(
                By.xpath("//h2[normalize-space()='Get the most out of Prepare a case for sentence in and out of court']")
        );

        return currentUrl.contains("https://justiceuk.sharepoint.com/sites/HMPPS_Group_CSA/")
                && heading.isDisplayed();
    }

    public boolean isUserGuideContentDisplayed(String expectedUrl) {

        DriverContext.waitForPageToLoad();

        String currentUrl = LocalDriverContext.getRemoteWebDriver().getCurrentUrl();

        WebElement heading = LocalDriverContext.getRemoteWebDriver().findElement(By.tagName("h2"));

        return currentUrl.contains(expectedUrl)
                && heading.isDisplayed()
                && heading.getText().trim()
                .equals("Get the most out of Prepare a case for sentence in and out of court");
    }

    private String originalWindowHandle;
    public boolean isUserGuideOpenedInNewTab() {

        DriverContext.waitForPageToLoad();

        Set<String> windowHandles = LocalDriverContext.getRemoteWebDriver().getWindowHandles();

        if (windowHandles.size() != 2) {
            return false;
        }

        for (String handle : windowHandles) {

            if (!handle.equals(originalWindowHandle)) {

                LocalDriverContext.getRemoteWebDriver().switchTo().window(handle);

                return true;
            }
        }

        return false;
    }

    public boolean isOriginalPACFSPageStillOpen() {

      //  LocalDriverContext.getRemoteWebDriver().switchTo().window(originalWindowHandle);

        DriverContext.waitForPageToLoad();

        return LocalDriverContext.getRemoteWebDriver().getTitle().contains("Prepare a case for sentence");
    }
}
