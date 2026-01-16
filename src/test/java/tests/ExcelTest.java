package tests;

import org.testng.annotations.Test;

public class LoginTest {

    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(String email, String password, String expectedResult) {

        System.out.println(email + " | " + password + " | " + expectedResult);

        // driver.findElement(...).sendKeys(email);
        // driver.findElement(...).sendKeys(password);
        // driver.findElement(...).click();

        // assert based on expectedResult
    }
}
