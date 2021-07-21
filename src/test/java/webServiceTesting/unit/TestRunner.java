package webServiceTesting.unit;

import java.io.File;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import java.util.Date;
import com.vimalselvam.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import webServiceTesting.utils.Globals;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = "src/test/features"
 ,glue={"webServiceTesting", "browserTesting"}
 ,plugin={"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:reports/report.html"}
 ,monochrome = true
)
 

public class TestRunner {
	@AfterClass
	 public static void writeExtentReport() {
	 Reporter.loadXMLConfig(new File(Globals.getWebTestingObject().staticPaths.reportConfigPath));
	 }
}

