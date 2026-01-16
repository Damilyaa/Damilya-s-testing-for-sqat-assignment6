package tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingData {

    public static void main(String[] args) throws IOException {
        new ReadingData();
    }

    public ReadingData() throws IOException {
        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/data.xlsx"
        );

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("sqat6");

        int totalRows = sheet.getLastRowNum();
        int totalCells = sheet.getRow(2).getLastCellNum();

        System.out.println("Rows: " + totalRows);
        System.out.println("Cells: " + totalCells);

        for (int r = 0; r <= totalRows; r++) {
            XSSFRow currentRow = sheet.getRow(r);
            for (int c = 0; c < totalCells; c++) {
                XSSFCell currentCell = currentRow.getCell(c);
                System.out.print(currentCell.toString() + "\t");
            }
            System.out.println();
        }

        workbook.close();
        file.close();
    }
}
