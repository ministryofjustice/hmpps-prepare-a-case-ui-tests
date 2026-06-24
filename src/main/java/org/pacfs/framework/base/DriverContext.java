package org.pacfs.framework.base;

import org.pacfs.framework.config.Settings;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ibi on 08/05/2026.
 */
public class DriverContext {

    private static Select select;
    private static WebElement element;
    public static String parent;


    public static void GoToUrl(String url) {  //try to remove

        LocalDriverContext.getRemoteWebDriver().get(url);
    }

    public static void Maximize() {

        LocalDriverContext.getRemoteWebDriver().manage().window().maximize();
    }

    /**
     * @ImplicitlyWait
     */
    public static void ImplicitlyWait() {

        LocalDriverContext.getRemoteWebDriver().manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
    }

    //todo: handling uquie element ONLY
    public static boolean IsElementPresent(WebElement Locator) {
        try {
            return Locator.getSize() != null;   //Locator.equals(1);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param Locator
     * @return
     */
    public static WebElement GetElement(WebElement Locator) {
        if (IsElementPresent(Locator))
            return Locator;
        else
            throw new NoSuchElementException("Element Not Found : " + Locator.toString());
    }

    /**
     * @param locator
     */
    public static void CheckedCheckBox(WebElement locator) {
        element = GetElement(locator);
        element.click();
    }

    /**
     * @param locator
     * @return
     */
    public static boolean IsCasesTabSelected(WebElement locator) {
        element = GetElement(locator);
        String flag = element.getAttribute("aria-current");

        if (flag == null)
            return false;
        else
            return flag.equalsIgnoreCase("true") || flag.equalsIgnoreCase("page");
    }

    /**
     * @WaitForPageToLoad
     */
    public static void WaitForPageToLoad() {

        //todo: replace "Driver" to "LocalDriverContext.getRemoteWebDriver()" and replace "WebDriverWait" to var
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(120));
        JavascriptExecutor jsExecutor = LocalDriverContext.getRemoteWebDriver();

        ExpectedCondition<Boolean> jsLoad = webDriver -> LocalDriverContext.getRemoteWebDriver()
                .executeScript("return document.readyState").toString().equals("complete"); //ToDo: Tp check if page is fully loaded

        //Get JS Ready
        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady)
            wait.until(jsLoad);
        else
            Settings.logs.Write("Page is ready !");
    }

