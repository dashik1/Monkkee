package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import constants.Urls;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
@Log4j
public class LoginPage {
    ModalWindowLoginPage modalWindowLoginPage = new ModalWindowLoginPage();

    private SelenideElement userInput = $(By.id("login"));
    private SelenideElement passwordInput = $(By.id("password"));
    private SelenideElement loginButton = $(By.xpath("//button[@type='submit']"));
    private SelenideElement blogLabel = $(By.xpath("//a[text()='Blog']"));
    private SelenideElement errorMessage = $(By.xpath("//div[@class='alert alert-danger']"));


    @Step("Login")
    public EntriesPage login(String email, String password)  {
        open(Urls.LOGIN_URL);
        userInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (modalWindowLoginPage.isModalWindowDisplayed()) {
            modalWindowLoginPage.closeModalWindow();
        } else {

            return new EntriesPage();
        }


        return new EntriesPage();
    }

    public LoginPage unsuccessfulLogin(String email, String password) {
        open(Urls.LOGIN_URL);
        userInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        errorMessage.shouldBe(Condition.enabled, Duration.ofSeconds(5));
        return this;
    }

    public BlogPage navigateToBlog() {
        blogLabel.click();
        return new BlogPage();
    }

    public boolean isErrorMessageDisplayed() {
        log.info("Error message " + errorMessage.getText() + " is displayed");
        return errorMessage.isDisplayed();

    }


}
