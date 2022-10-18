package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ModalWindowLoginPage {

    private SelenideElement modalWindow = $(By.xpath("//div[@class='modal-content']"));
    private SelenideElement cancelButton = $(By.xpath("//div[text()='Cancel']"));

    public boolean isModalWindowDisplayed() {
        Configuration.timeout = 10000;
        return modalWindow.isDisplayed();
    }

    public EntriesPage closeModalWindow() {
        cancelButton.shouldBe(Condition.enabled, Duration.ofSeconds(8));
        cancelButton.click();
        return new EntriesPage();
    }
}
