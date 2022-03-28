package spec;

import com.epam.reportportal.junit5.ReportPortalExtension;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.extension.ExtendWith;

import static config.UserConfig.BASE_URL;

@ExtendWith(ReportPortalExtension.class)
public class SpecificationsForAuth {
    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

    }

    public static ResponseSpecification responseSpecOK400() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();

    }
    public static void installSpecification(RequestSpecification request,ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

}
