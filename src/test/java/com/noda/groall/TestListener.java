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

public class TestListener implements TestWatcher {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(WebDriver.class);
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                    ((TakesScreenshot) RunningDrivers.driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Test Fail, Screenshot is done",
                    String.valueOf(RunningDrivers.driver.manage().logs().get(LogType.BROWSER).getAll()));
            WebDriverManager.chromedriver().quit();
            RunningDrivers.driver.close();
            Allure.step("2 Failure on " + context.getTestMethod());
        }catch (Exception e) {
            e.getClass().getName();
        }
    }
    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.step("Success on " + context.getRequiredTestMethod().getName());
        WebDriverManager.chromedriver().quit();
        RunningDrivers.driver.close();
    }
}