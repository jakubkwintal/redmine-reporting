package com.TestUtils;

import lombok.SneakyThrows;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RetryAnalyzer implements IRetryAnalyzer { // allows to automatically repeat the test if it fails

    public Properties properties = new Properties();
    public FileInputStream propertiesFile = new FileInputStream("variables.properties");

    int counter = 0;

    public RetryAnalyzer() throws FileNotFoundException {
    }

    @SneakyThrows
    @Override
    public boolean retry(ITestResult result) {
        properties.load(propertiesFile);

        int retryLimit = Integer.parseInt(properties.getProperty("RETRY_LIMIT"));

        if (counter < retryLimit) {
            counter++;
            return true;
        }
        return false;
    }
}