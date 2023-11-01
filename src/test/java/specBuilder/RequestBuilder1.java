package specBuilder;

import io.restassured.specification.RequestSpecification;

public class RequestBuilder1 {

    private RequestBuilder1 requestBuilder;

    public RequestBuilder1() {
        requestBuilder = new RequestBuilder1();
    }

    public RequestBuilder1 addHeader(String headerName, String headerValue) {
        requestBuilder.addHeader(headerName, headerValue);
        return this;
    }

    public RequestSpecification build() {
        return requestBuilder.build();
    }
}

