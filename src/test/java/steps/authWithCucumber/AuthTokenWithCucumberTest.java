package steps.authWithCucumber;

import config.UserConfig;
import io.cucumber.java.ru.Затем;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import spec.SpecificationsForAuth;

import java.util.HashMap;
import java.util.Map;

import static config.UserConfig.BASE_PATH;
import static config.UserConfig.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTokenWithCucumberTest {

   @Затем("Ввести валидные логин и пароль")
   @Step("POST запрос")
   @Description("Описание теста: Успешная авторизация на основе токена")
    public void successTestAuthTokenWithCucumber() {
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

    @Затем("Ввести логин и невалидный пароль")
    @Step("POST запрос")
    @Description("Описание теста: Неуспешная авторизация на основе токена")
    public void unSuccessTestAuthTokenWithCucumber() {
        SpecificationsForAuth.installSpecification(SpecificationsForAuth.requestSpec(BASE_URL), SpecificationsForAuth.responseSpecOK400());

        Map<String, String> params = new HashMap<>();
        params.put("login", UserConfig.USER_NAME);
        params.put("password", UserConfig.USER_PASSWORD + "1");


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
