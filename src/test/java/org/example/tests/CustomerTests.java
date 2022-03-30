package org.example.tests;

import org.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerTests extends TestBase {
    HomePage homePage;

    @Test
    public void testScenario1() {
        homePage = new HomePage(driver);
        homePage.clickCustomerLoginButton();
        homePage.chooseName("Albus Dumbledore");
        homePage.clickLogin();
        homePage.makeDeposit(1000);
        homePage.makeWithdrawal(400);
        Assert.assertEquals(homePage.getBalance(), "600");
        homePage.clickTransactions();
        Assert.assertEquals(homePage.getTransactionType(), "Debit");
    }

}
