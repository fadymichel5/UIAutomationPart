package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    public WebDriver driver;
    WebDriverWait wait;

    //Class constructor
    public PageBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    protected void clickOnElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    protected void typeInputInTextBox(WebElement webElement, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement webElement) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
        return element.getText();
    }

    protected void selectOption(WebElement webElement, String option) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Select select = new Select(webElement);
        select.selectByVisibleText(option);
    }

    protected void hoverElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    protected void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
