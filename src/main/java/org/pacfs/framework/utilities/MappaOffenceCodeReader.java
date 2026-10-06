package org.pacfs.framework.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MappaOffenceCodeReader {

    private static final String FILE_PATH =
            System.getProperty("user.dir")
                    + "/src/main/resources/testdata/final_MAPPA_CJS_offence_codes.xlsx";

    public static List<String> getOffenceCodes() {

        List<String> offenceCodes = new ArrayList<>();

        File file = new File(FILE_PATH);

        System.out.println("========================================");
        System.out.println("MAPPA OFFENCE CODE READER");
        System.out.println("Looking for file:");
        System.out.println(file.getAbsolutePath());
        System.out.println("File exists: " + file.exists());
        System.out.println("File readable: " + file.canRead());
        System.out.println("========================================");

        if (!file.exists()) {
            throw new RuntimeException(
                    "MAPPA offence code spreadsheet not found: "
                            + file.getAbsolutePath()
            );
        }

        try (FileInputStream inputStream =
                     new FileInputStream(file);
             Workbook workbook =
                     WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            System.out.println(
                    "Spreadsheet found successfully."
            );

            System.out.println(
                    "Sheet name: " + sheet.getSheetName()
            );

            System.out.println(
                    "Number of rows: " + sheet.getLastRowNum()
            );

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                Cell cell = row.getCell(0);

                if (cell == null) {
                    continue;
                }

                String offenceCode =
                        cell.toString().trim();

                if (!offenceCode.isEmpty()) {

                    offenceCodes.add(offenceCode);

                    System.out.println(
                            "MAPPA offence code: "
                                    + offenceCode
                    );
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read MAPPA offence codes from: "
                            + file.getAbsolutePath(),
                    e
            );
        }

        System.out.println(
                "Total MAPPA offence codes found: "
                        + offenceCodes.size()
        );

        return offenceCodes;
    }
}