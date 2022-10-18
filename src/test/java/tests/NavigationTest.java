package tests;

import constants.Urls;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilits.RetryAnalyzer;

import static com.codeborne.selenide.Selenide.open;
@Log4j

public class NavigationTest extends BaseTest {

    @Test //(retryAnalyzer = RetryAnalyzer.class, priority = 2, description = "User navigates to Blog page")
    public void navigateToBlogTest() {
        LoginPage loginPage = new LoginPage();
        open(Urls.LOGIN_URL);
        log.info(String.format("Navigate to URL: %s", Urls.LOGIN_URL));
        String actualUrl = loginPage
                .navigateToBlog()
                .switchToNewTab()
                .isBlogOpened();
        log.info(String.format("Navigate to Blog page with URL: %s", actualUrl));
        Assert.assertEquals(actualUrl, Urls.BLOG_URL);

    }
}
