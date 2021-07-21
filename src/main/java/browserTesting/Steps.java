package browserTesting;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import webServiceTesting.models.Root;
import webServiceTesting.utils.Globals;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Steps {

	private static Root configRoot = Globals.getWebTestingObject();
    private static WebDriver driver;
    Properties properties = new Properties();
    
    @After
    public void tearDown(){
        if (driver != null)
        driver.quit();
    }
    
    @Given("^I open Chrome and launch the application$")
    public void openChromeAndLaunchApplication() throws InterruptedException
    {
    	//Setting system properties of ChromeDriver
    	System.setProperty("webdriver.chrome.driver", configRoot.staticPaths.chromeDriverPath);
    	
    	//Creating an object of ChromeDriver
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	
    	//Specifying pageLoadTimeout and Implicit wait
    	driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	//launching the specified URL
    	driver.get(configRoot.staticPaths.productSubscriptionPageURL);
    	
    	Globals.waitForLoad(driver);
    }

    @When("^I select type \"(.*)\"$")
    public void selectType(String type)
    {
        Select select = new Select(driver.findElement(By.id("type")));
        select.selectByVisibleText(type);
    }

    @When("^I select support plan \"(.*)\"$")
    public void selectSupportPlan(String plan)
    {
        Select select = new Select(driver.findElement(By.id("support")));
        select.selectByVisibleText(plan);
    }

    @When("^I write monthly duration of \"(.*)\"$")
    public void writeMonthlyDuration (String duration)
    {
        driver.findElement(By.id("duration")).sendKeys(duration);
    }

    @When("^I click in calculate price button")
    public void clickCalculatePriceButton()
    {
        driver.findElement(By.id("calculate")).click();
    }

    @Then("^I validate price is \"(.*)\"$")
    public void	validatePrice(String price) throws InterruptedException {
        Globals.waitForLoad(driver, "return document.getElementById('loader').classList.contains('d-none')");
        Assert.assertEquals(price, driver.findElement(By.id("price")).getText());
    }

/*
    @Then("^I attach file \"(.*)\"$")
    public void	attachFile(String file) throws  {
        WebElement uploadElement = driver.findElement(By.id("attachment"));
        uploadElement.sendKeys(file);
    }
*/
}
