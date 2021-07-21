package webServiceTesting.unit;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.json.simple.JSONObject;
import webServiceTesting.CreateUser;
import webServiceTesting.RegisterUser;
import webServiceTesting.models.Root;
import webServiceTesting.utils.Globals;
import static org.hamcrest.Matchers.*;



public class RegisterUserTest {

  protected RegisterUser registerUser = new RegisterUser();
  protected static Root configRoot = Globals.getWebTestingObject();
  JSONObject body = new JSONObject();
  private static String registerPath = configRoot.webTesting.baseUri+configRoot.webTesting.basePaths.registerPath;
  
  String jsonWithEmail = "{\n" +
      "    \"email\": \"testEmail\"\n" +
      "}";

  String jsonWithEmailAndPassword = "{\n" +
		  "    \"email\": \"testEmail\",\n" +
		  "    \"password\": \"testPassword\"\n" +
      "}";

  @Before
  public void setup() {
    registerUser.setEmail("testEmail");
    registerUser.setPassword("testPassword");
  }

  @Test
  public void buildBody_validEmail_shouldReturnJsonWithEmail() throws Exception {
	JSONAssert.assertEquals(jsonWithEmail, registerUser.buildBodyWithEmail().toString(), JSONCompareMode.STRICT);
  }

  @Test
  public void buildBody_validPassword_shouldReturnJsonWithPassword() throws Exception {
	JSONAssert.assertEquals(jsonWithEmailAndPassword, registerUser.buildBodyWithEmailAndPassword().toString(), JSONCompareMode.STRICT);
  }
  
  @Test
  public void test_PostRegisterValidUser_shouldReturnStatusCode200() {
	registerUser.setEmail("eve.holt@reqres.in");
	registerUser.setPassword("pistol");
	String json = registerUser.buildBodyWithEmailAndPassword().toString();
	
	registerUser.getRequestSpecification().
	given()
		.contentType("application/json")
		.body(json)
	.when()
		.post(registerPath)
	.then()
		.statusCode(200)
		.body("token", equalTo("QpwL5tke4Pnpja7X4"));
  }
  
  @Test
  public void test_PostRegisterInvalidUser_shouldReturnStatusCode400() {
	registerUser.setEmail("johndoe@johndoe.com");
	String json = registerUser.buildBodyWithEmail().toString();
	
	registerUser.getRequestSpecification().
	given()
		.contentType("application/json")
		.body(json)
	.when()
		.post(registerPath)
	.then()
		.statusCode(400)
		.body("error", equalTo("Missing password"));
  }
  
  
  
  
  
 

}