package org.pacfs.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ibi on 08/05/2026.
 */
public class Browser extends Base {

    private WebDriver driver;
    public BrowserTypes type;
    /**
     * @Constructor
     * @param driver
     */
    public Browser(WebDriver driver) {

        this.driver = driver;
    }
}