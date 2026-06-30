package org.pacfs.framework.config;

import org.pacfs.framework.base.BrowserTypes;
import org.pacfs.framework.base.LocalDriverContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Reads configuration from environment variables (preferred) with fallback
 * to GlobalConfig.properties for local development.
 * <p>
 * Environment variable mapping:
 * PAC_AUT_URL            -> AUT
 * PAC_AUT1_URL           -> AUT1
 * PAC_USERNAME           -> UserName
 * PAC_PASSWORD           -> Password
 * PAC_BROWSER_TYPE       -> BrowserType
 * PAC_ENVIRONMENT_TYPE   -> environmentType
 * PAC_SELENIUM_GRID      -> SeleniumGrid
 * PAC_AUT_CONNECTION     -> AUTConnectionString
 * PAC_REPORTING_CONNECTION -> ReportingConnectionString
 * PAC_DRIVER_TYPE        -> DriverType
 * PAC_LOG_PATH           -> LogPath
 * PAC_EXCEL_PATH         -> ExcelSheetPath
 * PAC_EXCEL_SHEET        -> ExcelSheetName
 * PAC_AAD_URL            -> AADURL
 */
public class ConfigReader {
    //Create Property Object
    Properties p = new Properties();

    BrowserTypes bt = null;

    private static final String CONFIG_FILE_PATH = "src/main/java/org/pacfs/framework/config/GlobalConfig.properties";

    public static void populateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.readProperty();
    }

    /**
     * Returns the value from environment variable if set, otherwise falls back to properties file.
     */
    private String getConfigValue(String envVarName, String propertyName) {
        String envValue = System.getenv(envVarName);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        return p.getProperty(propertyName);
    }

    private void readProperty() throws IOException {
        // Load properties file if it exists (for local development)
        File configFile = new File(CONFIG_FILE_PATH);
        if (configFile.exists()) {
            InputStream inputStream = new FileInputStream(configFile);
            p.load(inputStream);
            inputStream.close();
        }

        // Read config values - environment variables take precedence over properties file
        Settings.LogPath = getConfigValue("PAC_LOG_PATH", "LogPath");
        Settings.AUT = getConfigValue("PAC_AUT_URL", "AUT");
        Settings.AUT1 = getConfigValue("PAC_AUT1_URL", "AUT1");
        Settings.UserName = getConfigValue("PAC_USERNAME", "UserName");
        Settings.Password = getConfigValue("PAC_PASSWORD", "Password");

        String browserType = getConfigValue("PAC_BROWSER_TYPE", "BrowserType");
        if (browserType != null) {
            Settings.BrowserType = BrowserTypes.valueOf(browserType);
        }

        Settings.ExcelSheetPath = getConfigValue("PAC_EXCEL_PATH", "ExcelSheetPath");
        Settings.ExcelSheetName = getConfigValue("PAC_EXCEL_SHEET", "ExcelSheetName");
        Settings.DriverType = getConfigValue("PAC_DRIVER_TYPE", "DriverType");
        Settings.ReportingConnectionString = getConfigValue("PAC_REPORTING_CONNECTION", "ReportingConnectionString");
        Settings.AUTConnectionString = getConfigValue("PAC_AUT_CONNECTION", "AUTConnectionString");
        Settings.SeleniumGridHub = getConfigValue("PAC_SELENIUM_GRID", "SeleniumGrid");
        Settings.AADURL = getConfigValue("PAC_AAD_URL", "AADURL");

        ArrayList envDetails = environmentDetails();
        // To run different Environments
        Settings.EnvironmentType = envDetails.get(0).toString();
    }

    public ArrayList environmentDetails() {
        String env = null;
        ArrayList ar = new ArrayList();
        String envType = System.getProperty("environmentType");
        if (envType == null || envType.isEmpty()) {
            envType = System.getenv("PAC_ENVIRONMENT_TYPE");
        }
        if ((envType == null) || envType.equals("local")) {
            env = p.getProperty("environmentType", "local");
            bt = Settings.BrowserType;
        } else if (envType.equals("BrowserStack") || envType.equals("grid")) {
            env = envType;
            String btProp = System.getProperty("BrowserType");
            if (btProp == null) {
                btProp = System.getenv("PAC_BROWSER_TYPE");
            }
            if (btProp != null) {
                bt = BrowserTypes.valueOf(btProp);
            }
        }
        LocalDriverContext.getRemoteWebDriver();
        ar.add(env);
        ar.add(bt);
        return ar;
    }
}