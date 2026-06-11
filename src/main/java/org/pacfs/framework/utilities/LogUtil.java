package org.pacfs.framework.utilities;

import org.pacfs.framework.config.Settings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ibi on 08/05/2026.
 */

public class LogUtil {

    /**
     * @contents- we are going to create our own logger mechanism
     * File format for the log name - i.e. random file name
     * Create the log file
     * Write some message within the log file
     */

    private BufferedWriter bufferedWriter = null; //Global variable for BufferedWriter class

    //ToDo: File format for the log name
    ZonedDateTime date = ZonedDateTime.now(); //Using the Java 1.8 feature in VOD.iml otherwise, the api will not be compatible
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHMMSS");
    String fileNameFormat = date.format(formatter);


    /**
     * @throws IOException
     * @Method for creating log file
     * we create a custom directory and specifying the location
     */
    public void CreateLogFile() throws IOException {
        try {
            File dir = new File(System.getProperty("user.dir") + Settings.LogPath);
            if (!dir.exists())   /*if directory does not exist */
                dir.mkdir();    /*then create one */

            /**
             * Create a random log file
             */
            File logFile = new File(dir + "/" + fileNameFormat + ".log");/*Formatter set above*/

            /**
             * The file write method
             */
            FileWriter fileWritter = new FileWriter(logFile.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWritter);
        } catch (Exception e) {

        }
    }

    public void Write(String message){
        try{
            formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH_MM_SS");
            String dateFormat = date.format(formatter);
            bufferedWriter.write("[" + dateFormat +"]" + message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception e){

        }
    }

}
