package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class ResponseSpecs {

    public static final RequestSpecification userRequestSpecification = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static final RequestSpecification userRequestSpecificationbaseUrl = with()
            .baseUri("https://demoqa.com")
            .contentType(JSON)
            .log().all();

    public static ResponseSpecification createResponseSpecification(int statusCode, boolean expectJson) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL);

        // Если ожидаем JSON, добавляем соответствующую проверку
        if (expectJson) {
            builder.expectContentType(JSON);
        }

        return builder.build();
    }

    // Примеры использования
    public static final ResponseSpecification userResponseSpecification200 = createResponseSpecification(200, false);
    public static final ResponseSpecification userResponseSpecification201 = createResponseSpecification(201, false);
    public static final ResponseSpecification userResponseSpecification201True = createResponseSpecification(201, true);
    public static final ResponseSpecification userResponseSpecification404 = createResponseSpecification(404, false);
    public static final ResponseSpecification userResponseSpecificationJson200 = createResponseSpecification(200, true);
    public static final ResponseSpecification userResponseSpecification204 = createResponseSpecification(204,false);

}
