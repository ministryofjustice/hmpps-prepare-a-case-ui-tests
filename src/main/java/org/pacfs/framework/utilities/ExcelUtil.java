package org.pacfs.framework.utilities;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ExcelUtil {
    /**
     * @Excel Helper
     * Act as library to read Data from Excel sheet, parse Data and store Data in-memory collections
     * We are using any external libraries (3rd party)
     * @JXL In-Memory-
     * @HashTable
     */

    static Sheet wrksheet;
    static Workbook wrkbook = null;
    static Hashtable dict = new Hashtable();

    /**
     * @create a constructor- to open a excel sheet
     * @param ExcelSheetPath
     * @throws BiffException
     * @throws IOException
     */
    public ExcelUtil(String ExcelSheetPath, String ExcelSheetName) throws BiffException, IOException {
        //initialise
        wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath)); /**Getting the Excel sheet and passing the excel sheet path*/
        //For Demo purpose the excel sheet path is hardcoded, but not recommended  :)
        wrksheet = wrkbook.getSheet(ExcelSheetName);//Sheet1

        //Call the Column Dictionary to store column Names
        ColumnDictionary();
    }

    /**
     * @Rowcount- number of rows for a particular excel sheet
     * Return the number of rows
     * @return
     */
    public static int Rowcount() {

        return wrksheet.getRows(); /**Telling us how many rows are available in the excel sheet*/
    }

    /**
     * @ReadCell-takes takes the column and row number and then get the exact cell data from the excel sheet
     * Returns the cell value by taking row and column values as argument
     * @param column
     * @param row
     * @return
     */
    private static String ReadCell(int column, int row) {

        return wrksheet.getCell(column, row).getContents();
    }

    public static String ReadCell(String columnName, int rowNumber){

        return ReadCell(GetCell(columnName), rowNumber);
    }

    /**
     * @ColumnDictionary - This is populating all the column values like the column Name into the collection
     * Create column dictionary to hold all the column
     */
    private static void ColumnDictionary() { /** This is the dictionary where we are going to use our in-memory collection*/
        //iterate through all the column in the excel sheet sheet and store the value in Hashtable
        for (int col = 0; col < wrksheet.getColumns(); col++) {
            dict.put(ReadCell(col, 0), col);/** Stored all the collection name into the collection and returns the column index*/
        }
    }

    /**
     * Read Column Names
     * @param colName
     * @return
     */
    private static int GetCell(String colName) {
        try {
            int value;
            value = ((Integer) dict.get(colName)).intValue();/** Read data from dictionary by paying column name*/
            return value;
        } catch (NullPointerException e) {
            return (0);
        }
    }
}
//Open Excel Sheet
//Read Cell Data
//Store in-memory collection
//Get Cell method