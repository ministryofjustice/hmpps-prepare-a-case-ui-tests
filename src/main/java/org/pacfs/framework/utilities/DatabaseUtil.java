package org.pacfs.framework.utilities;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;

public class DatabaseUtil {
    /**
     * We perform some of the common operation in database while it comes to automation testing like
     * Reading data from DB (Application DB)
     * Writing data into DB (very rare)
     * Running some sort of query or stored procedures
     * @JDBC driver- we use some of the classes (libraries) for connecting with DB provided by mssql-jdbc driver
     * @gitURL https://github.com/Microsoft/mssql-jdbc
     */

    public static Connection Open(String connectionString) {

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

            return DriverManager.getConnection(connectionString);
        }catch (Exception e){

        }
        return null;
    }

    public static void Close() {

    }

    public static void ExecuteQuery(String query, Connection connection) {

        Statement statement = null;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

        }catch (Exception e){

        }
    }

    public static void ExecuteStoredProc(String procedureName, Hashtable parameters, Connection connection) {

        try{
            int paramterLength = parameters.size();
            String paraAppender = null;
            StringBuffer bulider = new StringBuffer();
            /** Build the parameter list to be passed in the stored proc*/
            for(int i=0; i<parameters.size(); i++){
                bulider.append("?,");
            }//iterate all the parameters in the hashtable

            paraAppender = bulider.toString();
            paraAppender = paraAppender.substring(0,paraAppender.length() -1);

            CallableStatement stmt = connection.prepareCall("{Call "
                    + procedureName + "(" + paraAppender + ")}");

            /** Creates Enumeration for getting the keys for the parameters*/
            Enumeration params = parameters.keys();

            /**Iterate in all the Elements till there is no keys*/
            while (params.hasMoreElements()){

                /**Get the key from the parameters*/
                String paramaName = (String) params.nextElement();

                /**Set parameters name and value*/
                stmt.setString(paramaName, parameters.get(paramaName).toString());
            }

            /**Execute Query*/
            stmt.execute();
        }catch (Exception e){

        }
    }
}