package tests;

import base.BaseTest;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class TableTest extends BaseTest {

    @Test(dataProvider = "data", dataProviderClass = tests.ExcelDataProvider.class)
    public void tableTest(String firstName, String lastName, String age) {

        var webTablesPage = homePage.goToElements().clickWebTables();

        String email = "cierra@example.com";
        webTablesPage.clickEditButton(email);

        webTablesPage.setFirstName(firstName);
        webTablesPage.setLastName(lastName);
        webTablesPage.setAge(age);
        webTablesPage.clickSubmitButton();

        String actualResult = webTablesPage.getTableFirstName(email);
        String expectedResult = firstName;


        Assert.assertEquals(actualResult, firstName);

    }
}
