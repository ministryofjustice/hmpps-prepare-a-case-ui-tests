package org.pacfs.test.stepdefs;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.FrameworkInitialize;
import org.pacfs.framework.base.LocalDriverContext;
import org.pacfs.framework.config.ConfigReader;
import org.pacfs.framework.config.Settings;
import org.pacfs.framework.utilities.LogUtil;

import java.io.IOException;

/**
 * Created by Ibi on 11/05/2026.
 */
public class TestInitialize extends FrameworkInitialize {

    @Before
    public void Initialize(Scenario scenario) throws IOException {

        System.out.println("\n---------------------------------------------------------");
        System.out.println("Current Scenario running is : " + scenario.getName()+"\n");

        // Initialise Config
        ConfigReader.populateSettings();

        // Logging
        Settings.logs = new LogUtil();
        Settings.logs.createLogFile();
        Settings.logs.write("Framework Initialize");


       /*  //todo: Create Test Cycle for Reporting
         Settings.ReportingConnection = DatabaseUtil.Open(Settings.ReportingConnectionString);
         ReportingUtil.CreateTestCycle(Settings.ReportingConnection);
         Settings.logs.Write("Test Cycle Created");*/

        // Initialise Browser
        initializeBrowser(Settings.EnvironmentType, Settings.Os,Settings.BrowserType);
        Settings.logs.write("Browser Initialized");

        // Navigate to URL
        DriverContext.goToUrl(Settings.AUT);
        Settings.logs.write("Navigated to URL " + Settings.AUT);

        // Maximize url
        DriverContext.maximize();

        // Implicitly Wait
        DriverContext.implicitlyWait();
    }

    @After
    public void TearDownTest(Scenario scenario) {

        // Logging
        Settings.logs.write(scenario.getName());
        Settings.logs.write(String.format("Status is: %s", scenario.getName()));

        if (scenario.isFailed()) {
            Settings.logs.write("ie. scenario Not KO : --> capture !");
            Settings.logs.write("Take screenshot");
            try {
                final byte[] screenshot = LocalDriverContext.getRemoteWebDriver().getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "failure-screenshot");
            } catch (Exception e) {
                Settings.logs.write("Unable to take screenshot");
                e.printStackTrace();
            }
        }


        // Close browser
        Settings.logs.write("Closing browser");
        if (LocalDriverContext.getRemoteWebDriver() != null) {
            LocalDriverContext.getRemoteWebDriver().close();
            LocalDriverContext.getRemoteWebDriver().quit();
        }
    }//ahmed.awesu@beisdevtest.onmicrosoft.com
}