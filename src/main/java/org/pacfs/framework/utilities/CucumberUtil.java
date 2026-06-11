package org.pacfs.framework.utilities;

import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

public class CucumberUtil {

    private static List<DataCollection> _dataCollection = new ArrayList<>();

    /**
     * @Read data from feature file table
     * @param table
     * @return
     * it will convert datatble into a dictionary
     */
    public static List<DataCollection> ConvertDataTableToDict(DataTable table){// calling data table into custom collection

        _dataCollection.clear();
        var data = table.asList();
        int rowNumber = 0;

        for(String col : data){//navigate through all the column

                /**storing all the values for the particular column while get(colIndex) is the dynamic values
                 * @new DataCollection(data.get(0).get(colIndex) is storing all the values
                 * @col.get(colIndex),  getting the column values
                 * @rowNumber is the row number will created above*/
                _dataCollection.add(rowNumber, new DataCollection(data.get(2), data.get(3), rowNumber));
            rowNumber ++;
        }
        return _dataCollection; //return all the date value that we use in storing all the values
    }

    /**
    //ToDo: Passing the rowIndex to get the columnValue based on Row number
    public static String GetCellValue(String columnName){

        return _dataCollection.get(columnName).ColumnValue;
    }*/

    //ToDo: Passing the rowIndex to get the columnValue based on Row number - multiply row index
    public static String GetCellValueWithRowIndex(String columnName, int rowNumber){

        final String[] columnValue = {null};

        _dataCollection.forEach(x -> {
            if(x.ColumnName.equals(columnName) && x.RowNummber == rowNumber){
                columnValue[0] = x.ColumnValue;
            }
        });
        return columnValue[0];
    }

    /**
     * @DataCollection
     */
    private static class DataCollection{
        private String ColumnName;
        private String ColumnValue;
        private int RowNummber;

        public DataCollection(String columnName, String columnValue, int rowNummber) {
            ColumnName = columnName;
            ColumnValue = columnValue;
            RowNummber = rowNummber;
        }
    }
}
