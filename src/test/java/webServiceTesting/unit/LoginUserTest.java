package webServiceTesting.unit;

import org.junit.Test;
import static org.hamcrest.Matchers.*;



public class LoginUserTest extends RegisterUserTest {
	
  private static String loginPath = configRoot.webTesting.baseUri+configRoot.webTesting.basePaths.loginPath;
	
  @Test
  public void test_PostLoginValidUser_shouldReturnStatusCode200() {
	registerUser.setEmail("eve.holt@reqres.in");
	registerUser.setPassword("cityslicka");
	String json = registerUser.buildBodyWithEmailAndPassword().toString();
	
	registerUser.getRequestSpecification().
	given()
		.contentType("application/json")
		.body(json)
	.when()
		.post(loginPath)
	.then()
		.statusCode(200)
		.body("token", equalTo("QpwL5tke4Pnpja7X4"));
  }
  
  @Test
  public void test_PostLoginInvalidUser_shouldReturnStatusCode400() {
	registerUser.setEmail("johndoe@johndoe.com");
	String json = registerUser.buildBodyWithEmail().toString();
	
	registerUser.getRequestSpecification().
	given()
		.contentType("application/json")
		.body(json)
	.when()
		.post(loginPath)
	.then()
		.statusCode(400)
		.body("error", equalTo("Missing password"));
  }
  
  
  
  
  
 

}