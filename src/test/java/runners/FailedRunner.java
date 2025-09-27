package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failedRerun.txt",
        glue = {"stepdefinitions", "apphooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/",
                "rerun:target/failedRerun.txt"
        },
        dryRun = false,
        monochrome = true
        // tags = "@SmokeTest"//if we want to skip @Smoketest we can skip by giving not @SmokeTest
)
public class FailedRunner {
}
