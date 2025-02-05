package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginPage {
    @Step("Открываем техническую страницу  Логинимся на странице ")
    public LoginPage loginPageRegisteredPerson(String userId, String expires, String token){
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID",userId));
        getWebDriver().manage().addCookie(new Cookie("expires",expires));
        getWebDriver().manage().addCookie(new Cookie("token",token));
        return this;
    }
}