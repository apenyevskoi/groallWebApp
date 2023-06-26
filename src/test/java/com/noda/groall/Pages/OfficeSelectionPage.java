package com.noda.groall.Pages;

import static com.noda.groall.RunningDrivers.driver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OfficeSelectionPage {

    @FindBy(css = "a.franchiseeListLink")
    private List<WebElement> officeSelection;

    /**
     * Constructor of "Office Selection Page"
     *
     */
    public OfficeSelectionPage(){
        PageFactory.initElements(driver, this);
    }

    /**
     * method picks office address to List offices
     * @return list of offices
     */

    public List<String> officeSelectionCompareWithFormat(){
        return officeSelection.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
    }
}
