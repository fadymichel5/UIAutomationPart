package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {
    @FindBy(xpath = "//button[text()='Customer Login']")
    WebElement customerLoginButton;
    @FindBy(xpath = "//button[text()='Bank Manager Login']")
    WebElement bankManagerLoginButton;
    @FindBy(id = "userSelect")
    WebElement nameSelect;
    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginButton;
    @FindBy(xpath = "//div/button[contains(text(),'Deposit')]")
    WebElement depositButton;
    @FindBy(xpath = "//label[contains(text(),'Deposit')]//following-sibling::input")
    WebElement depositInput;
    @FindBy(xpath = "//form/button[contains(text(),'Deposit')]")
    WebElement depositConfirmButton;
    @FindBy(xpath = "//div/button[contains(text(),'With')]")
    WebElement withdrawalButton;
    @FindBy(xpath = "//label[contains(text(),'With')]//following-sibling::input")
    WebElement withdrawalInput;
    @FindBy(xpath = "//form/button[contains(text(),'With')]")
    WebElement withdrawalConfirmButton;
    @FindBy(xpath = "//div/button[contains(text(),'Trans')]")
    WebElement transactionButton;
    @FindBy(xpath = "//div[@ng-hide='noAccount']/strong[2]")
    WebElement balance;
    @FindBy(xpath = "//tbody/tr[2]/td[3]")
    WebElement secondTransactionType;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickCustomerLoginButton() {
        clickOnElement(customerLoginButton);
    }

    public void clickLogin() {
        clickOnElement(loginButton);
    }

    public void makeWithdrawal(int money) {
        clickOnElement(withdrawalButton);
        typeInputInTextBox(withdrawalInput, String.valueOf(money));
        clickOnElement(withdrawalConfirmButton);
    }

    public void makeDeposit(int money) {
        clickOnElement(depositButton);
        typeInputInTextBox(depositInput, String.valueOf(money));
        clickOnElement(depositConfirmButton);
    }

    public void chooseName(String name) {
        selectOption(nameSelect, name);
    }

    public void clickTransactions() {
        clickOnElement(transactionButton);
    }

    public String getBalance() {
        return getText(balance);
    }

    public String getTransactionType() {
        return getText(secondTransactionType);
    }

}
