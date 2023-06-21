package com.noda.groall;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static com.noda.groall.RunningDrivers.driver;
import static com.noda.groall.RunningDrivers.wait;

public class InputTokenWithdrawalPage {
    @FindBy(css = "#sendTokenForm input:nth-child(2)")
    private WebElement inputTokenAmount;
    @FindBy(css = "#sendTokenForm input[type='button']")
    private WebElement inputBtn;
    @FindBy(css = "input#all")
    private WebElement checkBox;
    @FindBy(css = "label#value-error.error")
    private WebElement inputContextError;
    public InputTokenWithdrawalResults inputNAmount(int tokens){
        inputTokenAmount.sendKeys(String.valueOf(tokens));
        inputBtn.click();
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        return new InputTokenWithdrawalResults();
    }
    public InputTokenWithdrawalResults inputAmountMoreThanBalance(int value){
        inputTokenAmount.sendKeys(String.valueOf(value));
        return new InputTokenWithdrawalResults();
    }
    public InputTokenWithdrawalResults checkCheckBoxMaxTokenAmount(){
        try {
            if(!checkBox.isSelected()) {
                checkBox.click();
                Assertions.assertTrue(inputTokenAmount.getAttribute("value").length() > 0);
                inputBtn.click();
                wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.alertIsPresent());
            }
        }catch (Exception e){
        }
        return new InputTokenWithdrawalResults();
    }
    public InputTokenWithdrawalResults checkInputNegativeOrZeroValue(String value){
        inputTokenAmount.sendKeys(value);
        inputBtn.click();
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        return new InputTokenWithdrawalResults();
    }
    public InputTokenWithdrawalResults checkInputSpecialSymbols(String value){
        inputTokenAmount.sendKeys(value);
        inputBtn.click();
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label#value-error.error")));
        return new InputTokenWithdrawalResults();
    }
}