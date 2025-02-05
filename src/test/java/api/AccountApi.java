package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import Models.AccountModel;

import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;


public class AccountApi {

    @Step("Логинемся через API")
    public  Response loging (String userName, String password ){
        AccountModel lombokModelLogin=new AccountModel();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);

        Response response =given(userRequestSpecification)
                .log().all()
                .body(lombokModelLogin)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(userResponseSpecification201True)
                .extract().response();
        return response;
    }

    @Step("Получаем токен")
    public  Response gettoken (String userName, String password ){
        AccountModel lombokModelLogin=new AccountModel();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);

        Response response =given(userRequestSpecification)
                .log().all()
                .body(lombokModelLogin)
                .when()
                .post("/Account/v1/GenerateToken")
                .then()
                .spec(userResponseSpecification201True)
                .extract().response();
        return response;
    }

    @Step("Получаем список книг в профиле используя API")
    public  Response getUserBooks (String token, String userId ){
        Response response =given(userRequestSpecification)
                .header("Authorization","Bearer "+token)
                .when()
                .get("/Account/v1/User/"+userId)
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;

    }
}