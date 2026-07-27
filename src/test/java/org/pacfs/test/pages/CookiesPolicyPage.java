package org.pacfs.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.LocalDriverContext;

import java.util.List;

public class CookiesPolicyPage extends BasePage {

    @FindBy(how = How.LINK_TEXT, using = "My courts")
    private WebElement MyCourtDetailsLinkTxt;


    public boolean isCookiesPolicyPageDisplayed() {

        DriverContext.waitForPageToLoad();

        String pageTitle =
                LocalDriverContext.getRemoteWebDriver()
                        .getTitle();

        WebElement heading =
                LocalDriverContext.getRemoteWebDriver()
                        .findElement(By.tagName("h1"));

        return pageTitle.contains("Accessibility statement")
                && heading.isDisplayed()
                && heading.getText()
                .trim()
                .contains("Cookies policy for Probation Digital Services");
    }
}
