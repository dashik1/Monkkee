package pages;

import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;

@Log4j
public class BlogPage {




    public BlogPage switchToNewTab() {
        ArrayList<String> newTab = new ArrayList<>(WebDriverRunner.getAndCheckWebDriver().getWindowHandles());
        WebDriverRunner.driver().switchTo().window(newTab.get(1));
        log.info("User switches to the new opened tab");
        return this;
    }

    public String isBlogOpened() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();

    }
}
