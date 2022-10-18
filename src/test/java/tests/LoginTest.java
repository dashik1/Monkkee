package tests;

import constants.Credentials;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilits.FakeMessageGenerator;
import utilits.RetryAnalyzer;
@Log4j
public class LoginTest extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Test //(retryAnalyzer = RetryAnalyzer.class, priority = 1, description = "User successfully logs into the system")
    public void successfulLoginTest() {
        boolean isCreateEntryButtonDisplayed = loginPage
                .login(Credentials.EMAIL, Credentials.PASSWORD)
                .isCreateEntryButtonDisplayed();
        log.info("User is logged in");
        Assert.assertTrue(isCreateEntryButtonDisplayed, "Login is not successful!");

    }

    @Test //(retryAnalyzer = RetryAnalyzer.class, priority = 2, description = "User can't log in with incorrect password")
    public void unsuccessfulLoginWrongPasswordTest() {
        boolean isErrorMessageDisplayed = loginPage
                .unsuccessfulLogin(Credentials.EMAIL, FakeMessageGenerator.generatePassword())
                .isErrorMessageDisplayed();
        log.info("User is not logged in");
        Assert.assertTrue(isErrorMessageDisplayed, "Error message is not displayed!");

    }
}
