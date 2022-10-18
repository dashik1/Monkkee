package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class EntriesPage {


    private SelenideElement createEntryButton = $(By.id("create-entry"));

    private SelenideElement manageTagsButton = $(By.xpath("//a[text()='Manage tags']"));
    private SelenideElement searchInput = $(By.id("appendedInputButton"));
    private SelenideElement searchButton = $(By.xpath("//button[@type='submit']"));
    private SelenideElement searchResultLabel = $(By.xpath("//div[@class='search-explanation ng-binding']"));

    private ElementsCollection entries = $$(By.xpath("//div[@class='entry-container clearfix ng-scope']"));

    private ElementsCollection entriesTexts = $$(By.xpath("//div[@class='body ']"));

    public boolean isCreateEntryButtonDisplayed() {
        createEntryButton.shouldBe(Condition.enabled, Duration.ofSeconds(10));
        return createEntryButton.isEnabled();
    }

    @Step("Create new entry")
    public NewEntryPage clickCreateEntryButton() {
        createEntryButton.click();
        return new NewEntryPage();
    }



    public NewEntryPage navigateToEntry() {
        entriesTexts.get(1).click();
        return new NewEntryPage();
    }

    @Step("Get number of entries")
    public int getNumberOfEntries() {
        entries.get(1).shouldBe(Condition.enabled, Duration.ofSeconds(10));
        return entries.size();

    }


    public ManageTagsPage navigateToManageTags() {
        manageTagsButton.click();
        return new ManageTagsPage();
    }

    public EntriesPage search(String searchWord) {
        searchInput.sendKeys(searchWord);
        searchButton.click();
        searchResultLabel.shouldBe(Condition.enabled, Duration.ofSeconds(5));
        return this;
    }

    public boolean isSearchResultDisplayed() {
        return searchResultLabel.isDisplayed();
    }
}
