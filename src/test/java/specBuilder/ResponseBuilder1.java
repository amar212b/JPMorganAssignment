package specBuilder;

import io.restassured.specification.ResponseSpecification;

public class ResponseBuilder1 {

    private ResponseBuilder1 responseBuilder;

    public ResponseBuilder1() {
        responseBuilder = new ResponseBuilder1();
    }

    public ResponseBuilder1 expectStatusCode(int statusCode) {
        responseBuilder.expectStatusCode(statusCode);
        return this;
    }

    public ResponseSpecification build() {
        return responseBuilder.build();
    }
}

