package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum();
        int totalCells = sheet.getRow(0).getLastCellNum();

        System.out.println("Rows: " + totalRows);
        System.out.println("Cells: " + totalCells);

        Object[][] data = new Object[totalRows][totalCells];
        DataFormatter formatter = new DataFormatter();

        for (int r = 1; r <= totalRows; r++) {
            Row currentRow = sheet.getRow(r);

            for (int c = 0; c < totalCells; c++) {
                Cell currentCell = currentRow.getCell(c);
                String value = formatter.formatCellValue(currentCell);
                data[r - 1][c] = value;

                System.out.print(value + "\t");
            }
            System.out.println();
        }

        workbook.close();
        return data;
    }
}
