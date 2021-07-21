package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import webServiceTesting.models.Root;
import webServiceTesting.utils.Globals;

import org.json.simple.JSONObject;
import webServiceTesting.utils.Globals;

public class RegisterUser {
  private static Root configRoot = Globals.getWebTestingObject();
  private String email;
  private String password;
  
  private RequestSpecification requestSpecification;

  /**
  * Creates RequestSpecification object and sets up BaseUri and BasePath for Register endpoint
  * in order to send an HttpRequest and register a user
  */
  public RegisterUser() {
	    this.requestSpecification = RestAssured.given()
	        .baseUri(configRoot.webTesting.baseUri)
	        .basePath(configRoot.webTesting.basePaths.registerPath);
	  }

  public RequestSpecification getRequestSpecification() {
    return requestSpecification;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getEmail() {
    return this.email;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  /*
   * Adds the body to the request for later use
   */
  private void addBodyToRequest(JSONObject body){
      this.requestSpecification =
              RestAssured.given().body(body.toJSONString())
	              .baseUri(configRoot.webTesting.baseUri)
	              .basePath(configRoot.webTesting.basePaths.registerPath);
  }

  /**
  * Creates User object with email parameter
  * @return JSONObject: Object containing only the email parameter
  */
  private JSONObject getJsonUserObject(String email) {
      JSONObject userObj = new JSONObject();
      userObj.put("email", this.email);
      return userObj;
  }
  
  /**
   * Creates User object with email and password parameters
   * @return JSONObject: Object containing the email and password parameters 
   */
  private JSONObject getJsonUserObject(String email, String password) {
      JSONObject userObj = new JSONObject();
      userObj.put("email", this.email);
      userObj.put("password", this.password);
      return userObj;
  }

  /**
  * Creates and returns Users object containing the email parameter
  * @return JSONObject: Object in relation to the user's credentials (e.g. {"email": "johndoe@johndoe.com"})
  */
  public JSONObject buildBodyWithEmail() {
	  JSONObject userObj = getJsonUserObject(this.email);
	  addBodyToRequest(userObj);
	  return userObj;
  }
  
  /**
  * Creates and returns Users object containing the email and password parameters
  * @return JSONObject: Object in relation to the user's credentials (e.g. {"email": "johndoe@johndoe.com", "password": "P@ssw0rd"})
  */
  public JSONObject buildBodyWithEmailAndPassword() {
	  JSONObject userObj = getJsonUserObject(this.email, this.password);
	  addBodyToRequest(userObj);
	  return userObj;
  }
}
