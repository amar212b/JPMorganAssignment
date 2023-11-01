package specBuilder;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ApiRequestBuilder {
    private RequestSpecification request;
    private ResponseSpecification responseSpec;


    public ApiRequestBuilder withRequestSpec(RequestSpecification requestSpec) {
        request = request.spec(requestSpec);
        return this;
    }

    public ApiRequestBuilder withResponseSpec(ResponseSpecification responseSpec) {
        this.responseSpec = responseSpec;
        return this;
    }

    public ApiRequestBuilder addHeader(String headerName, String headerValue) {
        request.header(headerName, headerValue);
        return this;
    }

    public ApiRequestBuilder addPathParam(String pathParamName, Object pathParamValue) {
        request.pathParam(pathParamName, pathParamValue);
        return this;
    }

    public Response get(String endpoint) {
        return request.get(endpoint).then().spec(responseSpec).extract().response();

    }

    public Response post(String endpoint, String requestBody) {
        return request.body(requestBody).post(endpoint).then().spec(responseSpec).extract().response();
    }

}

