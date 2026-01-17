package tests;

import utilities.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;

public class ExcelDataProvider {

    @DataProvider(name = "data")
    public Object[][] provideData() throws IOException {
        return ExcelUtils.getExcelData(System.getProperty("user.dir") + "/src/test/resources/data.xlsx", "sqat6");
    }
}