    /**
     * @param elementFindBy
     */
    public static void WaitForElementVisible(final WebElement elementFindBy) {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.visibilityOf(elementFindBy));
    }


    /**
     * @param elementFindBy
     * @param text
     */
    public static void WaitForElementTextVisible(final WebElement elementFindBy, String text) {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    /**
     * @param element
     * @param text
     */
    public static void WaitUntilTextDisplayed(final By element, String text) {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(120));
        wait.until(textDisplayed(element, text));
    }

    /**
     *
     */
    private static ExpectedCondition<Boolean> textDisplayed(final By elementFindBy, final String text) {

        return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
    }

    /**
     * @param elementFindBy
     */
    public static void WaitElementEnabled(final By elementFindBy) {

        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(120));
        wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
    }

    /**
     * @param table
     * @return
     */
    public static boolean CheckSectionsExist(List<String> table) {
        String source = LocalDriverContext.getRemoteWebDriver().getPageSource();
        for (String section : table) {
            if (!source.equals(section))
                return false;
        }
        return true;
    }

    public static boolean CheckSectionsExist2(List<String> table) {
        String source = LocalDriverContext.getRemoteWebDriver().getPageSource();
        for (String section : table) {
            if (!source.contains(section))
                return false;
        }
        return true;
    }

    public static boolean checkLinksAndURLs(Map<String, String> data) {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!LocalDriverContext.getRemoteWebDriver().findElement(By.linkText(entry.getKey())).getAttribute("href").contains(entry.getValue()))
                return false;
        }
        return true;
    }

    /**
     * Wait for element to be clickable
     *
     * @param locator
     */
    public static void WaitForElementToBeClickable(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(120));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        WaitForPageToLoad();
        Settings.logs.Write("Wait for element to be clickable");
    }

    public static void WaitForElementToBePresenceLocated(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated((By) locator));
        WaitForPageToLoad();
        Settings.logs.Write("Wait for element to be clickable");
    }

    /**
     * @param locator
     * @param index
     */
    public static void SelectElement(WebElement locator, int index) {
        select = new Select(locator);
        select.selectByIndex(index);
        Settings.logs.Write("select index " + index + " from dropdown");
    }

    /**
     * @param locator
     * @param visibletext
     */
    public static void SelectElement(WebElement locator, String visibletext) {
        select = new Select(locator);
        select.selectByVisibleText(visibletext);
        Settings.logs.Write("select " + visibletext + " from dropdown");
    }

    public static String getSelectElementText(WebElement locator) {
        Select selectDropdown = new Select(locator);
        String selectedOption = selectDropdown.getFirstSelectedOption().getAttribute("value");
        Settings.logs.Write("selected option " + selectedOption + " from dropdown");
        return selectedOption;
    }

    public static void AcceptingAlert() {

        // Switching to Alert
        Alert alert = LocalDriverContext.getRemoteWebDriver().switchTo().alert();
        // Accepting alert
        alert.accept();
    }

    public static void DismissingAlert() {

        // Switching to Alert
        Alert alert = LocalDriverContext.getRemoteWebDriver().switchTo().alert();
        // dismissing alert
        alert.dismiss();
    }

    public static void SwitchingToAlert() {

        // Switching to Alert
        Alert alert = LocalDriverContext.getRemoteWebDriver().switchTo().alert();

        System.out.println(alert.getText());
        //alert.getText().contains("");
    }

    public static void enterText(WebElement webElement, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElement,String string) : " + webElement);
            throw t;
        }
    }

    public static void MouseMover() {

        Actions keyDown = new Actions(LocalDriverContext.getRemoteWebDriver());
        keyDown.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
    }

    public static String GetCurrentWindow() {

        return parent = LocalDriverContext.getRemoteWebDriver().getWindowHandle();
    }

    public static void WindowHandling() throws InterruptedException {

        // It will return the parent window name as a String
        //parent = LocalDriverContext.getRemoteWebDriver().getWindowHandle();
        Set<String> allWindows = LocalDriverContext.getRemoteWebDriver().getWindowHandles();

        for (String curWindow : allWindows) {
            if (!parent.equals(curWindow)) {
                LocalDriverContext.getRemoteWebDriver().switchTo().window(curWindow);
                Thread.sleep(500);
                System.out.println("switch to powerApp url "+LocalDriverContext.getRemoteWebDriver().getCurrentUrl());
            }
        }
    }

    // Switch back to main web page
    public static void SwitchFrameToDefault() {

        LocalDriverContext.getRemoteWebDriver().switchTo().defaultContent();
    }

    public static void SwitchFrame(int value) throws InterruptedException {

        Thread.sleep(7000);
        SwitchFrameToDefault();
        int count = LocalDriverContext.getRemoteWebDriver().findElements(By.tagName("iframe")).size() ;
        if(count == 0){
            // No frames
        }else{
            List<WebElement> NameOfFrame = LocalDriverContext.getRemoteWebDriver().findElements(By.tagName("iframe"));
            for(WebElement s : NameOfFrame){

                //System.out.println("List of frames is : "+s.getText());
            }
            // Frames present
        }
        LocalDriverContext.getRemoteWebDriver().switchTo().frame(value);
    }

    public static void SwitchFrame(String value) {

        LocalDriverContext.getRemoteWebDriver().switchTo().frame(value);
    }

    public static void DoubleClick(WebElement ele) {

        Actions actions = new Actions(LocalDriverContext.getRemoteWebDriver());
        WebElement elementLocator = ele;
        actions.doubleClick(elementLocator).perform();
    }
}