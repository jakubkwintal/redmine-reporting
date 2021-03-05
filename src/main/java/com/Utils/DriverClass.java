package com.Utils;

import org.openqa.selenium.WebDriver;

public abstract class DriverClass {

    protected WebDriver driver;

    public DriverClass(WebDriver driver) {
        this.driver = driver;
    }
}