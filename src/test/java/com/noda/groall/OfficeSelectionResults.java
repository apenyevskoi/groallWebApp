package com.noda.groall;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OfficeSelectionResults {
    public String[] officesCities = {"Москва", "Власиха", "Таганрог"};
    public String[] streetsT = {"ул.", "улица", "пр.", "проспект", "проезд", "пер.", "переулок", "ш.", "шоссе"};
    public List<String> streetsTypes = Arrays.asList(streetsT);
    public List<String> officesInCities = Arrays.asList(officesCities);
    @FindBy(css = "a.franchiseeListLink")
    private List<WebElement> officeSelection;
    
    public OfficeSelectionResults officeSelection(){
        try {
            List<String> offices = new ArrayList<>();
            for (int i = 0; i < officeSelection.size() - 1; i++)
                offices.add(officeSelection.get(i).getText().split(" ")[0]);
            Assertions.assertLinesMatch(offices, officesInCities);
            for (int i = 0; i < officeSelection.size() - 1; i++) {
                boolean isStreet = false;
                for (int j = 0; j < streetsTypes.size(); j++) {
                    if (officeSelection.get(i).getText() == null)
                        Allure.step("Wrong address. Null. " + officeSelection.get(i).getText(), Status.FAILED);
                    Assertions.assertNotNull(officeSelection.get(i));
                    if (officeSelection.get(i).getText().contains(streetsTypes.get(j)))
                        isStreet = true;
                }
                if (isStreet == false)
                    Allure.step("Wrong address " + officeSelection.get(i).getText(), Status.FAILED);
                Assertions.assertTrue(isStreet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return this;
    }
}
