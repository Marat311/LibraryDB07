package com.library.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "src/test/resources/features" , //alternatively "classpath:features"
        glue = "com/library/step_definitions" ,
        plugin = {"pretty", "html:target/cucumber.html",
                "json:target/cucumber.json", // this will generate json report
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target" // this is for detailed html report
        } ,
        dryRun = false
          , tags ="@us5"
)

public class TestRunner {
}
