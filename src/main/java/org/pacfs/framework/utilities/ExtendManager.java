package org.pacfs.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.pacfs.framework.config.Settings;
import org.openqa.selenium.Platform;

import java.io.File;
/**
 * Responsible to hold all different types of options that has been create
 */
public class ExtendManager {

    private static ExtentReports extent = new ExtentReports();
    private static Platform platform;
    private static String reportFileName = "EAExtenReport.html";
    //todo: directory of the curren user
    private static String macPath = System.getProperty("user.dir")+"/TestReport";
    private static String windowsPath = System.getProperty("user.dir")+"\\TestReport";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static ExtentReports createInstance() {

        platform = getCurrentPlatform();
        String fileName = getReportFileLocation(platform);
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(fileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(fileName);

        extent.attachReporter(htmlReporter);
        return extent;
    }

    //Select the extend report file location based on platform
    //todo: Checking what type of platform
    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        switch (platform){
            case MAC:
                reportFileLocation = macReportFileLoc;
                createReportPath(macPath);
                System.out.println("ExtenReport Path for Mac: " + macPath + "\n");
                Settings.logs.Write("ExtenReport Path for Mac: " + macPath + "\n");
                break;
            case WINDOWS:
                reportFileLocation = winReportFileLoc;
                createReportPath(windowsPath);
                System.out.println("ExtenReport Path for WINDOWS: " + windowsPath + "\n");
                Settings.logs.Write("ExtenReport Path for WINDOWS: " + windowsPath + "\n");
                break;
                default:
                    System.out.println("ExtendReport path has not been set! There is a problem!\n");
                    Settings.logs.Write("ExtendReport path has not been set! There is a problem!\n");
        }
        return reportFileLocation;
    }

    //Create the report path if it does not exist
    private static void createReportPath(String path) {
        File testDirectory = new File(path);
        if(!testDirectory.exists()){
            if(testDirectory.mkdir()){
                System.out.println("Directory: " + path + " is created");
                Settings.logs.Write("Directory: " + path + " is created");
            }else {
                System.out.println("Failed to create directory: " + path);
                Settings.logs.Write("Failed to create directory: " + path);
            }
        }else {
            System.out.println("Directory already exist: " + path);
            Settings.logs.Write("Directory already exist: " + path);
        }
    }

    //Get Current platform
    private static Platform getCurrentPlatform() {
        if(platform == null){
            String operSys = System.getProperty("os.name").toLowerCase();
            if(operSys.contains("win")){
                platform = Platform.WINDOWS;
            }else if(operSys.contains("mac")){
                platform = Platform.MAC;
            }
        }
        return platform;
    }
}