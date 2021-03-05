package com.TestUtils;

import org.testng.xml.XmlClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class ReadDB {

    int testScenarioId; // Redmine scenario (issue) number taken from the Redmine DB
    int issueNumber; // Redmine scenario (issue) number taken from the HashMap
    int chbxId;  // Redmine checkbox number taken from the Redmine DB
    String title; // Redmine scenario (issue) name taken from the Redmine DB

    Properties properties = new Properties();
    FileInputStream propertiesFile = new FileInputStream("variables.properties");

    public ReadDB() throws FileNotFoundException {
    }

    public boolean isClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


    public List<XmlClass> connect() throws SQLException, ClassNotFoundException, IOException {
        properties.load(propertiesFile);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(properties.getProperty("LOCAL_DB_URL"), properties.getProperty("LOCAL_DB_USER"), properties.getProperty("LOCAL_DB_PASSWORD"));
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(properties.getProperty("LOCAL_TS_VIEW"));

        List<XmlClass> myClasses = new ArrayList();
        int campainNumber = Integer.parseInt(properties.getProperty("CAMPAIN_ISSUE_NUMBER"));
        while (rs.next()) {
            title = rs.getString("TS_NAME").replaceAll("\\. ", "_").replaceAll("- ", "_").replaceAll("\\W", "_");
            System.out.println(title);

            if (isClass("com.TS_" + title + "Test") && campainNumber == rs.getInt("CAMPAIGN_ID")) {
                myClasses.add(new XmlClass("com.TS_" + title + "Test"));
            } else if (isClass("com.ebay.TS_" + title + "Test") && campainNumber == rs.getInt("CAMPAIGN_ID")) {
                myClasses.add(new XmlClass("com.ebay.TS_" + title + "Test"));
            }
        }
        System.out.println("Number of tests tu run: " + myClasses.size());
        return myClasses;
    }

    public int getIssueNumber(String clazzName) throws IOException, ClassNotFoundException, SQLException {
        properties.load(propertiesFile);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(properties.getProperty("LOCAL_DB_URL"), properties.getProperty("LOCAL_DB_USER"), properties.getProperty("LOCAL_DB_PASSWORD"));
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(properties.getProperty("LOCAL_TS_VIEW"));

        Map<String, Integer> classesAndIssuesNumbers = new HashMap<>();

        while (rs.next()) {
            testScenarioId = rs.getInt("TS_ID");
            title = rs.getString("TS_NAME").replaceAll("\\. ", "_").replaceAll("- ", "_").replaceAll("\\W", "_");

            if (isClass("com.TS_" + title + "Test")) {
                classesAndIssuesNumbers.put(new String("com.TS_" + title + "Test"), testScenarioId);
            } else if (isClass("com.ebay.TS_" + title + "Test")) {
                classesAndIssuesNumbers.put(new String("com.ebay.TS_" + title + "Test"), testScenarioId);
            }
        }

        for (Map.Entry<String, Integer> testclassName : classesAndIssuesNumbers.entrySet()) {
            if (testclassName.getKey().equals(clazzName)) {
                issueNumber = testclassName.getValue();
            }
        }
        return issueNumber;
    }

    public int getChbxId(String tcName, int issue) throws IOException, SQLException {
        properties.load(propertiesFile);
        Connection con = DriverManager.getConnection(properties.getProperty("LOCAL_DB_URL"), properties.getProperty("LOCAL_DB_USER"), properties.getProperty("LOCAL_DB_PASSWORD"));
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(properties.getProperty("LOCAL_TC_VIEW"));

        char tcCharNumber = tcName.charAt(2); // gets the third character of TC name
        int tcNumber = Character.getNumericValue(tcCharNumber);

        while (rs.next()) {
            char tcCharFromDB = rs.getString("TC_NAME").charAt(2); //gets the third character of TC name from DB
            int tcIntFromDB = Character.getNumericValue(tcCharFromDB);

            /* get checkboxID from DB if:
            TS issue number in Redmine = TS isue nuber in Redmine taken before tests execution
            & TC number = TC number from Redmine DB */
            if (rs.getInt("TS_ID") == issue && tcNumber == tcIntFromDB) {
                chbxId = rs.getInt("TC_CHECKBOX_ID");
            }
        }
        return chbxId;
    }
}