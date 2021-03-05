package com.TestUtils;

import com.Utils.DriverClass;
import com.Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Redmine extends DriverClass {

    public Redmine(WebDriver driver) throws FileNotFoundException {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    FileInputStream propertiesFile = new FileInputStream("variables.properties");

    public void goToIssue(Properties properties, WebDriver driver, int issue) throws IOException {
        properties.load(propertiesFile);
        driver.get(properties.getProperty("REDMINE_URL"));
        driver.manage().window().maximize();
        Utils.waitForElementToBeClickable(driver, logInToTestRedmine);
        logInToTestRedmine.click();
        username.sendKeys(properties.getProperty("REDMINE_USERNAME"));
        password.sendKeys(properties.getProperty("REDMINE_PASSWORD"));
        login.click();
        driver.get(properties.getProperty("REDMINE_URL") + "/issues/" + issue);
    }

    public void clickCheckbox(WebDriver driver, List<Integer> passedCases) {

        for (int chcbx : passedCases) {
            WebElement checkbox = driver.findElement(By.id("checklist-checkbox-" + chcbx));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void caseComment(Properties properties, StringBuilder comment, StringBuilder tcComment) throws IOException {
        FileInputStream propertiesFile = new FileInputStream("variables.properties");
        properties.load(propertiesFile);

        if (!tcComment.toString().contains("DONE!")) {
            comment.append(tcComment + "\n");
        }
    }

    public void leaveComment(Properties properties, StringBuilder comment) throws IOException {
        FileInputStream propertiesFile = new FileInputStream("variables.properties");
        properties.load(propertiesFile);
        comment.append("%*");
        notatki.click();
        notatki.sendKeys(comment);
    }

    public void testScenarioStatus(Properties properties, WebDriver driver, int issue, StringBuilder comment, List<Integer> passedCases) throws IOException, InterruptedException {
        Redmine redmine = new Redmine(driver);
        goToIssue(properties, driver, issue);
        clickCheckbox(driver, passedCases);
        driver.navigate().refresh();
        Thread.sleep(3000);
        editIssue.click();

        int selectedCheckboxes = 0;
        for (int i = 0; i < chbxList().size(); i++) {
            if (chbxList().get(i).isSelected()) {
                selectedCheckboxes++;
            }
        }

        tsStatusList.click();
        if (chbxList().size() == selectedCheckboxes) {
            Utils.waitForElementToBeClickable(driver, pozytywny);
            pozytywny.click();
        } else {
            Utils.waitForElementToBeClickable(driver, negatywny);
            negatywny.click();
            redmine.leaveComment(properties, comment);
        }

        Utils.waitForElementToBeClickable(driver, submit);
        submit.click();
    }

    public List<WebElement> chbxList() {

        List<WebElement> chbxes = new ArrayList<>();

        int chbxNumber = 1;
        String checklistStart = "//*[@id='checklist_items']/li[";
        String checklistEnd = "]/input";
        String checklist = checklistStart + chbxNumber + checklistEnd;

        do {
            chbxes.add(driver.findElement(By.xpath(checklist)));
            chbxNumber++;
            checklist = checklistStart + chbxNumber + checklistEnd;
        } while (driver.findElements(By.xpath(checklist)).size() > 0);

        return chbxes;
    }


    @FindBy(className = "login")
    WebElement logInToTestRedmine;

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-submit")
    WebElement login;

    @FindBy(className = "icon-edit")
    WebElement editIssue;

    @FindBy(id = "issue_status_id")
    WebElement tsStatusList;

    @FindBy(xpath = "//option[contains(text(),'Pozytywny')]")
    WebElement pozytywny;

    @FindBy(xpath = "//option[contains(text(),'Negatywny')]")
    WebElement negatywny;

    @FindBy(css = "input[value = 'Wyślij']")
    WebElement submit;

    @FindBy(id = "issue_notes")
    WebElement notatki;
}