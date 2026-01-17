package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentManager;

import static base.BasePage.driver;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getExtent();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
        if (driver instanceof RemoteWebDriver) {
            ((RemoteWebDriver) ((BaseTest) result.getInstance()).getDriver())
                    .executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\",\"reason\":\"Test passed\"}}");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        if (driver instanceof RemoteWebDriver) {
            ((RemoteWebDriver) ((BaseTest) result.getInstance()).getDriver())
                    .executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\",\"reason\":\"Test failed\"}}");
        }
    }


    private void attachScreenshot(ITestResult result) {
        String screenshotPath =
                ((BaseTest) result.getInstance())
                        .takeScreenshot(result.getMethod().getMethodName());

        String relativePath = "../" + screenshotPath.replace("\\", "/");

        test.get().addScreenCaptureFromPath(relativePath);
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}
