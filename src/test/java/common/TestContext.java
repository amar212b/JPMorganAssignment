package common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestContext {
    private RequestSpecification requestSpec;

    private ResponseSpecification responseSpec;
    private Response response;

    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    public void setRequestSpec(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec;
    }

    public ResponseSpecification getResponseSpec() {
        return responseSpec;
    }

    public void setResponseSpec(ResponseSpecification responseSpec) {
        this.responseSpec = responseSpec;
    }


    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}


