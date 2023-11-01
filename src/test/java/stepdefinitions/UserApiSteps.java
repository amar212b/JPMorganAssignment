package stepdefinitions;

import common.TestContext;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.deser.DataFormatReaders;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.util;

import static org.junit.Assert.assertEquals;

public class UserApiSteps {
    private TestContext testContext;

    public UserApiSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserApiSteps.class);


    @Then("the response should contain a list of users")
    public void the_response_should_contain_a_list_of_users() {
        logger.info("Validating response size should be more than Zero");
        testContext.getResponse().then().assertThat().body("size()", Matchers.greaterThan(0)); // Assuming a non-empty list
    }

    @Then("the response should contain user details for ID {int}")
    public void the_response_should_contain_user_details_for_ID(int userId) {
        logger.info("Validating response should have user details of id sent in request");
        testContext.getResponse().then().assertThat().body("id",Matchers.equalTo(userId));
    }

    @And("the response should contain the created user details")
    public void the_response_should_contain_the_created_user_details() {
        // Add assertions to verify the presence of the created user's details in the response
        testContext.getResponse().then().assertThat().body("id", Matchers.equalTo(11));
        testContext.getResponse().then().assertThat().body("name", Matchers.equalTo("John Doe"));
        testContext.getResponse().then().assertThat().body("email", Matchers.equalTo("johndoe@example.com"));
        testContext.getResponse().then().assertThat().body("username", Matchers.equalTo("johndoe123"));
    }

    @And("the response should contain the updated user details")
    public void the_response_should_contain_the_updated_user_details() {
        // Add assertions to verify the presence of the updated user's details in the response
        testContext.getResponse().then().assertThat().body("name", Matchers.equalTo("Updating Name"));
    }


}
