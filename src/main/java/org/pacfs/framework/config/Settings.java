package org.pacfs.framework.config;

import org.pacfs.framework.base.BrowserTypes;
import org.pacfs.framework.utilities.LogUtil;

import java.sql.Connection;

/**
 * Created by Ibi on 08/05/2026.
 */
public class Settings {
    /**
     * @Configuration should have information required to control the framework or give some details which can be
     * used as a record for the test session. These come very handy while working with large team
     * @Application URL
     * @Browser
     * @TestType
     * @Technology
     * @LogPath
     */

    //For Application Backend
    public static Connection AUTConnection;
    //For EARS Reporting
    public static Connection ReportingConnection;
    //For Application backend connection string
    public static String AUTConnectionString;
    public static String ReportingConnectionString;

    /**
     * Log Path for framework
     */
    public static String LogPath;

    //Driver Type for SQL Server connectivity
    public static String DriverType;

    public static BrowserTypes BrowserType;
    public static String ExcelSheetPath;
    public static String ExcelSheetName;
    public static String AUT;
    public static LogUtil logs;
    public static String SeleniumGridHub;
    public static String CustomerUser;
    public static String AUT1;
    public static String UserName;
    public static String Password;
    public static String RedirectURL;
    public static String EnvironmentType;
    public static String Os;
    public static String AADURL;
}