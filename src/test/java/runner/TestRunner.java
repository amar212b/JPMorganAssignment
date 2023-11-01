package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)


@CucumberOptions(features="src/test/java/features",
        glue = "stepdefinitions",monochrome=true,dryRun = false,
        plugin = {"pretty","html:target/cucumber.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_scenarios.txt"},
        //tags="@abc41",
        publish = true)

public class TestRunner {

}
