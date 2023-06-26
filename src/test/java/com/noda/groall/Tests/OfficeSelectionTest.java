package com.noda.groall.Tests;

import com.noda.groall.RunningDrivers;
import com.noda.groall.TestListener;
import com.noda.groall.Pages.OfficeSelectionPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Test Class of office selection additional page: https://groall.noda.pro/test_qa
 */

@ExtendWith(TestListener.class)
public class OfficeSelectionTest extends RunningDrivers {

    private final String officeSelectionPageUrl = "https://groall.noda.pro/test_qa";

    public String[] citiesArray = {"Москва", "Власиха", "Таганрог"};

    public String[] streetsArray = {"ул.", "улица", "пр.", "проспект", "проезд", "пер.", "переулок", "ш.", "шоссе"};

    public List<String> streetsList = Arrays.asList(streetsArray);

    public List<String> citiesList = Arrays.asList(citiesArray);

    /**
     * Test checks if address format is correct. Address should be according to format "CITY_NAME, street STREET_NAME".
     */

    @Test
    @Description("Test-Case 1. Offices address checking")
    @Severity(SeverityLevel.NORMAL)
    @Disabled
    public void checkAddressFormatAtOfficeSelectionPageTest(){
        driver.get(officeSelectionPageUrl);
        OfficeSelectionPage officeSelectionPage = new OfficeSelectionPage();
        List<String> officeLst = officeSelectionPage.officeSelectionCompareWithFormat();
        boolean isCityCorrect = officeLst.stream()
                .allMatch(officeElem -> citiesList.contains(officeElem));
        boolean isStreetCorrect = officeLst.stream()
                .allMatch(officeElem -> streetsList.contains(officeElem));
        assertTrue((isCityCorrect && isStreetCorrect));
    }
}