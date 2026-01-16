package tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingData {

    public static void main(String[] args) throws IOException {
        new WritingData();
    }

    public WritingData() throws IOException {
        FileOutputStream file = new FileOutputStream(
                System.getProperty("user.dir") + "/src/test/resources/data-1.xlsx"
        );

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sqat6-1");

        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("University");
        row1.createCell(1).setCellValue("Educational Program");
        row1.createCell(2).setCellValue("Course");

        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Astana IT Univeristy");
        row2.createCell(1).setCellValue("Software Engineering");
        row2.createCell(2).setCellValue("3");

        XSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("Astana IT Univeristy");
        row3.createCell(1).setCellValue("Computer Science");
        row3.createCell(2).setCellValue("3");

        workbook.write(file);

        workbook.close();
        file.close();
    }
}
