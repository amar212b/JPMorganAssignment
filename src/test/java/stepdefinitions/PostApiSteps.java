package stepdefinitions;

import common.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.util;

import static org.junit.Assert.assertEquals;

public class PostApiSteps {
    private final TestContext testContext;

    public PostApiSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    private util u=new util();
    private static final Logger logger = LoggerFactory.getLogger(PostApiSteps.class);


    @Then("the response should contain a list of posts")
    public void the_response_should_contain_a_list_of_posts() {
        logger.info("Validating response size should be more than Zero");
        testContext.getResponse().then().assertThat().body("size()", Matchers.greaterThan(0)); // Assuming a non-empty list
    }

    @Then("the response should contain post details for ID {int}")
    public void the_response_should_contain_post_details_for_ID(int postId) {
        logger.info("Validating response should have post details of id sent in request");
        testContext.getResponse().then().assertThat().body("id",Matchers.equalTo(postId));
    }

    @And("the response should contain the created post details")
    public void the_response_should_contain_the_created_post_details() {
        testContext.getResponse().then().assertThat().body("userId", Matchers.equalTo(1));
        testContext.getResponse().then().assertThat().body("title", Matchers.equalTo("test post"));
        testContext.getResponse().then().assertThat().body("body", Matchers.equalTo("testing test post"));
    }

    @And("the response should contain the updated post details")
    public void the_response_should_contain_the_updated_post_details() {
        testContext.getResponse().then().assertThat().body("title", Matchers.equalTo("Updating title"));
    }

}
