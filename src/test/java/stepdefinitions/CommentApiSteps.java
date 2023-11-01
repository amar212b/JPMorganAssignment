package stepdefinitions;

import common.CommonApiMethods;
import common.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Comment;
import utils.util;

import java.util.List;
import java.util.Map;

public class CommentApiSteps {
    private TestContext testContext;

    public CommentApiSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    private static final Logger logger = LoggerFactory.getLogger(CommentApiSteps.class);


    @Then("the response should contain a list of comments")
    public void the_response_should_contain_a_list_of_comments() {
        logger.info("Validating response size should be more than Zero");
        testContext.getResponse().then().assertThat().body("size()", Matchers.greaterThan(0)); // Assuming a non-empty list
    }

    @Then("the response should contain comment details for ID {int}")
    public void the_response_should_contain_comment_details_for_ID(int commentId) {
        logger.info("Validating response should have comment details of id sent in request");
        testContext.getResponse().then().assertThat().body("id", Matchers.equalTo(commentId));
    }

    @And("the response should contain the created comment details")
    public void the_response_should_contain_the_created_comment_details() {
        testContext.getResponse().then().assertThat().body("postId", Matchers.equalTo(510));
        testContext.getResponse().then().assertThat().body("id", Matchers.equalTo(501));
        testContext.getResponse().then().assertThat().body("name", Matchers.equalTo("test"));
        testContext.getResponse().then().assertThat().body("email", Matchers.equalTo("test@gardner.biz"));
        testContext.getResponse().then().assertThat().body("body", Matchers.equalTo("testing test"));
    }


    @And("the response should contain the comment details")
    public void the_response_should_contain_the_comment_details(DataTable dt) {
        List<Map<String, String>> postDetails = dt.asMaps(String.class, String.class);
        for (Map<String, String> data : postDetails) {
            Map<String,String> result = testContext.getResponse().then().extract().body().jsonPath().get();
            testContext.getResponse().then().assertThat().body("postId", Matchers.equalTo(Integer.parseInt(data.get("postId"),10)));
            testContext.getResponse().then().assertThat().body("id", Matchers.equalTo(Integer.parseInt(data.get("id"),10)));
            testContext.getResponse().then().assertThat().body("name", Matchers.equalTo(data.get("name")));
            testContext.getResponse().then().assertThat().body("email", Matchers.equalTo(data.get("email")));
            testContext.getResponse().then().assertThat().body("body", Matchers.equalTo(data.get("body")));
        }

    }

    @And("the response should contain the updated comment details")
    public void the_response_should_contain_the_updated_comment_details() {
        // Add assertions to verify the presence of the updated comment's details in the response
        testContext.getResponse().then().assertThat().body("title", Matchers.equalTo("Updating title"));
    }

    @When("I send a POST request to {string} with multiple JSON body:")
    public void i_send_a_POST_request_JSON_body(String endpoint, DataTable dt) {
        List<Map<String, String>> postDetails = dt.asMaps(String.class, String.class);
        for (Map<String, String> data : postDetails) {
            Comment comm = new Comment();
            comm.setpostId(Integer.parseInt(data.get("postId"), 10));
            //comm.setId(Integer.parseInt(data.get("id"), 10));
            comm.setName(data.get("name"));
            comm.setEmail(data.get("email"));
            comm.setBody(data.get("body"));
            testContext.setResponse(CommonApiMethods.performPostRequest(testContext.getRequestSpec(), endpoint, comm));
        }

    }
}
