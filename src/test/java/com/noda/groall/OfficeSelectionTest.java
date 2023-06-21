package com.noda.groall;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.openqa.selenium.support.PageFactory;

@ExtendWith(TestListener.class)
public class OfficeSelectionTest extends RunningDrivers{
    @Test
    @Description
    @Severity(SeverityLevel.NORMAL)
//    @Disabled
    public void checkOfficeAddressAtOfficeSelectionPageTest(){
        driver.get("https://groall.noda.pro/test_qa");
        OfficeSelectionPage officeSelectionPage =
                PageFactory.initElements(driver, OfficeSelectionPage.class);
        OfficeSelectionResults officeSelectionResults =
                PageFactory.initElements(driver, OfficeSelectionResults.class);

        officeSelectionPage.setOfficeSelection();
        officeSelectionResults.officeSelection();
    }
    @Test
    @Description
    @Severity(SeverityLevel.NORMAL)
//    @Disabled
    public void checkInputTokens1AmountTest(){
        driver.get("https://groall.noda.pro/test_qa?FranchiseeId=250790");
        InputTokenWithdrawalPage inputTokenWithdrawalPage =
                PageFactory.initElements(driver, InputTokenWithdrawalPage.class);
        InputTokenWithdrawalResults inputTokenWithdrawalResults =
                PageFactory.initElements(driver, InputTokenWithdrawalResults.class);
        inputTokenWithdrawalPage.inputNAmount(1);
        inputTokenWithdrawalResults.checkTokenAmountResult();
    }
    @Test
    @Description
    @Severity(SeverityLevel.NORMAL)
//    @Disabled
    public void checkCheckBoxTest(){
        driver.get("https://groall.noda.pro/test_qa?FranchiseeId=250790");
        InputTokenWithdrawalPage inputTokenWithdrawalPage =
                PageFactory.initElements(driver, InputTokenWithdrawalPage.class);
        InputTokenWithdrawalResults inputTokenWithdrawalResults =
                PageFactory.initElements(driver, InputTokenWithdrawalResults.class);
        inputTokenWithdrawalPage.checkCheckBoxMaxTokenAmount();
        inputTokenWithdrawalResults.checkCheckBox();
    }
    @Test
    @Description
    @Severity(SeverityLevel.NORMAL)
//    @Disabled
    public void checkInputTokensAmountMoreThanBalanceTest(){
        driver.get("https://groall.noda.pro/test_qa?FranchiseeId=250790");
        InputTokenWithdrawalPage inputTokenWithdrawalPage =
                PageFactory.initElements(driver, InputTokenWithdrawalPage.class);
        InputTokenWithdrawalResults inputTokenWithdrawalResults =
                PageFactory.initElements(driver, InputTokenWithdrawalResults.class);
        inputTokenWithdrawalPage.inputAmountMoreThanBalance(1230);
        inputTokenWithdrawalResults.checkTokenAmountMoreThanBalance(1230);
    }
    @ParameterizedTest
    @Description
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"-500", "0"})
//    @Disabled
    public void checkTokenInputNegativeOrZeroValueTest(String argument){
        driver.get("https://groall.noda.pro/test_qa?FranchiseeId=250790");
        InputTokenWithdrawalPage inputTokenWithdrawalPage =
                PageFactory.initElements(driver, InputTokenWithdrawalPage.class);
        InputTokenWithdrawalResults inputTokenWithdrawalResults =
                PageFactory.initElements(driver, InputTokenWithdrawalResults.class);
        inputTokenWithdrawalPage.checkInputNegativeOrZeroValue(argument);
        inputTokenWithdrawalResults.checkTokenInputNegativeOrZeroValue();
    }
    @ParameterizedTest
    @Description
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"asdf","!@#$","as!@",})
//    @Disabled
    public void checkTokenInputSpecialSymbolsTest(String argument) {
        driver.get("https://groall.noda.pro/test_qa?FranchiseeId=250790");
        InputTokenWithdrawalPage inputTokenWithdrawalPage =
                PageFactory.initElements(driver, InputTokenWithdrawalPage.class);
        InputTokenWithdrawalResults inputTokenWithdrawalResults =
                PageFactory.initElements(driver, InputTokenWithdrawalResults.class);
        inputTokenWithdrawalPage.checkInputSpecialSymbols(argument);
        inputTokenWithdrawalResults.checkTokenInputSpecialSymbols();
    }
}
