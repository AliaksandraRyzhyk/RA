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

public class DeleteListTest {

    @Затем("Изменить список контактов путем удаление одного")
    @Step("POST/delete запрос")
    @Description("Описание шага: Проверка удаления имеющегося контакта")
    public void testDeleteOneContact() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        Map<String, String> params = new HashMap<>();
        params.put("format", UserConfig.getFormat());
        params.put("api_key", UserConfig.getApiKey());
        params.put("list_id", UserConfig.getListId());


        given()
                .queryParams(params)
                .when()
                .post("/deleteList")
                .then().log().all()
                .body("result", notNullValue());
    }
}
