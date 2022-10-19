package pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;

@Log4j
public class BlogPage {

    @Step("Switching to the new opened tab")
    public BlogPage switchToNewTab() {
        ArrayList<String> newTab = new ArrayList<>(WebDriverRunner.getAndCheckWebDriver().getWindowHandles());
        WebDriverRunner.driver().switchTo().window(newTab.get(1));
        log.info("User switches to the new opened tab");
        return this;
    }

    @Step("Check if Blog page is opened")
    public String isBlogOpened() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();

    }
}
