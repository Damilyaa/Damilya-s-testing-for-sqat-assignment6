package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.logging.FileHandler.*;

import static base.BasePage.delay;
import static utilities.Utility.setUtilityDriver;
import static utilities.Utility.setUtilityDriver;


public class BaseTest {
    private static final Logger logger =
            LogManager.getLogger(BaseTest.class);

    private WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    private String DEMOQA_URL = "https://demoqa.com/";


    @BeforeClass
    public void setUp() {
        logger.info("Setting up WebDriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadApp() {
        logger.info("Opening DemoQA homepage");
        driver.get(DEMOQA_URL);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        homePage = new HomePage();
    }

//    @AfterMethod
//    public void takeFailedResultScreenshot(ITestResult testResult) {
//        TakesScreenshot screenshot = null;
//        if (ITestResult.FAILURE == testResult.getStatus()) {
//            screenshot = (TakesScreenshot) driver;
//        }
//        File source = screenshot.getScreenshotAs(OutputType.FILE);
//        File destination = new File(System.getProperty("user.dir")+ "/resources/screenshots/("+
//                java.time.LocalDate.now() +
//                testResult.getName() + ".png");
//        try{
//            FileHandler.copy(source, destination);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
        logger.info("Cleaning up test cookies");
    }




    @AfterClass
    public void tearDown() {
        logger.info("Closing browser");
        delay(3000);
        driver.quit();
    }

    public String takeScreenshot(String testName) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String path = "screenshots/" + testName + "_" + timestamp + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }




}
