package org.pacfs.framework.utilities;

import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

public class CucumberUtil {

    private static List<DataCollection> _dataCollection = new ArrayList<>();

    /**
     * @param table
     * @return it will convert datatble into a dictionary
     * @Read data from feature file table
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public static List<DataCollection> convertDataTableToDict(DataTable table) {// calling data table into custom collection

        _dataCollection.clear();
        var data = table.asList();
        int rowNumber = 0;

        //navigate through all the column
        for (String col : data) {

            /**storing all the values for the particular column while get(colIndex) is the dynamic values
             * @new DataCollection(data.get ( 0).get(colIndex) is storing all the values
             * @col.get(colIndex), getting the column values
             * @rowNumber is the row number will created above*/
            _dataCollection.add(rowNumber, new DataCollection(data.get(2), data.get(3), rowNumber));
            rowNumber++;
        }
        return _dataCollection; //return all the date value that we use in storing all the values
    }

    /**
     * //ToDo: Passing the rowIndex to get the columnValue based on Row number
     * public static String GetCellValue(String columnName){
     * <p>
     * return _dataCollection.get(columnName).ColumnValue;
     * }
     */

    //ToDo: Passing the rowIndex to get the columnValue based on Row number - multiply row index
    public static String getCellValueWithRowIndex(String columnName, int rowNumber) {

        final String[] columnValue = {null};

        _dataCollection.forEach(x -> {
            if (x.columnName.equals(columnName) && x.rowNummber == rowNumber) {
                columnValue[0] = x.columnValue;
            }
        });
        return columnValue[0];
    }

    /**
     * @DataCollection
     */
    private static class DataCollection {
        private String columnName;
        private String columnValue;
        private int rowNummber;

        public DataCollection(String columnName, String columnValue, int rowNummber) {
            this.columnName = columnName;
            this.columnValue = columnValue;
            this.rowNummber = rowNummber;
        }
    }
}