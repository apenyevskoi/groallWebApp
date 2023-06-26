package com.noda.groall.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.HashMap;
import java.util.Map;

import static com.noda.groall.RunningDrivers.driver;
import static com.noda.groall.RunningDrivers.wait;

/**
 * Page Class of "Withdrawal Amount Page"
 * https://groall.noda.pro/test_qa?FranchiseeId=250790
 */

public class WithdrawalAmountPage {
    @FindBy(css = "#sendTokenForm input:nth-child(2)")
    private WebElement inputTokenAmount;
    @FindBy(css = "#sendTokenForm input[type='button']")
    private WebElement inputBtn;
    @FindBy(css = "input#all")
    private WebElement checkBox;
    @FindBy(css = "label#value-error.error")
    private WebElement inputContextError;
    @FindBy(css = ".baseContent p")
    private WebElement balance;


    /**
     * Constructor of "Withdrawal Amount Page"
     */
    public WithdrawalAmountPage(){
        PageFactory.initElements(driver, this);
    }

    /**
     * Parse "balance form" webelement at "Withdrawal Amount Page"
     * @return int value of balance
     */
    public int parseTokenBalanceInToken(){
        double tokenMaxAmountDouble = Double.parseDouble(balance.getText().split(" ")[1]);
        return Integer.parseInt(
                Double.toString(Math.floor(tokenMaxAmountDouble)).split("\\.")[0]);
    }

    /**
     * Parse amount value at "Input form" at "Withdrawal Amount Page"
     * @return int value from input coins form
     */
    public int getTokenAmountAtInputForm(){
        // wait value in "Input form"
        try {
            wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.textToBePresentInElementValue(inputTokenAmount, ""));

            return Integer.parseInt(inputTokenAmount.getAttribute("value"));
        }catch(NumberFormatException e1){
            try{
                return Integer.parseInt(inputTokenAmount.getText());
            }catch (NumberFormatException e2){
                e2.printStackTrace();
            }
            e1.printStackTrace();
        }
        return Integer.parseInt(inputTokenAmount.getAttribute("value"));
    }

    /**
     * Send text to withrawal form at "Withdrawal Amount Page"
     */
    public void inputTokenAmount(String tokens) {
        inputTokenAmount.sendKeys(tokens);
    }

    /**
     * Click button "Вывести" at "Withdrawal Amount Page"
     */
    public void clickInputBtn() {
        inputBtn.click();
    }

    /**
     * Parse "Alert window" to get balance remainder
     * @return Map\<String, Integer>
     *             actual  as int value of actual balance at "Alert message"
     */
    public Map<String, Integer> getActualBalanceAtAlertMessage() {
        Map<String,Integer> valuesMap = new HashMap<>();
        // wait "Alert window"
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());

        // parse "Alert window"
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        // put "actual" value to Map
        int actual =  Integer.parseInt(alertMessage.
                    split(" ")[alertMessage.split(" ").length - 1]);
        valuesMap.put("actual", actual);

        return valuesMap;
    }

    /**
     * Parse "Alert message"
     * @return String of message
     */
    public String getMessageFromAlertWindow(){
        // wait "Alert window"
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());

        // get message from "Alert window"
        String actual = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        return actual;
    }

    /**
     * Click check-box at "Withdrawal Amount Page"
     */
    public void clickCheckBox(){
        checkBox.click();
    }

    /**
     * Get text from context message at "Withdrawal Amount Page" after button click and incorrect input values
     * @return String context message as result of incorrect data input
     */
    public String getInputContextError(){
        return inputContextError.getText();
    }

    /**
     * Accept "Alert window"
     */
    public void acceptAlertWindow(){
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());

        // accept "Alert window"
        driver.switchTo().alert().accept();
    }
}