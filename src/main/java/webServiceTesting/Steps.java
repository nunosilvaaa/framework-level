package webServiceTesting;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import webServiceTesting.models.Root;
import webServiceTesting.utils.Globals;

public class Steps {

  private static Root configRoot = Globals.getWebTestingObject();
  private static String usersPath = configRoot.webTesting.baseUri+configRoot.webTesting.basePaths.usersPath;
  private static String registerPath = configRoot.webTesting.baseUri+configRoot.webTesting.basePaths.registerPath;
  private CreateUser createUser;
  private RegisterUser registerUser;

  @Given("^I create the user creation service$")
  public void useUserCreationWebService() {
    createUser = new CreateUser();
  }
  
  @Given("^I create the user registration service$")
  public void useUserRegistrationWebService() {
    registerUser = new RegisterUser();
  }

  @When("^I set name \"([^\"]*)\"$")
  public void setName(String name) {
    createUser.setName(name);
  }
  
  @When("^I set job \"([^\"]*)\"$")
  public void setJob(String job) {
	createUser.setJob(job);
  }

  @When("^I set email \"([^\"]*)\"$")
  public void setEmail(String email) {
	registerUser.setEmail(email);
  }
  
  @When("^I set password \"([^\"]*)\"$")
  public void setPassword(String password) {
	registerUser.setPassword(password);
  }
  
  @Then("^I submit the user deletion request$")
  public void executeAddUserOperation() {
	  createUser.buildBody();;
  }
  
  @Then("^I submit the user registration request$")
  public void executeRegisterUserOperation() {
	registerUser.buildBodyWithEmail();
  }
  
  @Then("^I submit the user creation request$")
  public void executeDeleteUserOperation() {
	  createUser.buildBody();
  }

  @Then("^I validate the user has been created$")
  public void validateMyResponseIsCorrect() {
  createUser.getRequestSpecification()
	.given()
		.body(createUser.buildBody().toJSONString())
	.when()
		.post(usersPath)
	.then()
		.statusCode(201);
  }
  
  @Then("^I validate the user has been deleted$")
  public void validateUserHasBeenDeleted() {
    createUser.getRequestSpecification()
    .given()
    .when()
		.delete(usersPath+"/2")
	.then()
		.statusCode(204);
  }
  
  @Then("^I validate the user registration was not successful$")
  public void validateUserRegistrationWasNotSuccessful() {
    registerUser.getRequestSpecification()
    .given()
		.contentType("application/json")
		.body(registerUser.buildBodyWithEmail().toJSONString())
	.when()
		.post(registerPath)
	.then()
		.statusCode(400);
  }
}
