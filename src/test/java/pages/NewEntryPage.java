package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utilits.FakeMessageGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class NewEntryPage {

    private SelenideElement entryInput = $(By.id("editable"));
    private SelenideElement savedLabel = $(By.xpath("//span[text()='saved']"));
    private SelenideElement backButton = $(By.id("back-to-overview"));
    private SelenideElement textBox = $(By.xpath("//div[@role='textbox']"));
    private SelenideElement newTagInput = $(By.id("new-tag"));
    private SelenideElement assignNewTagButton = $(By.id("assign-new-tag"));
    private SelenideElement assignedTag = $(By.xpath("//a[@ng-click='untagEntry(assignedTag)']"));
    private SelenideElement entryText = $(By.xpath("//div[@id='editable']//p"));
    private SelenideElement deleteButton = $(By.id("delete-entry"));


    public NewEntryPage typeNewEntry() {
        textBox.shouldBe(Condition.enabled, Duration.ofSeconds(3));
        entryInput.sendKeys(FakeMessageGenerator.generateNewEntry());
        savedLabel.shouldBe(Condition.enabled, Duration.ofSeconds(6));
        return this;

    }

    public EntriesPage goBackToEntriesPage() {
        backButton.click();
        return new EntriesPage();
    }

    public NewEntryPage createNewTag() {
        newTagInput.sendKeys(FakeMessageGenerator.generateWord());
        assignNewTagButton.click();
        return this;
    }

    public boolean getAssignedTag() {
        assignedTag.shouldBe(Condition.enabled, Duration.ofSeconds(5));
        return assignedTag.isDisplayed();
    }

    public String getEntryText() {
        return entryText.getText();
    }

    public NewEntryPage clearEntry() {
        entryText.clear();
        return this;
    }


    public EntriesPage deleteEntry() {
        backButton.shouldBe(Condition.enabled, Duration.ofSeconds(5));
        deleteButton.click();
        switchTo().alert(Duration.ofSeconds(5)).accept();
        return new EntriesPage();
    }

}
