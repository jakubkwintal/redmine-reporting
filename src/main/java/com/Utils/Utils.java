package com.Utils;


import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log
public class Utils {

    static WebDriverWait wait;


    /* WAITERS */

    public static void waitForElementToBeInvisible(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /* ELEMENTS */

    public static void checkElementList(WebDriver driver, WebElement[] elements) {
        int i = 0;
        for (WebElement el : elements) {
            log.info(el.getText());
            Utils.waitForElementToBeVisible(driver, el);
            Utils.waitForElementToBeClickable(driver, el);
        }
    }

}