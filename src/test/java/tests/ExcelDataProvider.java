package tests;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.InputStream;

public class ExcelDataProvider {

    @tests.DataProvider(name = "excelData")
    public static Object[][] getData() throws Exception {

        InputStream file = ExcelDataProvider.class
                .getClassLoader()
                .getResourceAsStream("testdata.xlsx");

        if (file == null) {
            throw new RuntimeException("testdata.xlsx not found");
        }

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) { // skip header
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = row.getCell(j).toString();
            }
        }

        workbook.close();
        return data;
    }
}
