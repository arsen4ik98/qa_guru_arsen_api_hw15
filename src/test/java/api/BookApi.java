package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.BookModel;
import org.aeonbits.owner.ConfigFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static specs.ResponseSpecs.*;


public class BookApi {
    WebDriverConfig WebDriverConfig = ConfigFactory.create(WebDriverConfig.class);
    String baseUrl = WebDriverConfig.getBaseUrl();
    BookModel.CollectionOfIsbns collection = new BookModel.CollectionOfIsbns();
    BookModel bookModel =new BookModel();

    @Step("Удаляем все книги в профиле используя API")
    public Response deleteBooks(String userId, String token) {
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
    @Step("Добавляем  книгу в профиль используя API")
    public  Response addBooks (String bookIsbn, String token,String userId) {
        bookModel.setUserId(userId);
        collection.setIsbn(bookIsbn);
        List<BookModel.CollectionOfIsbns> collections =new ArrayList<>();
        collections.add(collection);
        bookModel.setCollectionOfIsbns(collections);

        Response response =given(userRequestSpecification)
                .body(bookModel)
                .filter(withCustomTemplates())
                .header("Authorization", "Bearer " + token)
                .when()
                .post(baseUrl + "/BookStore/v1/Books")
                .then()
                .spec(userResponseSpecification201True)
                .extract().response();
        return response;
    }
    @Step("Удаляем книгу в профиле используя API " )
    public  Response deleteBook(String userId, String token, String bookIsbn){

        bookModel.setUserId(userId);
        bookModel.setIsbn(bookIsbn);
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

    @Step("Проверяем, что в профиле есть книги с ISBN {expectedIsbns}")
    public void assertBooksInProfile(Response response, String... expectedIsbns) {
        List<String> actualIsbns = response.jsonPath().getList("books.isbn", String.class);
        assertThat(actualIsbns)
                .as("Проверяем список книг в профиле")
                .containsExactlyInAnyOrder(expectedIsbns);
    }


}