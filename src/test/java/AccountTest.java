import config.AuthConfig;
import helpers.AuthHelper;
import org.aeonbits.owner.ConfigFactory;
import pages.AccountPage;

import api.AccountApi;
import api.BookApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("bookStore")
public class AccountTest extends TestBase {
    AccountApi apiAccount = new AccountApi();
    BookApi apiBookStore = new BookApi();
    AuthHelper poLogin = new AuthHelper();
    AccountPage poAccount = new AccountPage();
    AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
    String userName = authConfig.userName();
    String password = authConfig.password();
    String bookGitIsbn = "9781449325862";
    String bookJsIsbn = "9781449331818";
    String  bookGitName = "Git Pocket Guide";
    String bookJsName = "Learning JavaScript Design Patterns";



    @DisplayName("UI проверка удаления одной книге в профиле")
    @Test
    void deleteBookUiTest() {
        Response responseapiAccount = apiAccount.loging(userName, password);
        String userId = responseapiAccount.path("userId");
        String token = responseapiAccount.path("token");
        String expires = responseapiAccount.path("expires");
        apiBookStore.deleteBooks(userId, token);
        apiBookStore.addBooks(bookJsIsbn , token, userId);
        apiBookStore.addBooks(bookGitIsbn, token, userId);
        Response responseGetUserBooks = apiAccount.getUserBooks(token, userId);
        apiBookStore.assertBooksInProfile(responseGetUserBooks, bookGitIsbn, bookJsIsbn);
        poLogin.loginPageRegisteredPerson(userId, expires, token);
        poAccount.openAccount();
        poAccount.checkUserName(userName);
        poAccount.checkBook(bookGitName);
        poAccount.checkBook(bookJsName);
        poAccount.deleteBook(bookJsName);
        poAccount.deleteBookOk(bookJsName);
        poAccount.checkBook(bookGitName);
        poAccount.checkNotBook( bookJsName);


    }
}