package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import webServiceTesting.models.Root;
import webServiceTesting.utils.Globals;

import org.json.simple.JSONObject;
//import webServiceTesting.Globals;

public class CreateUser {
  private static Root configRoot = Globals.getWebTestingObject();
  private String name;
  private String surname;
  private String job;
  
  private RequestSpecification requestSpecification;

  /**
  * Creates RequestSpecification object and sets up BaseUri and BasePath for Users endpoint
  * in order to send an HttpRequest and create a user
  */
  public CreateUser() {
	    this.requestSpecification = RestAssured.given()
	        .baseUri(configRoot.webTesting.baseUri)
	        .basePath(configRoot.webTesting.basePaths.usersPath);
	  }

  public RequestSpecification getRequestSpecification() {
    return requestSpecification;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setJob(String job) {
    this.job = job;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getSurname() {
    return this.surname;
  }

  public String getJob() {
    return this.job;
  }
  
  /**
  * Creates User object with name and job parameters
  * @return JSONObject: Object in relation to the user
  */
  private JSONObject getJsonUserObject(String name, String job) {
      JSONObject userObj = new JSONObject();
      userObj.put("name", name);
      userObj.put("job", job);
      return userObj;
  }

  /**
  * Creates and returns Users object containing the name and job parameters
  * @return JSONObject: Object in relation to the user (e.g. {"name": "testName", "job": "testJob"})
  */
  public JSONObject buildBody() {
	  JSONObject userObj = getJsonUserObject(this.name, this.job);
	  return userObj;
  }
  
  /**
  * Creates and returns Users object containing the name plus surname (separated by a whitespace) and job parameters
  * @return JSONObject: Object in relation to the user (e.g. {"name": "testName autoSurname", "job": "testJob"})
  */
  public JSONObject buildBodyWithSurname() {
      JSONObject userObj = getJsonUserObject("%s %s".formatted(this.name, this.surname), this.job);
	  return userObj;
  }
}
