package org.pacfs.framework.base;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.pacfs.framework.config.Settings;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Ibi on 08/05/2026.
 */
public class FrameworkInitialize extends Base {

    public static void initializeBrowser(String environmentType, String Os, BrowserTypes browserType) throws MalformedURLException {
        RemoteWebDriver driver = null;
        System.out.println("\nTests are running in : " + environmentType + "\n" +
                "The Browser running is : " + browserType + "\n" +
                "The Operating system is : " + Os + "\n");
        if (environmentType.equalsIgnoreCase("local") || environmentType.equalsIgnoreCase("grid")) {
            switch (browserType) {
                case Chrome: {
                    HashMap<String, Object> chromePreferences = new HashMap<>();
                    chromePreferences.put("profile.password_manager_enabled", false);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--test-type");
                    options.addArguments("chrome.switches", "--disable-extensions");
                    options.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling");
                    options.addArguments("--no-default-browser-check");
                    options.setExperimentalOption("prefs", chromePreferences);

                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new  ChromeDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(new URL(Settings.SeleniumGridHub), options);//cap
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Chrome browser");
                    break;
                }
                case Firefox: {
                    System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
                    FirefoxOptions options = new FirefoxOptions();
                    options.setCapability("browserName", "firefox");

                    if (environmentType.equalsIgnoreCase("local")) {
                        driver = new FirefoxDriver(options);
                    } else if (environmentType.equalsIgnoreCase("grid")) {
                        driver = new RemoteWebDriver(
                                new URL(Settings.SeleniumGridHub),
                                options
                        );
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting Firefox browser");
                    break;
                }
                case IE: {
                    InternetExplorerOptions options = new InternetExplorerOptions();

                    options.setCapability("ignoreProtectedModeSettings", true);
                    options.setCapability("ignoreZoomSetting", true);
                    options.setCapability("requireWindowFocus", true);
                    options.setCapability("enablePersistentHover", true);
                    options.setCapability("acceptSslCerts", true);
                    options.setCapability("ensureCleanSession", true);

                    System.setProperty(
                            "webdriver.ie.driver",
                            System.getProperty("user.dir") + "/src/browser/IEDriverServer.exe"
                    );

                    if (environmentType.equalsIgnoreCase("local")) {

                        driver = new InternetExplorerDriver(options);

                    } else if (environmentType.equalsIgnoreCase("grid")) {

                        driver = new RemoteWebDriver(
                                new URL(Settings.SeleniumGridHub),
                                options
                        );
                    }

                    LocalDriverContext.setRemoteWebDriverThreadLocal(driver);
                    Settings.logs.Write("Starting IE browser");

                    break;
                }
            }
        }
    }
}
