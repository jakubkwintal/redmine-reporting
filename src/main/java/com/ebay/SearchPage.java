package com.ebay;

import com.Utils.DriverClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends DriverClass {

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(id = "gh-ac")
    WebElement searchInput;

    @FindBy(xpath = "//span[contains(text(),'Buying Format')]")
    WebElement buyingFormat;

    @FindBy(xpath = "//h2[contains(text(),'All Listings')]")
    WebElement allListings;

    @FindBy(xpath = "//h2[contains(text(),'Accepts Offers')]")
    WebElement acceptOffers;

    @FindBy(xpath = "//h2[contains(text(),'Auction')]")
    WebElement auction;

    @FindBy(xpath = "//h2[contains(text(),'Buy It Now')]")
    WebElement buyItNow;

    @FindBy(xpath = "//span[contains(text(),'Condition')]")
    WebElement condition;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-53[1]-9-content-menu']/a[1]/span")
    WebElement anyCondition;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-53[1]-9-content-menu']/a[2]/span")
    WebElement brandNew;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-53[1]-9-content-menu']/a[3]/span")
    WebElement likeNew;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-53[1]-9-content-menu']/a[4]/span")
    WebElement veryGood;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-53[1]-9-content-menu']/a[5]/span")
    WebElement good;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-53[1]-9-content-menu']/a[6]/span")
    WebElement acceptable;

    @FindBy(xpath = "//span[contains(text(),'Best Match')]")
    WebElement sort;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-28-0-content-menu']/a[1]/span")
    WebElement bestMatch;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-28-0-content-menu']/a[2]/span")
    WebElement time_EndingSoonest;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-28-0-content-menu']/a[3]/span")
    WebElement time_NewlyListed;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-28-0-content-menu']/a[4]/span")
    WebElement priceShipping_lowestFirst;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-28-0-content-menu']/a[5]/span")
    WebElement priceShipping_highestFirst;

    @FindBy(xpath = "//div[@id='s0-14-11-5-1[0]-28-0-content-menu']/a[6]/span")
    WebElement distance_nearestFirst;

    @FindBy(xpath = "//div[@class='s-pagination']/div/span[2]/button")
    WebElement itemsPerPage;

    @FindBy(xpath = "//span[text()='25']")
    WebElement ipp25;

    @FindBy(xpath = "//span[text()='50']")
    WebElement ipp50;

    @FindBy(xpath = "//span[text()='100']")
    WebElement ipp100;

    @FindBy(xpath = "//span[text()='200']")
    WebElement ipp200;
}
