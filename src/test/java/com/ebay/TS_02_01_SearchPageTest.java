package com.ebay;

import com.TestUtils.Base;
import com.TestUtils.RetryAnalyzer;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log
public class TS_02_01_SearchPageTest extends Base {

    final String tc0 = "TC0. Search for U2's 'No line on the horizon' album with some details";

    public TS_02_01_SearchPageTest() throws FileNotFoundException {
    }

    /* TESTS */

    @Test(description = tc0, retryAnalyzer = RetryAnalyzer.class)
    public void tc01_searchForU2Album() throws InterruptedException, IOException, SQLException {
        String tc = tc0;
        caseComment.append(tc + ":");
        MainPage mainPage = new MainPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        caseComment.append(" search for U2's 'No line on the horizon' album with music category -->");
        mainPage.searchInput.sendKeys("U2 No line on the horizon");
        mainPage.category_Music.click();
        mainPage.searchButton.click();
        caseComment.append(" set the search details: Buy It Now, Condition: Brand New, Sort by: Distance - nearest first -->");
        searchPage.buyItNow.click();
        searchPage.condition.click();
        searchPage.brandNew.click();
        searchPage.sort.click();
        searchPage.distance_nearestFirst.click();

        caseComment.append(" scroll down and select 25 items per page -->");
        Actions act = new Actions(driver);
        act.moveToElement(searchPage.itemsPerPage);
        act.perform();
        searchPage.itemsPerPage.click();
        searchPage.ipp25.click();

        caseComment.append(" print the number and names of the displayed items -->");
        List<WebElement> items = driver.findElements(By.cssSelector("h3.s-item__title"));
        log.info("Number of displayed items: " + items.size());
        log.info("ITEMS:");
        int count = 1;
        for (WebElement el : items) {
            log.info(count++ + ": " + el.getText());
        }

        caseComment.append(" check that the number of displayed items equals 27 (eBay displays 2 items more that it declares) -->");
        Assert.assertEquals(items.size(), 27); // eBay displays 2 items more that it declares
        caseComment.append(" DONE!");
        passedCases.add(readDB.getChbxId(tc, redmine_issue));
    }

}