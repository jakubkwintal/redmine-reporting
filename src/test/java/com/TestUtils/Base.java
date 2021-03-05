package com.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Base {

    public int redmine_issue;
    public StringBuilder comment;
    public StringBuilder caseComment;
    protected WebDriver driver;

    public ReadDB readDB = new ReadDB();
    public List<Integer> passedCases = new ArrayList<>();

    public Properties properties = new Properties();
    public FileInputStream propertiesFile = new FileInputStream("variables.properties");


    public Base() throws FileNotFoundException {
    }

    public void browser() throws IOException {
        properties.load(propertiesFile);

        String browserName = properties.getProperty("BROWSER");
        if (browserName.equals("C")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equals("F")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equals("E")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equals("IE")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browserName.equals("H")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            driver = new ChromeDriver(options);
        }
    }


    @BeforeClass
    public void startComment() {
        comment = new StringBuilder("*%{color:#f99000}");
    }

    @BeforeMethod
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        String className = getClass().getName();
        redmine_issue = readDB.getIssueNumber(className); // gets testScenario number from Redmine DB based on name
        browser();
        properties.load(propertiesFile);
        driver.get(properties.getProperty("URL"));
        driver.manage().window().maximize();

        caseComment = new StringBuilder("");
    }

    @AfterMethod
    public void endTest() throws IOException {
        Redmine redmine = new Redmine(driver);
        redmine.caseComment(properties, comment, caseComment);
        driver.close();
    }

    @AfterClass
    public void tsStatusInRedmine() throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        String className = getClass().getName();
        redmine_issue = readDB.getIssueNumber(className);
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        properties.load(propertiesFile);
        Redmine redmine = new Redmine(driver);
        redmine.testScenarioStatus(properties, driver, redmine_issue, comment, passedCases);
        driver.close();
    }

}

