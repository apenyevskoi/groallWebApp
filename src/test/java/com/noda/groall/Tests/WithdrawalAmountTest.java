package com.noda.groall.Tests;

import com.noda.groall.RunningDrivers;
import com.noda.groall.TestListener;
import com.noda.groall.Pages.WithdrawalAmountPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

/**
 * Test Class
 * Amount withdrawing functionality testing at "Withdrawal Amount Page"
 */

@ExtendWith(TestListener.class)
public class WithdrawalAmountTest extends RunningDrivers {

    private final String withdrawalPageUrl = "https://groall.noda.pro/test_qa?FranchiseeId=250790";

    /**
     * Test withdrawing of 1 coin. Assert balance after withdrawing.
     */
    @Test
    @Description("Test-Case 2. Withdrawing 1 coin amount")
    @Severity(SeverityLevel.NORMAL)
    @Disabled
    public void inputWithdrawalAmountOfOneTokenAmountTest(){
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // withdrawal amount value (in coins)
        int withdrawalAmountCoins = 1;

        // expected value (in tokens)
        int expected = withdrawalAmountPage.parseTokenBalanceInToken() - (withdrawalAmountCoins * 100);

        // send value of withdrawal amount
        withdrawalAmountPage.inputTokenAmount(String.valueOf(withdrawalAmountCoins));

        // click button to withdraw amount
        withdrawalAmountPage.clickInputBtn();

        // get parsed actual value
        Map<String, Integer> valuesMap =
                withdrawalAmountPage.getActualBalanceAtAlertMessage();
        int actual = valuesMap.get("actual");

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " +  expected +
                ". Actual: " + actual);

        Assertions.assertEquals(expected,actual);
    }

    /**
     * Test click on check-box to trace appearance of max amount in the input form
     */
    @Test
    @Description("Test-Case 8. Click on check-box")
    @Severity(SeverityLevel.NORMAL)
    @Disabled
    public void checkClickOnCheckBoxTest(){
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // click Check-Box
        withdrawalAmountPage.clickCheckBox();

        // get actual amount value at "Input form" at "Withdrawal Amount Page"
        int actual = withdrawalAmountPage.getTokenAmountAtInputForm();

        // get expected amount value at "Balance 'p' form" at "Withdrawal Amount Page" (in coins)
        int expected = withdrawalAmountPage.parseTokenBalanceInToken() / 100;

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " + expected +
                ". Actual: " + actual);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Click check-box and withdrawal button to assert balance after withdrawing
     */
    @Test
    @Description("Test-Case 3. Withdrawing max amount using check-box")
    @Severity(SeverityLevel.NORMAL)
    @Disabled
    public void checkClickOnCheckBoxAndClickOnButtonTest(){
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // click Check-Box
        withdrawalAmountPage.clickCheckBox();

        // get withrawal amount after check-box click (in tokens)
        int withdrawalAmountTokens = withdrawalAmountPage.getTokenAmountAtInputForm();

        // expected value (in coins)
        int expected = (withdrawalAmountPage.parseTokenBalanceInToken()/100) - (withdrawalAmountTokens/100);

        // click button to withdraw amount
        withdrawalAmountPage.clickInputBtn();

        // get parsed actual value
        Map<String, Integer> valuesMap =
                withdrawalAmountPage.getActualBalanceAtAlertMessage();
        int actual = valuesMap.get("actual");

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " + expected +
                ". Actual: " +  actual);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Insert withdrawal amount more than balance and trace message
     */
    @Test
    @Description("Test-Case 4. Withdrawing amount more than balance")
    @Severity(SeverityLevel.NORMAL)
    @Disabled
    public void inputWithdrawalOfTokensMoreThanBalanceTest(){
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // withdrawal amount value (in coins)
        int withdrawalAmountCoins = 1300;

        // expected value
        String expected = "Введеное кол-во коинов не должно превышать баланс";

        // send value of withdrawal amount
        withdrawalAmountPage.inputTokenAmount(String.valueOf(withdrawalAmountCoins));

        // click button to withdraw amount
        withdrawalAmountPage.clickInputBtn();

        // get parsed actual value
        String actual = withdrawalAmountPage.getMessageFromAlertWindow();

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " + expected +
                ". Actual: " +  actual);

        Assertions.assertEquals(expected, actual);
    }

    /**
     * Test of negative and zero values of amount in input form
     * @param argument -500, 0
     */
    @ParameterizedTest
    @Description("Test-Case 5. Checking input of negative or zero value")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"-500", "0"})
    @Disabled
    public void checkNegativeOrZeroValueTest(String argument){
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // expected value
        String expected = "Введеное кол-во коинов должно быть больше 0";

        // send value of withdrawal amount
        withdrawalAmountPage.inputTokenAmount(argument);

        // click button to withdraw amount
        withdrawalAmountPage.clickInputBtn();

        // get parsed actual value
        String actual = withdrawalAmountPage.getMessageFromAlertWindow();

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " +  expected +
                ". Actual: " + actual);

        Assertions.assertEquals(expected,actual);
    }

    /**
     * Test of non-numeric symbols
     * @param argument nonnumeric, !@#, as!@
     */
    @ParameterizedTest
    @Description("Test-Case 6. Checking input of non-numeric symbols")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"nonnumeric","!@#","as!@",})
    @Disabled
    public void checkSpecialSymbolsTest(String argument) {
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // expected value (in tokens)
        String expected = "Поле должно содержать только цифры!";

        // send value of withdrawal amount
        withdrawalAmountPage.inputTokenAmount(argument);

        // click button to withdraw amount
        withdrawalAmountPage.clickInputBtn();

        // get parsed actual value
        String actual = withdrawalAmountPage.getInputContextError();

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " +  expected +
                ". Actual: " + actual);

        Assertions.assertEquals(expected,actual);
    }

    /**
     * Test of balance state after amount withdrawing at "Balance form" at "Withdrawal Amount Page"
     */
    @Test
    @Description("Test-Case 7. Checking balance after amount withdrawing at 'Balance p form'")
    @Severity(SeverityLevel.CRITICAL)
//    @Disabled
    public void checkBalanceAfterWithdrawingTest(){
        driver.get(withdrawalPageUrl);
        WithdrawalAmountPage withdrawalAmountPage = new WithdrawalAmountPage();

        // withdrawal amount value (in coins)
        int withdrawalAmountCoins = 100;
        int initialTokenBalance = withdrawalAmountPage.parseTokenBalanceInToken();

        // expected value (in tokens)
        int expected = initialTokenBalance - (withdrawalAmountCoins * 100);

        // send value of withdrawal amount
        withdrawalAmountPage.inputTokenAmount(String.valueOf(withdrawalAmountCoins));

        // click button to withdraw amount
        withdrawalAmountPage.clickInputBtn();

        // accept "Alert window"
        withdrawalAmountPage.acceptAlertWindow();

        // get actual balance at "Withdrawal Amount Page"
        int actual = withdrawalAmountPage.parseTokenBalanceInToken();

        // add attachment to allure report
        Allure.addAttachment("Result", "Expected: " +  expected +
                ". Actual: " + actual +
                ". Initial balance: " + initialTokenBalance +
                ". Withdrawal coins amount: " + withdrawalAmountCoins);

        Assertions.assertEquals(expected,actual);
    }
}
