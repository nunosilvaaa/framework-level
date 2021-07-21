package webServiceTesting.utils;

import webServiceTesting.models.Root;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Globals {
	private static Root configRoot;
	public static Root getWebTestingObject()
	{
		try {
            ObjectMapper om = new ObjectMapper();
            Root root = om.readValue(new File(System.getProperty("user.dir") + "/settings.json"), Root.class);
            configRoot = root;
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public static String getDate() {
        DateTime _time = new DateTime();
        DateTimeFormatter time_formatter = DateTimeFormat.forPattern("YYYY-MM-dd-HH-mm-ss");
        return time_formatter.print(_time);
	}
	
	public String getReportConfigPath(){
		 String reportConfigPath = configRoot.staticPaths.reportConfigPath;
		 if(reportConfigPath!= null) return reportConfigPath;
		 else throw new RuntimeException("Report Config Path not specified in the settings.json file!"); 
		}
	
	public static void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
	
	public static void waitForLoad(WebDriver driver, final String script) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript(script).equals(true);
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}
