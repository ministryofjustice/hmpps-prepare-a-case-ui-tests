package org.pacfs.test.testRunners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Ibi on 08/05/2026.
 */
@CucumberOptions(features = {"src/test/java/org/pacfs/test/features"}, glue = {"org/pacfs/test/stepdefs"},
        plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
})
public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Test(dataProvider = "features")
    public void runTest(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {

        //todo: insert the feature name
        //ExtendReport.startFeature("");
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());//ToDo: to run all cucumber features
    }

    @DataProvider
    public Object[][] features() {

        return testNGCucumberRunner.provideScenarios();// ToDo: provide all the features available
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {

        testNGCucumberRunner.finish();
    }
}