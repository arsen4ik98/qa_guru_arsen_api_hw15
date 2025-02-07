import models.LombokModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import specs.UserSpecs;

import java.time.LocalDate;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class HW15Tests extends tests.TestBase {
    @Test
    void checkStatusCodeTest() {
        Response response = step("Make request", ()->
                given(UserSpecs.userRequestSpecification)
                        .queryParam("page","2")
                        .when()
                        .get("/users"));
        step("Check response", ()->{
            response.then()
                    .spec(UserSpecs.userResponseSpecification200);
        });
    }

    @Test
    void checkSingleUserTest() {
        LombokModel response =step("Make request", ()->
                given(UserSpecs.userRequestSpecification)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(UserSpecs.userResponseSpecificationJson200)
                        .extract().as(LombokModel.class));
        step("Check response", ()-> {
            assertEquals(2, response.getData().getId());
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("Janet", response.getData().getFirst_name());
            assertEquals("Weaver", response.getData().getLast_name());
        });
    }
    @Test
    void checkSingleUserNotFoundTest() {
        LombokModel response = step("Make request", ()->
                given(UserSpecs.userRequestSpecification)
                        .when()
                        .get("/users/23")
                        .then()
                        .spec(UserSpecs.userResponseSpecification404)
                        .extract().as(LombokModel.class));
        step("Check response", ()-> {
            assertNull(response.getData());
            assertNull(response.getSupport());
        });
    }
    @Test
    void checkCreateTest() {
        LombokModel authData = new LombokModel();
        authData.setData(new LombokModel.Data());
        authData.setJob("tester");
        authData.setName("arsen");

        LombokModel response = step("Make request", ()->
                given(UserSpecs.userRequestSpecification)
                        .body(authData)
                        .when()
                        .post("/users")
                        .then()
                        .spec(UserSpecs.userResponseSpecification201)
                        .extract().as(LombokModel.class));
        step("Check response", ()-> {
            assertEquals("arsen", response.getName());
            assertEquals("tester", response.getJob());
        });

    }
    @Test
    void checkUpdateTest() {
        LombokModel authData = new LombokModel();
        authData.setData(new LombokModel.Data());
        authData.setJob("test lead");
        authData.setName("arsen");
        LombokModel response = step("Make request", ()->
                given(UserSpecs.userRequestSpecification)
                        .body(authData)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(UserSpecs.userResponseSpecification200)
                        .extract().as(LombokModel.class));
        step("Check response", ()->{
            assertEquals("arsen", response.getName());
            assertEquals("test lead", response.getJob());
            assertTrue( response.getUpdatedAt().startsWith(String.valueOf(LocalDate.now())));
        });

    }


}