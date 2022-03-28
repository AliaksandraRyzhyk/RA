package auth;

import config.UserConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import spec.SpecificationsForAuth;

import java.util.HashMap;
import java.util.Map;

import static config.UserConfig.BASE_PATH;
import static config.UserConfig.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthTokenTest {

    @Test
    @Order(1)
    public void successTestAuthToken() {
        SpecificationsForAuth.installSpecification(SpecificationsForAuth.requestSpec(BASE_URL), SpecificationsForAuth.responseSpecOK200());

        Map<String, String> params = new HashMap<>();
        params.put("login", UserConfig.USER_NAME);
        params.put("password", UserConfig.USER_PASSWORD);

        given()
                .body(params)
                .when()
                .post(BASE_PATH)
                .then().log().all()
                .body("success", equalTo(true))
                .body("result.token", notNullValue());

    }

    @Test
    @Order(2)
    public void unSuccessTestAuthToken() {
        SpecificationsForAuth.installSpecification(SpecificationsForAuth.requestSpec(BASE_URL), SpecificationsForAuth.responseSpecOK400());

        Map<String, String> params = new HashMap<>();
        params.put("login", UserConfig.USER_NAME);
        params.put("password", UserConfig.USER_PASSWORD + "2");

        Response response = given()
                .body(params)
                .when()
                .post(BASE_PATH)
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Boolean success = jsonPath.get("success");
        int code = jsonPath.get("code");

        Assertions.assertEquals("Bad request", message);
        Assertions.assertEquals(false, success);
        Assertions.assertEquals(400, code);

    }
}
