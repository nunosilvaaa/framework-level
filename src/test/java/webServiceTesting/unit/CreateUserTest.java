package webServiceTesting.unit;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.json.simple.JSONObject;
import webServiceTesting.CreateUser;
import webServiceTesting.models.Root;
import webServiceTesting.utils.Globals;



public class CreateUserTest {

  CreateUser createUser = new CreateUser();
  private static Root configRoot = Globals.getWebTestingObject();
  JSONObject body = new JSONObject();
  private static String usersPath = configRoot.webTesting.baseUri+configRoot.webTesting.basePaths.usersPath;
  
  String jsonWithUserAndJob = "{\n" +
      "    \"name\": \"testName\",\n" +
      "    \"job\": \"testJob\"\n" +
      "}";

  String jsonWithUserNameSurnameAndJob = "{\n" +
      "    \"name\": \"testName autoSurname\",\n" +
      "    \"job\": \"testJob\"\n" +
      "}";

  @Before
  public void setup() {
    createUser.setName("testName");
    createUser.setSurname("autoSurname");
    createUser.setJob("testJob");
    
	body.put("name", createUser.getName());
	body.put("job", createUser.getJob());
  }

  @Test
  public void buildBody_validUserAndJob_shouldReturnJsonWithUserAndJob() throws Exception {
	JSONAssert.assertEquals(jsonWithUserAndJob, createUser.buildBody().toString(), JSONCompareMode.STRICT);
  }

  @Test
  public void buildBodyWithSurname_validUserAndJob_shouldReturnJsonWithUserNameSurnameAndJob() throws Exception {
	JSONAssert.assertEquals(jsonWithUserNameSurnameAndJob, createUser.buildBodyWithSurname().toString(), JSONCompareMode.LENIENT);
  }
  
  @Test
  public void test_postUserInfo_shouldReturnStatusCode201() {
	createUser.getRequestSpecification().
	given()
		.body(body.toJSONString()).
	when()
		.post(usersPath)
	.then()
		.statusCode(201);
  }
  
  @Test
  public void test_putUserInfo_shouldReturnStatusCode200() {
	createUser.getRequestSpecification().
	given()
		.body(body.toJSONString()).
	when()
		.put(usersPath).
	then()
		.statusCode(200);
  }
  
  @Test
  public void test_patchUserInfo_shouldReturnStatusCode200() {
	createUser.getRequestSpecification().
	given()
		.body(body.toJSONString()).
	when()
		.patch(usersPath).
	then()
		.statusCode(200);
  }
  
  @Test
  public void test_deleteUser_shouldReturnStatusCode204() {
	createUser.getRequestSpecification().
	given()
		.body(body.toJSONString()).
	when()
		.delete(usersPath+"/2").
	then()
		.statusCode(204);
  }
  
  @Test
  public void test_GetUserNotFound_shouldReturnStatusCode404() {
	createUser.getRequestSpecification().
	given()
	.when()
		.get(usersPath+"/2344")
	.then()
		.statusCode(404);
  }
  
  
  
 

}