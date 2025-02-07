package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AccountModel;


import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;


public class AccountApi {

    @Step("Логинемся через API")
    public  Response loging (String userName, String password ){
        AccountModel lombokModelLogin=new AccountModel();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);

        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        Response response =given(userRequestSpecification)
                .body(lombokModelLogin)
                .when()
                .post(baseUrl + "/Account/v1/Login")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Получаем токен")

    public  Response gettoken (String userName, String password ){
        AccountModel lombokModelLogin=new AccountModel();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        Response response =given(userRequestSpecification)
                .log().all()
                .body(lombokModelLogin)
                .when()
                .post(baseUrl + "/Account/v1/GenerateToken")
                .then()
                .spec(userResponseSpecification201True)
                .extract().response();
        return response;
    }

    @Step("Получаем список книг в профиле используя API")
    public  Response getUserBooks (String token, String userId ){
        String baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        Response response =given(userRequestSpecification)
                .header("Authorization","Bearer "+token)
                .when()
                .get(baseUrl + "/Account/v1/User/"+userId)
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;

    }
}