package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static base.BasePage.delay;
import static base.BasePage.driver;
import static utilities.Utility.setUtilityDriver;

public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;

    private static final String DEMOQA_URL = "https://demoqa.com/";

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setUp() throws Exception {

        String runMode = System.getProperty("runMode", "local");

        if (runMode.equalsIgnoreCase("cloud")) {

            String username = System.getenv("BROWSERSTACK_USERNAME");
            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

            if (username == null || accessKey == null) {
                throw new RuntimeException("BrowserStack credentials are missing!");
            }

            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", "Chrome");

            HashMap<String, Object> bstackOptions = new HashMap<>();
            bstackOptions.put("os", "Windows");
            bstackOptions.put("osVersion", "11");
            bstackOptions.put("browserVersion", "latest");
            bstackOptions.put("sessionName", "DemoQA Table Test");
            bstackOptions.put("buildName", "Assignment 6 - Cloud Run");

            capabilities.setCapability("bstack:options", bstackOptions);

            driver = new RemoteWebDriver(
                    new URL("https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub"),
                    capabilities
            );

        } else {
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadApp() {
        driver.get(DEMOQA_URL);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        homePage = new HomePage();
    }

    @AfterMethod
    public void reportTestStatus(ITestResult result) {
        if (driver instanceof RemoteWebDriver) {
            RemoteWebDriver remoteDriver = (RemoteWebDriver) driver;
            String status = (result.getStatus() == ITestResult.SUCCESS) ? "passed" : "failed";
            remoteDriver.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\""
                    + status + "\", \"reason\": \"" + result.getThrowable() + "\"}}");
        }
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            delay(2000);
            driver.quit();
        }
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
