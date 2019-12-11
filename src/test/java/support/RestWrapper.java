package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestWrapper {

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private static String loginToken;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";

    public void login(Map<String, String> credentials) {

        // prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .baseUri(baseUrl)
                .header(CONTENT_TYPE, JSON)
                .body(credentials);

        // execute
        Response response = request.post("login");

        // verify and extract
        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        loginToken = "Bearer " + result.get("token");

    }
}
