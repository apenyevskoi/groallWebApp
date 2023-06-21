package com.noda.groall;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunningDrivers {
    public static EventFiringWebDriver driver;
    public static ChromeOptions chromeOptions;
    public static WebDriverWait wait;

    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        driver.register(new CustomReports());
    }
    public static void tearDown(){
        driver.close();
    }
    @BeforeEach
    public void init(){
        setUp();
    }
}
