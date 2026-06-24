package org.pacfs.test.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.pacfs.framework.base.BasePage;
import org.pacfs.framework.base.DriverContext;

public class SignInPage extends BasePage {

    @FindBy(how = How.NAME, using = "username")
    private WebElement UsernameTxt;

    @FindBy(how = How.NAME, using = "password")
    private WebElement PasswordTxt;

    @FindBy(how = How.ID, using = "submit")
    private WebElement SignInBnt;


    public void EnterUsername(String user) {

        DriverContext.enterText(UsernameTxt,user);
    }

    public void EnterPassword(String pass) {

        DriverContext.enterText(PasswordTxt,pass);
    }

    public MyCourtsPage ClickSignInButton() {

        DriverContext.WaitForElementToBeClickable(SignInBnt);
        DriverContext.WaitForPageToLoad();
        return GetInstance(MyCourtsPage.class);
    }
}