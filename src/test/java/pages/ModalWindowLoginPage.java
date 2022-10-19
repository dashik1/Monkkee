package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class ModalWindowLoginPage {
    private SelenideElement modalWindow = $(By.xpath("//div[@class='modal-content']"));
    private SelenideElement cancelButton = $(By.xpath("//div[text()='Cancel']"));

    public boolean isModalWindowDisplayed() {
        return modalWindow.isDisplayed();
    }
    
    @Step("Close modal window")
    public EntriesPage closeModalWindow() {
        cancelButton.shouldBe(Condition.enabled, Duration.ofSeconds(8));
        cancelButton.click();
        return new EntriesPage();
    }
}
