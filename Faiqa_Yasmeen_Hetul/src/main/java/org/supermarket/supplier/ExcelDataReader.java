package org.supermarket.supplier;

import java.util.*;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataReader {






    public static List<Map<String, Object>> fetchExcelData() {
        List<Map<String, Object>>  excelData = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/items.xlsx"));
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            // Assuming you're reading data from the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows and columns to extract data
            Iterator<Row> rowIterator = sheet.iterator();
            boolean heading = true;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, Object> rowData = new HashMap<>();
                int columnIndex = 0;
                Iterator<Cell> cellIterator = row.cellIterator();

                if(heading){
                    heading = false;

                }else{

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        // Extract data and convert to different data types
                        if (cell.getCellType() == CellType.STRING) {
                            rowData.put("Column" + columnIndex, cell.getStringCellValue());
                            // Handle string data
                            //System.out.println("String Value: " + stringValue);
                        } else if (cell.getCellType() == CellType.NUMERIC) {
                            rowData.put("Column" + columnIndex, cell.getNumericCellValue());
                            //double numericValue = cell.getNumericCellValue();
                            // Handle numeric data
                            //System.out.println("Numeric Value: " + numericValue);
                        }
                        columnIndex++;
                    }


                    excelData.add(rowData);

                    // You can handle other data types as needed
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelData;
    }
}

