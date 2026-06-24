package org.pacfs.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ibi on 08/05/2026..
 */
public class Browser extends Base {

    private WebDriver _driver;
    public BrowserTypes Type;
    /**
     * @Constructor
     * @param driver
     */
    public Browser(WebDriver driver){

        _driver=driver;
    }
}