package com.fisglobal.com.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.junit.BeforeClass;

import io.restassured.RestAssured;
//@SpringBootTest(PropertyFileEditorApplicationTests.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PropertyFileEditorApplicationTests {

	@Test
	void contextLoads() {
	}

	

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
   // http://localhost:8080/listAllFiles/version2/108 
    @Test
    public void testUserFetchesSuccess() {
        get("/listAllFiles/version2/108")
        .then().assertThat()
        .statusCode(HttpStatus.OK.value());
//        .body("id", equalTo(12))
//        .body("firstName", equalTo("Vinod"))
//        .body("lastName", equalTo("Kashyap"))
//        .body("designation", equalTo("CEO"));
    }
}
