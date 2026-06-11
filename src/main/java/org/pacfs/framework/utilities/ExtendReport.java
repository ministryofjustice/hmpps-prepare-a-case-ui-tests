package org.pacfs.framework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ibi on 08/05/2026.
 */

public class ExtendReport {
    public static ExtentReports extentReports = ExtendManager.createInstance();
    private static ExtentTest extentTest = null;
    private static Map extentFeatureMap = new HashMap();
    private static Map extentScenariosMap = new HashMap();

    //public static ExtentTest feature;
    //public static ExtentTest scenario;

    public static synchronized ExtentTest getFeature(){

        return (ExtentTest) extentFeatureMap.get((int)(long)(Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest getScenario(){

        return (ExtentTest) extentScenariosMap.get((int)(long)(Thread.currentThread().getId()));
    }

    public static synchronized ExtentTest startFeature(String featureName)throws ClassNotFoundException{

        ExtentTest test = extentReports.createTest(new GherkinKeyword("Feature"), featureName);
        extentScenariosMap.put((int)(long) (Thread.currentThread().getId()), test);
        return test;
    }

    public static synchronized ExtentTest startScenario(String scenarioName) throws ClassNotFoundException {
        extentTest = getFeature();
        extentTest = extentTest.createNode(new GherkinKeyword("Scenario"), scenarioName);
        extentScenariosMap.put((int)(long) (Thread.currentThread().getId()), extentTest);
        return extentTest;
    }
}
