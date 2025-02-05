package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import Models.BookModel;

import java.util.ArrayList;
import java.util.List;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;


public class BookApi {
    @Step("Удаляем все книги в профиле используя API")
    public Response deleteBooks(String userId, String token) {
        Response response = given(userRequestSpecification)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(userResponseSpecification204)
                .extract().response();
        return response;
    }
    @Step("Добавляем  книгу {bookName} в профиль используя API")
    public  Response addBooks (String bookName,String bookIsbn, String token,String userId) {
        BookModel getBookModel = new BookModel();
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
                .post("/BookStore/v1/Books")
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
        Response response =given(userRequestSpecification)
                .body(bookModel)
                .header("Authorization","Bearer "+token)
                .when()
                .delete("/BookStore/BookStore/v1/Book")
                .then()
                .spec(userResponseSpecification204)
                .extract().response();
        return response;
    }
}