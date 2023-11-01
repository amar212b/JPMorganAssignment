package common;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class CommonApiMethods {

    private static final Logger logger = LoggerFactory.getLogger(CommonApiMethods.class);

    public static Response performGetRequest(RequestSpecification requestSpecification, String endpoint) {
        logger.info("Sending GET Request on:"+endpoint);
        return given()
                .spec(requestSpecification)
                .get(endpoint);
    }

    public static Response performPostRequest(RequestSpecification requestSpecification, String endpoint, String requestBody) {
        logger.info("Sending POST Request on:"+endpoint);
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(endpoint);
    }

    public static Response performPostRequest(RequestSpecification requestSpecification, String endpoint, Object requestBody) {
        logger.info("Sending POST Request on:"+endpoint);
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .post(endpoint);
    }

    public static Response performPutRequest(RequestSpecification requestSpecification, String endpoint, String requestBody) {
        logger.info("Sending PUT Request on:"+requestSpecification.toString(),endpoint);
        return given()
                .spec(requestSpecification)
                .body(requestBody)
                .put(endpoint);
    }

    public static Response performDeleteRequest(RequestSpecification requestSpecification, String endpoint) {
        logger.info("Sending DELETE Request on:"+requestSpecification.toString(),endpoint);

        return given()
                .spec(requestSpecification)
                .delete(endpoint).then().log().all().extract().response();
    }

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        logger.info("Validate the Status code to be equal to:"+expectedStatusCode);
        logger.info(String.valueOf(response.getStatusCode()));
        Object endpoint = null;
        response.then().assertThat().statusCode(expectedStatusCode).log().all();
        if (response.getStatusCode() == expectedStatusCode) {
            logger.info("request passed with a status code of {}.",response.getStatusCode());
        } else {
            logger.error("request failed with a status code of {}.", response.getStatusCode());

        }

    }

    public static void validateResponseContainsKey(Response response, String key) {
        response.then()
                .body(key, notNullValue());
    }

    public static void validateResponseBodyEquals(Response response, String expectedBody) {
        response.then()
                .body(equalTo(expectedBody));
    }

    public static void validateResponseHeader(Response response, String headerName, String expectedValue) {
        response.then()
                .header(headerName, expectedValue);
    }

}


