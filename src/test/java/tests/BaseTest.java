package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utilits.TestListeners;

import java.awt.*;
import java.sql.DriverManager;

@Listeners(TestListeners.class)
public class BaseTest {

    public WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    @BeforeClass


    public void setUp() {
        Configuration.browser = "chrome";
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight() - 50;
        Configuration.browserSize = String.format("%dx%d", width, height);

    }



}

