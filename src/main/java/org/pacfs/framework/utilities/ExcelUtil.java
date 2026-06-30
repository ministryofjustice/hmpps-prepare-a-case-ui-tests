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
     * @param excelSheetPath
     * @throws BiffException
     * @throws IOException
     * @create a constructor- to open a excel sheet
     */
    public ExcelUtil(String excelSheetPath, String excelSheetName) throws BiffException, IOException {
        //initialise
        wrkbook = Workbook.getWorkbook(new File(excelSheetPath)); /**Getting the Excel sheet and passing the excel sheet path*/
        //For Demo purpose the excel sheet path is hardcoded, but not recommended  :)
        wrksheet = wrkbook.getSheet(excelSheetName);//Sheet1

        //Call the Column Dictionary to store column Names
        columnDictionary();
    }

    /**
     * @return
     * @Rowcount- number of rows for a particular excel sheet
     * Return the number of rows
     */
    public static int rowcount() {

        return wrksheet.getRows(); /**Telling us how many rows are available in the excel sheet*/
    }

    /**
     * @param column
     * @param row
     * @return
     * @ReadCell-takes takes the column and row number and then get the exact cell data from the excel sheet
     * Returns the cell value by taking row and column values as argument
     */
    private static String readCell(int column, int row) {

        return wrksheet.getCell(column, row).getContents();
    }

    public static String readCell(String columnName, int rowNumber) {

        return readCell(getCell(columnName), rowNumber);
    }

    /**
     * @ColumnDictionary - This is populating all the column values like the column Name into the collection
     * Create column dictionary to hold all the column
     */
    private static void columnDictionary() { /** This is the dictionary where we are going to use our in-memory collection*/
        //iterate through all the column in the excel sheet sheet and store the value in Hashtable
        for (int col = 0; col < wrksheet.getColumns(); col++) {
            dict.put(readCell(col, 0), col);/** Stored all the collection name into the collection and returns the column index*/
        }
    }

    /**
     * Read Column Names
     *
     * @param colName
     * @return
     */
    private static int getCell(String colName) {
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