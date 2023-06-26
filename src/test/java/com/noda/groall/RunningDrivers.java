package com.noda.groall;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class provides WebDriver running, browser options setting, custom report register
 */

public class RunningDrivers {

    public static EventFiringWebDriver driver;

    public static ChromeOptions chromeOptions;

    public static WebDriverWait wait;

    /**
     * setting up Chrome WebDriver and Options. Register logger reporter
     */

    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        driver.register(new CustomReports());
    }

    /**
     * Close WebDriver
     */

    public static void tearDown(){
        driver.close();
    }

    /**
     * Running init() before each test
     */

    @BeforeEach
    public void init(){
        setUp();
    }
}
