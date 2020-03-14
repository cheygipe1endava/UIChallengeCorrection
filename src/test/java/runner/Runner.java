package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true , features = "src/test/resources/features/Login.feature",
                    glue = {"steps", "helper"})
public class Runner{

}