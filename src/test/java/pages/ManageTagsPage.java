package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ManageTagsPage {

    private ElementsCollection editTagButtons = $$(By.xpath("//i[@class='icon-plus icon-white']"));
    private ElementsCollection deleteTagButtons = $$(By.xpath("//i[@class='icon-trash icon-white']"));
    private ElementsCollection tagsNames = $$(By.xpath("//td[@class='tag ng-binding']"));


    @Step("Click Edit tag button")
    public EditTagPage clickEditTagButton() {
        editTagButtons.get(1).click();
        return new EditTagPage();
    }

    @Step("Get tag name")
    public String getTagName() {
        return tagsNames.get(1).getText();
    }

    @Step("Get number of tags")
    public int getNumberOfTags() {
        tagsNames.get(1).shouldBe(Condition.enabled, Duration.ofSeconds(5));
        return tagsNames.size();
    }

    @Step("Click Delete tag button")
    public ManageTagsPage clickDeleteTagButton() {
        deleteTagButtons.get(1).click();
        return this;
    }

    public ManageTagsPage acceptAlert() {
        switchTo().alert(Duration.ofSeconds(5)).accept();
        return this;
    }


}
