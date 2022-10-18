package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utilits.FakeMessageGenerator;

import static com.codeborne.selenide.Selenide.$;

public class EditTagPage {

    private SelenideElement tagInput = $(By.id("tag"));
    private SelenideElement okButton = $(By.xpath("//button[@type='submit']"));



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
