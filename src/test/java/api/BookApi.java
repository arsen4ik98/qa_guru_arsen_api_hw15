package api;

import config.AuthConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.BookModel;
import org.aeonbits.owner.ConfigFactory;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Configuration.baseUrl;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;


public class BookApi {
    @Step("Удаляем все книги в профиле используя API")
    public Response deleteBooks(String userId, String token) {
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
        String baseUrl = authConfig.baseUrl();

        Response response = given(userRequestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete(baseUrl + "/BookStore/v1/Books")
                .then()
                .spec(userResponseSpecification204)
                .extract().response();
        return response;
    }
    @Step("Добавляем  книгу {bookName} в профиль используя API")
    public  Response addBooks (String bookName,String bookIsbn, String token,String userId) {
        BookModel getBookModel = new BookModel();
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
        String baseUrl = authConfig.baseUrl();
        BookModel.CollectionOfIsbns collection = new BookModel.CollectionOfIsbns();
        getBookModel.setUserId(userId);
        collection.setIsbn(bookIsbn);
        List<BookModel.CollectionOfIsbns> collections =new ArrayList<>();
        collections.add(collection);
        getBookModel.setCollectionOfIsbns(collections);


        Response response =given(userRequestSpecification)
                .body(getBookModel)
                .filter(withCustomTemplates())
                .header("Authorization", "Bearer " + token)
                .when()
                .post(baseUrl + "/BookStore/v1/Books")
                .then()
                .spec(userResponseSpecification201True)
                .extract().response();
        return response;
    }
    @Step("Удаляем книгу {bookName} в профиле используя API " )
    public  Response deleteBook(String bookName,String userId, String token, String bookIsbn){
        BookModel bookModel =new BookModel();
        bookModel.setUserId(userId);
        bookModel.setIsbn(bookIsbn);
        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class);
        String baseUrl = authConfig.baseUrl();
        Response response =given(userRequestSpecification)
                .body(bookModel)
                .header("Authorization","Bearer "+token)
                .when()
                .delete(baseUrl + "/BookStore/BookStore/v1/Book")
                .then()
                .spec(userResponseSpecification204)
                .extract().response();
        return response;
    }
}