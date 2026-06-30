package org.pacfs.test.stepdefs;


import org.pacfs.framework.base.DriverContext;
import org.pacfs.framework.base.FrameworkInitialize;
import org.pacfs.framework.base.LocalDriverContext;
import org.pacfs.framework.config.ConfigReader;
import org.pacfs.framework.config.Settings;
import org.pacfs.framework.utilities.*;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

/**
 * Created by Ibi on 11/05/2026.
 */
public class TestInitialize extends FrameworkInitialize {

    @Before
    public void Initialize(Scenario scenario) throws IOException {

        //todo: insert the scenario name
        //ExtendReport.startScenario(scenario.getName());

      System.out.println("\n---------------------------------------------------------");
      System.out.println("Current Scenario running is : " + scenario.getName()+"\n");


        //ToDo: Initialize Config
        ConfigReader.populateSettings();

        //ToDo: Logging
        Settings.logs = new LogUtil();
        Settings.logs.createLogFile();
        Settings.logs.write("Framework Initialize");


       /*  //todo: Create Test Cycle for Reporting
         Settings.ReportingConnection = DatabaseUtil.Open(Settings.ReportingConnectionString);
         ReportingUtil.CreateTestCycle(Settings.ReportingConnection);
         Settings.logs.Write("Test Cycle Created");*/

        //ToDo: Browser Initialized
        initializeBrowser(Settings.EnvironmentType, Settings.Os,Settings.BrowserType);
        Settings.logs.write("Browser Initialized");

        //ToDo: Navigate to url
        //DriverContext.Browser.GoToUrl(Settings.AUT);
        DriverContext.goToUrl(Settings.AUT);
         Settings.logs.write("Navigated to URL " + Settings.AUT);

        //ToDo: Maximize url
        //DriverContext.Browser.Maximize();
        DriverContext.maximize();

        //ToDo: Implicitly Wait
        DriverContext.implicitlyWait();

        try {
            ExcelUtil util = new ExcelUtil(System.getProperty("user.dir") + Settings.ExcelSheetPath, Settings.ExcelSheetName);
        } catch (Exception e) {

        }
    }

    @After
    public void TearDownTest(Scenario scenario) {

        //ToDo: Logging
        Settings.logs = new LogUtil();

        if (scenario.isFailed()) {
            Settings.logs.write(scenario.getName());
            Settings.logs.write("ie. scenario Not KO : --> capture !");

            //ToDo: Take screenshot
            Settings.logs.write("Take screenshot");
            try {
                //final byte[] screenshot = ((TakesScreenshot) Driver)
                final byte[] screenshot = ((TakesScreenshot) LocalDriverContext.getRemoteWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
      //          scenario.embed(screenshot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (scenario.getStatus().equals("pass")) {

            Settings.logs.write(scenario.getName());
            Settings.logs.write("ie. scenario KO : --> capture !");
        }


        //ToDo: Closing browser
        Settings.logs.write("Closing browser");
        //if (Driver != null) {
        if (LocalDriverContext.getRemoteWebDriver() != null) {
            //Driver.close();
            LocalDriverContext.getRemoteWebDriver().close();
            LocalDriverContext.getRemoteWebDriver().quit();
        }
    }//ahmed.awesu@beisdevtest.onmicrosoft.com
}