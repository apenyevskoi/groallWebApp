package com.noda.groall;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;
import java.util.List;

public class InputTokenWithdrawalResults extends RunningDrivers{
    @FindBy(css = ".baseContent p")
    private WebElement balance;
    @FindBy(css = "label#value-error.error")
    private WebElement inputContextError;
    private int parseTokenBalanceInCoin(){
        double tokenMaxAmountDouble = Double.parseDouble(balance.getText().split(" ")[1]) / 100;
        return Integer.parseInt(
                        Double.toString(Math.floor(tokenMaxAmountDouble)).split("\\.")[0]);
    }

    public InputTokenWithdrawalResults checkTokenAmountResult(){
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        int coinWithdrawAmount = Integer.parseInt(alertMessage.
                split(" ")[alertMessage.split(" ").length - 3].split(",")[0]);
        int tokenBalanceAmount = parseTokenBalanceInCoin();
        int expected = tokenBalanceAmount - (coinWithdrawAmount);
        //122000 - 100 = 121900
        int actual = Integer.parseInt(alertMessage.
                split(" ")[alertMessage.split(" ").length-1]);
        Allure.addAttachment("Result", "Expected: " + expected +
                ". Actual: " + actual +
                ". Balance: " + tokenBalanceAmount);
        Assertions.assertEquals(expected, actual);
        return this;
    }
    public InputTokenWithdrawalResults checkCheckBox(){
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        int tokenBalanceAmount = parseTokenBalanceInCoin();
        int withdrawaledAmount = Integer.parseInt(alertMessage.
                split(" ")[alertMessage.split(" ").length - 3].split(",")[0]);
        withdrawaledAmount *= 100;
        int expected = tokenBalanceAmount;
        int actual = withdrawaledAmount;
        Allure.addAttachment("Result", "Expected: " + expected +
                ". Actual: " + actual +
                ". Balance: " + tokenBalanceAmount);
        Assertions.assertEquals(expected, actual);
        return this;
    }
    public InputTokenWithdrawalResults checkTokenAmountMoreThanBalance(int coins){
        int tokenBalanceAmount = parseTokenBalanceInCoin();
        int expected = tokenBalanceAmount;
        int actual = coins * 100;
        Allure.addAttachment("Check if withdrawal amount more than balance",
                "balance: " + expected + ". " + "Withdrawal: " + actual);
        Assertions.assertTrue( expected > actual );
        return this;
    }
    public InputTokenWithdrawalResults checkTokenInputNegativeOrZeroValue(){
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        String expected = "Введеное кол-во коинов должно быть больше 0";
        Assertions.assertEquals(expected, alertMessage);
        return this;
    }
    public InputTokenWithdrawalResults checkTokenInputSpecialSymbols(){
        String expected = "Поле должно содержать только цифры!";
        String actual = inputContextError.getText();
        Assertions.assertEquals(expected, actual);
        return this;
    }
}
