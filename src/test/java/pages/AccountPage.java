package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class AccountPage {
    private SelenideElement booksPanel = $(".profile-wrapper"),
            accountPanel = $(".body-height"),
            closeSmallModalOk=$("#closeSmallModal-ok"),
            deleteBook=$(".profile-wrapper").$("#delete-record-undefined");

    @Step("Открываем страницу с Аккаунтом используя  UI ")
    public AccountPage openAccount(){
        open("/profile");
        return this;
    }
    @Step("Проверяем, что книга {bookName} Есть в корзине используя  UI ")
    public AccountPage checkBook(String bookName){
        booksPanel.shouldHave(text(bookName));
        return this;
    }
    @Step("Проверяем, что книги {bookName} НЕТ в корзине используя  UI ")
    public AccountPage checkNotBook(String bookName){
        booksPanel.shouldNotHave(text(bookName));
        return this;
    }
    @Step("Проверяем авторизованный профиль используя  UI ")
    public AccountPage checkUserName(String userName){
        accountPanel.shouldHave(text(userName));
        return this;
    }
    @Step("Удаляем книгу {bookName}  используя  UI ")
    public AccountPage deleteBook(String bookName){
        deleteBook.click();
        return this;
    }

    @Step("Подтверждаем удаление книги {bookName} используя  UI ")
    public AccountPage deleteBookOk(String bookName){
        closeSmallModalOk.click();
        return this;
    }
}