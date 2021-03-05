package com.ebay;

import com.TestUtils.Base;
import com.TestUtils.RetryAnalyzer;
import com.Utils.Utils;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@Log
public class TS_01_01_MainPageTest extends Base {

    final String tc0 = "TC0. Check all the mandatory items on the main page";
    final String tc1 = "TC1. Search for Depeche Mode in music category";

    public TS_01_01_MainPageTest() throws FileNotFoundException {
    }

    /* TESTS */

    @Test(description = tc0, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_checkAllItemsOnMainPage() throws IOException, SQLException {
        String tc = tc0;
        caseComment.append(tc + ":");
        MainPage mainPage = new MainPage(driver);
        caseComment.append(" check if elements are visible and clickable -->");
        Utils.checkElementList(driver, mainPage.elementList());
        caseComment.append(" DONE!");
        passedCases.add(readDB.getChbxId(tc, redmine_issue));

    }

    @Test(description = tc1, retryAnalyzer = RetryAnalyzer.class)
    public void tc02_searchForDepecheModeItems() throws InterruptedException, IOException, SQLException {
        String tc = tc1;
        caseComment.append(tc + ":");
        MainPage mainPage = new MainPage(driver);
        caseComment.append(" search for Depeche Mode band items -->");
        mainPage.searchInput.sendKeys("Depeche Mode");
        mainPage.category_Music.click();
        mainPage.searchButton.click();

        caseComment.append(" print first item and check that it is displayed on the website -->");
        WebElement item = driver.findElement(By.partialLinkText("Depeche Mode")); // first item
        log.info(item.getText());
        Assert.assertTrue(item.isDisplayed());
        caseComment.append(" DONE!");
        passedCases.add(readDB.getChbxId(tc, redmine_issue));
    }

}