package steps.contactList;

import config.UserConfig;
import io.cucumber.java.ru.Затем;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pojo.GetListPojo;
import spec.Specifications;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static config.UserConfig.URL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class GetListTest {

    @Затем("Получить окончательный список контактов")
    @Step("Get запрос")
    @Description("Описание шага: Проверка наличия окончательного списка контактов")
    public void testGetContactList() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        Map<String, String> params = new HashMap<>();
        params.put("format", UserConfig.getFormat());
        params.put("api_key", UserConfig.getApiKey());


        List<GetListPojo> lists = given()
                    .queryParams(params)
                    .when()
                    .get("/getLists")
                    .then().log().all()
                    .extract().jsonPath().getList("result", GetListPojo.class);

        assertThat(lists).extracting(GetListPojo::getId).isNotNull();
        assertThat(lists).extracting(GetListPojo::getTitle).isNotNull();
    }
}
