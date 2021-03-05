package com;

import com.TestUtils.DynamicTestNG;
import org.testng.TestNG;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {


        DynamicTestNG dynamicTestNG = new DynamicTestNG();
        dynamicTestNG.runner();

        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add("testsToRun.xml");
//        suites.add("");
        testng.setTestSuites(suites);
        testng.run();

    }
}