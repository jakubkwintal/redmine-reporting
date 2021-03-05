package com.ebay;

import com.Utils.DriverClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends DriverClass {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//title[contains(text(),'Electronics, Cars, Fashion, Collectibles & More | ')]")
    WebElement pageTitle;

    @FindBy(linkText = "Sign in")
    WebElement singIn;

    @FindBy(id = "gh-p-1")
    WebElement dailyDeals;

    @FindBy(id = "gh-p-3")
    WebElement helpAndContact;

    @FindBy(id = "gh-p-2")
    WebElement sell;

    @FindBy(id = "gh-eb-My")
    WebElement myEbay;

    @FindBy(id = "gh-eb-Alerts")
    WebElement alerts;

    @FindBy(id = "gh-minicart-hover")
    WebElement cart;

    @FindBy(id = "gh-logo")
    WebElement logo;

    public WebElement[] elementList() {
        WebElement[] elements = {
                singIn,
                dailyDeals,
                helpAndContact,
                sell,
                myEbay,
                alerts,
                cart,
                logo
        };
        return elements;
    }

    @FindBy(id = "gh-shop-a")
    WebElement shopByCategory;

    @FindBy(linkText = "Music")
    WebElement shopByCategory_Music;

    @FindBy(id = "gh-ac")
    WebElement searchInput;

    @FindBy(id = "gh-cat")
    WebElement category;

    @FindBy(css = "option[value='11233']")
    WebElement category_Music;

    @FindBy(id = "gh-btn")
    WebElement searchButton;

}
