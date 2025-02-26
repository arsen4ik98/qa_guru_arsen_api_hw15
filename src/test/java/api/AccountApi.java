package api;

import config.WebDriverConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AccountModel;
import org.aeonbits.owner.ConfigFactory;


import static io.restassured.RestAssured.given;
import static specs.ResponseSpecs.*;



public class AccountApi {
    AccountModel accountModelLogin=new AccountModel();
    WebDriverConfig WebDriverConfig = ConfigFactory.create(WebDriverConfig.class);
    String baseUrl = WebDriverConfig.getBaseUrl();
    @Step("Логинемся через API")
    public  Response loging (String userName, String password ){
        accountModelLogin.setUserName(userName);
        accountModelLogin.setPassword(password);
        Response response =given(userRequestSpecification)
                .body(accountModelLogin)
                .when()
                .post(baseUrl + "/Account/v1/Login")
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;
    }

    @Step("Получаем токен")

    public  Response gettoken (String userName, String password ){
        accountModelLogin.setUserName(userName);
        accountModelLogin.setPassword(password);

        Response response =given(userRequestSpecification)
                .log().all()
                .body(accountModelLogin)
                .when()
                .post(baseUrl + "/Account/v1/GenerateToken")
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
                .get(baseUrl + "/Account/v1/User/"+userId)
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;

    }
}