package com.noda.groall;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Descriptions of actions during "Failed" and "Success" tests
 */

public class TestListener implements TestWatcher {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(WebDriver.class);

    /**
     * Description of actions during "Failed". Provides report and screenshot to allure
     * @param context
     * @param cause
     */

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                    ((TakesScreenshot) RunningDrivers.driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Test Fail, Screenshot is done",
                    String.valueOf(RunningDrivers.driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
            RunningDrivers.driver.close();
            Allure.step("Failure on " + context.getTestMethod());
        }catch (Exception e) {
            e.getClass().getName();
        }
    }

    /**
     * Description of actions during "Success". Provides allure report of successful test
     * @param context
     */

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.step("Success on " + context.getRequiredTestMethod().getName());
        WebDriverManager.chromedriver().quit();
        RunningDrivers.driver.close();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        int a = 0;
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause){
    }
}
