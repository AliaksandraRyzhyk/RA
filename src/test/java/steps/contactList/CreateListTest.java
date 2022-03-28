package steps.contactList;

import config.UserConfig;
import io.cucumber.java.ru.Затем;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import spec.Specifications;
import java.util.HashMap;
import java.util.Map;
import static config.UserConfig.URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateListTest {

    @Затем("Создать новый контакт")
    @Step("POST/create запрос")
    @Description("Описание шага: Проверка создания нового контакта")
    public void testCreateNewContact() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        Map<String, String> params = new HashMap<>();
        params.put("format", UserConfig.getFormat());
        params.put("api_key", UserConfig.getApiKey());
        params.put("title", UserConfig.getTitle());

        given()
                .queryParams(params)
                .when()
                .post("/createList")
                .then().log().all()
                .body("result.id", notNullValue());

    }
}
