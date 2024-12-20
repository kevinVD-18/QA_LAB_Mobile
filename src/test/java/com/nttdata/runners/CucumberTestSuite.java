package com.nttdata.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/login.feature",
        glue = "com.nttdata.stepsDefinitions",
        tags = "@login_success"
)
public class CucumberTestSuite {
}
