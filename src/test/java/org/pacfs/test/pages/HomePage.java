package org.pacfs.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'Manage people on probation')]")
    private WebElement headerTitle;

    public boolean IsTitleHeaderDisplay(){

        return headerTitle.isDisplayed();
    }
}
