package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilits.FakeMessageGenerator;

import static com.codeborne.selenide.Selenide.$;

public class EditTagPage {

    private SelenideElement tagInput = $(By.id("tag"));
    private SelenideElement okButton = $(By.xpath("//button[@type='submit']"));

    @Step("Edit tag")
    public EditTagPage editTagName() {
        tagInput.clear();
        tagInput.sendKeys(FakeMessageGenerator.generateWord());
        return this;
    }

    public ManageTagsPage clickOkButton() {
        okButton.click();
        return new ManageTagsPage();
    }
}
