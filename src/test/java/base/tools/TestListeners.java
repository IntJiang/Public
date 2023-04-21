package base.tools;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener{
    public void onTestStart(ITestResult result) {
        Log.info(result.getMethod().getMethodName() + " Started");
        Log.info(result.getMethod().getDescription());
    }
    public void onTestSuccess(ITestResult result) {
        Log.info(result.getMethod().getMethodName() + " Passed");
    }
    public void onTestFailure(ITestResult result) {
        Log.info("Failed because of - "+ result.getThrowable());
    }
    public void onTestSkipped(ITestResult result) {
        Log.info("Skipped because of - "+ result.getThrowable());
    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }
    public void onStart(ITestContext context) {
        Log.info("=========== onStart :-" + context.getName() + "===============");

    }
    public void onFinish(ITestContext context) {
        Log.info("=========== onFinish :-" + context.getName() + "===============");

    }
}