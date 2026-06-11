package org.pacfs.framework.config;

import org.pacfs.framework.base.BrowserTypes;
import org.pacfs.framework.base.LocalDriverContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Ibi on 08/05/2026.
 */

public class ConfigReader {
    //Create Property Object
    Properties p = new Properties();
    String env;
    String os;
    BrowserTypes bt = null;

    public static  void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }


    private void ReadProperty() throws IOException {
        //Load the Property file available in same package
        InputStream inputStream = new FileInputStream("src/main/java/org/pacfs/framework/config/GlobalConfig.properties");
        p.load(inputStream);

        /**Get LogPath*/
        Settings.LogPath = p.getProperty("LogPath");
        /**Get AUT*/
        Settings.AUT = p.getProperty("AUT");
        /**Get AUT1*/
        Settings.AUT1 = p.getProperty("AUT1");
        /** User Name**/
        Settings.UserName = p.getProperty("UserName");
        /** Password**/
        Settings.Password = p.getProperty("Password");
        /**Get Browser type*/
        Settings.BrowserType = BrowserTypes.valueOf(p.getProperty("BrowserType"));
        /**GEt ExcelSheetPath*/
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
        /**GEt ExcelSheetName*/
        Settings.ExcelSheetName = p.getProperty("ExcelSheetName");
        //Get DriverType
        Settings.DriverType = p.getProperty("DriverType");
        /**Get Reporting String*/
        Settings.ReportingConnectionString = p.getProperty("ReportingConnectionString");
        /**Get AUTConnection String*/
        Settings.AUTConnectionString = p.getProperty("AUTConnectionString");
        /**Selenium grid Hub*/
        Settings.SeleniumGridHub = p.getProperty("SeleniumGrid");
        // AAD url
        Settings.AADURL=p.getProperty("AADURL");

        ArrayList envDetails =environmentDetails();
        // To run different Environments
        Settings.EnvironmentType = envDetails.get(0).toString();
    }

    public ArrayList environmentDetails() {
        ArrayList ar = new ArrayList();
        String envType = System.getProperty("environmentType");
        if ((envType==null) || envType.equals("local")){
            env = p.getProperty("environmentType");
            //os = p.getProperty("Os");
            bt = BrowserTypes.valueOf(p.getProperty("BrowserType"));
        } else if (envType.equals("BrowserStack") || envType.equals("grid")) {
            env = envType;
            //os = System.getProperty("Os");
            bt = BrowserTypes.valueOf(System.getProperty("BrowserType"));
        }
        LocalDriverContext.getRemoteWebDriver();
        ar.add(env);
        //ar.add(os);
        ar.add(bt);
        return ar;
    }

}
