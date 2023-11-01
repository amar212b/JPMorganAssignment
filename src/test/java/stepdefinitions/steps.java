package stepdefinitions;

import common.CommonApiMethods;
import common.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Comment;
import specBuilder.ApiRequestBuilder;
import io.restassured.response.Response;
import utils.util;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
//import org.testng.Assert;

public class steps {
    //private Response response;
    private util u=new util();
    private TestContext testContext;
    private static final Logger logger = LoggerFactory.getLogger(steps.class);

    public steps(TestContext testContext){
        this.testContext = testContext;
    }


    @Given("the JSONPlaceholder API is available")
    public void the_JSONPlaceholder_API_is_available() {

    }

    @When("I send a GET request to {string}")
    public void i_send_a_GET_request_to(String endpoint) {
        testContext.setResponse(CommonApiMethods.performGetRequest(RestAssured.requestSpecification,endpoint));
    }

    @When("I send a POST request to {string} with JSON body:")
    public void i_send_a_POST_request_with_JSON_body(String endpoint,String requestBody) {
        testContext.setResponse(CommonApiMethods.performPostRequest(RestAssured.requestSpecification,endpoint, requestBody));
    }

    @When("I send a PUT request to {string} with JSON:")
    public void i_send_a_PUT_request_to_user_with_the_following_JSON(String endpoint, String requestBody) {
        testContext.setResponse(CommonApiMethods.performPutRequest
                (RestAssured.requestSpecification,endpoint, requestBody));

        //response = request.body(requestBody).put("/users/" + userId);
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_DELETE_request_to_user(String endpoint) {
        testContext.setResponse(CommonApiMethods.performDeleteRequest
                (RestAssured.requestSpecification,endpoint));
        //response = request.delete("/users/" + userId);
    }

    @When("I send a custom request to {string}")
    public void i_send_a_custom_request_to(String endpoint) {
       // response = apiRequestBuilder.get(endpoint).execute();
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        //int actualStatusCode = response.getStatusCode();
        CommonApiMethods.validateStatusCode(testContext.getResponse(), expectedStatusCode);
    }

    @Then("the response should contain a list of {string}")
    public void the_response_should_contain_a_list_of(String key) {
        // Implement validation for a list of items in the response.
    }

    @Then("the response should contain a {string} with ID {int}")
    public void the_response_should_contain_a_with_ID(String key, int expectedId) {
        // Implement validation for a specific item in the response.
    }

    @And("the response should indicate successful deletion")
    public void the_response_should_indicate_successful_deletion() {
        // Add assertions to verify that the response indicates successful deletion
        testContext.getResponse().then().assertThat().body("message", Matchers.nullValue());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String Expectedvalue) {
        logger.info("Validating key value in the api body");

        assertEquals(u.getJsonPath(testContext.getResponse(),keyValue),Expectedvalue);
    }

    @Then("the response should contain empty data")
    public void the_response_should_contain_empty_data() {
        logger.info("Validating empty response");
        testContext.getResponse().then().assertThat().and().extract().response().body().asString().equals("{}");
        testContext.getResponse().then().assertThat().body("message",Matchers.anything());
    }
    // Add step definitions for other scenarios, such as PUT and DELETE requests
}


