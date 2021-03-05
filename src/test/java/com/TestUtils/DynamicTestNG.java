package com.TestUtils;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTestNG {

    static String xmlFileName = "testsToRun.xml";

    public void runTestNGTest(Map<String, String> testngParams) throws SQLException, ClassNotFoundException, IOException {   //Create an instance on TestNG
        TestNG myTestNG = new TestNG();

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite mySuite = new XmlSuite();
        mySuite.setName("eBay Test Suite");
        mySuite.setParallel(XmlSuite.ParallelMode.METHODS);

        //Create an instance of XmlTest and assign a name for it.
        XmlTest myTest = new XmlTest(mySuite);
        myTest.setName("MyTest");

        //Add any parameters that you want to set to the Test.
        myTest.setParameters(testngParams);

        //Create a list which can contain the classes that you want to run.
        ReadDB readDB = new ReadDB();
        List<XmlClass> myClasses = readDB.connect();

        //Assign that to the XmlTest Object created earlier.
        myTest.setXmlClasses(myClasses);

        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> myTests = new ArrayList<XmlTest>();
        myTests.add(myTest);

        //add the list of tests to your Suite.
        mySuite.setTests(myTests);

        //Add the suite to the list of suites.
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        mySuites.add(mySuite);

        //Set the list of Suites to the testNG object you created earlier.
        myTestNG.setXmlSuites(mySuites);
        mySuite.setFileName(xmlFileName);
        mySuite.setThreadCount(1);
//        myTestNG.run();

        //Create physical XML file based on the virtual XML content
        for (XmlSuite suite : mySuites) {
            createXmlFile(suite);
        }
        System.out.println("File generated successfully.");

        //Print the parameter values
        Map<String, String> params = myTest.getAllParameters();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    //This method will create an Xml file based on the XmlSuite data
    public void createXmlFile(XmlSuite mSuite) {
        FileWriter writer;
        try {
            writer = new FileWriter(new File(xmlFileName));
            writer.write(mSuite.toXml());
            writer.flush();
            writer.close();
            System.out.println(new File(xmlFileName).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Main Method
    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
        DynamicTestNG dt = new DynamicTestNG();
        Map<String, String> testngParams = new HashMap<>();
        dt.runTestNGTest(testngParams);
    }

    public void runner() throws SQLException, ClassNotFoundException, IOException {
        DynamicTestNG dt = new DynamicTestNG();
        Map<String, String> testngParams = new HashMap<>();
        dt.runTestNGTest(testngParams);
    }
}