package org.pacfs.framework.base;

import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by Ibi on 08/05/2026.
 */
public class LocalDriverContext {

    //todo: This will be local to our driver instance
    /**
     * Because, all our existing code in framework are static variable and static method, even WebDriver is static
     *
     * A static variable is common to all the instances (or object) of the class because it is a class level variable.
     * In other words you can say that only a single copy of static variable is created and shared among all the instances of the class.
     * Memory allocation for such variables only happens once when the class is loaded in the memory. (
     * i.e. create one driver instance means that will only shared across lifecycle of the framework)
     *
     * create a non-static webdriver/remote webdriver to SPAWN multiple webdriver request from our code and
     * then this webdriver is going to send multiple request to the selenium server so that it can open multiple browser to start multple test parallel
     */
    private static ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();
    //todo: local thread


    public static RemoteWebDriver getRemoteWebDriver() {

        return remoteWebDriverThreadLocal.get();
    }

    static void setRemoteWebDriverThreadLocal(RemoteWebDriver driverThreadLocal) {

        remoteWebDriverThreadLocal.set(driverThreadLocal);
    }
}
