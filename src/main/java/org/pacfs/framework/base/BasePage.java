package org.pacfs.framework.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

/**
 * Created by Ibi on 08/05/2026.
 */
public class BasePage extends Base{

    protected static String worldwideRandomNumber;

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance) {

        try {
            return (TPage) this;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    protected String getRandomString(){

        int length = 7;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public enum ElementStatus{
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }

    public static ElementStatus isElementVisible(By by, ElementStatus getStatus){
        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(LocalDriverContext.getRemoteWebDriver().findElement(by).isEnabled())
                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;
            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(LocalDriverContext.getRemoteWebDriver().findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }

    public void UIMandatoryChecks(){

        isElementVisible(By.id("Pequin-Hub-Click"), ElementStatus.PRESENT);
        isElementVisible(By.id("filter-and-fields"), ElementStatus.PRESENT);
        isElementVisible(By.id("token"), ElementStatus.PRESENT);
        isElementVisible(By.id("Table"), ElementStatus.PRESENT);
        isElementVisible(By.id("Notification_dbl"), ElementStatus.PRESENT);
        isElementVisible(By.id("Header"), ElementStatus.PRESENT);
        isElementVisible(By.id("Profile_dbl"), ElementStatus.PRESENT);
    }

    public String GetCurrentUrlString(){

        System.out.println("url is : "+LocalDriverContext.getRemoteWebDriver().getCurrentUrl());
        return LocalDriverContext.getRemoteWebDriver().getCurrentUrl();
    }

    public void ExportCurrentSearchResultToCVS(){

        isElementVisible(By.cssSelector("#root > div.sc-gWHgXt.cmQCUf > div.sc-eWvPJL.hgQjmC > div.sc-jSgupP.eplsmx > div.sc-jtHMlw.jaQVoM > div > div.sc-cKZHah.iYAzYL > div:nth-child(1) > span > svg"), ElementStatus.PRESENT);
    }

    public void BulkUploadData(){

        isElementVisible(By.id("Layer_1"), ElementStatus.PRESENT);
    }
}
