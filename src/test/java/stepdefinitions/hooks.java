package stepdefinitions;

import common.CommonApiMethods;
import common.TestContext;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.util;

import java.io.IOException;
import java.io.PrintStream;

public class hooks {
    private static final Logger logger = LoggerFactory.getLogger(hooks.class);

    private final TestContext testContext;

    public hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setup () throws IOException {
        logger.info("Setup Request Spec");
        RestAssured.config = RestAssured.config().sslConfig(
                new SSLConfig().allowAllHostnames().relaxedHTTPSValidation());

        RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
               // .setBaseUri("https://jsonplaceholder.typicode.com/")
                .setBaseUri(util.getBaseURLValue("baseUrl"))
                .addHeader("Accept", "application/json").setConfig(RestAssured.config)
                .addFilter(new RequestLoggingFilter(LogDetail.URI))
                .addFilter(new ResponseLoggingFilter(LogDetail.STATUS))
                .setContentType(ContentType.JSON).log(LogDetail.BODY).setRelaxedHTTPSValidation()
                .build();

        ResponseSpecification responseSpec = new ResponseSpecBuilder().log(LogDetail.ALL).build();
        testContext.setRequestSpec(requestSpec);
        testContext.setResponseSpec(responseSpec);

        logger.info("Request Base Details"+String.valueOf(requestSpec.log()));
        logger.info("Response Base Details"+String.valueOf(responseSpec));
        //RestAssured.requestSpecification = requestSpec;
        //RestAssured.responseSpecification = responseSpec;


    }
}
