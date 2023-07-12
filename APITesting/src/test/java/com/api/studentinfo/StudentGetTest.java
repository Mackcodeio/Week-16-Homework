package com.api.studentinfo;

import com.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentInfo(){
            given()
                    .log().all()
                    .when()
                    .get("/list")
                    .then().log().all().statusCode(200);
    }

    @Test
    public void getSingleStudentInfo(){
        given()
                .pathParam("id","110")
                .when()
                .get("/{id}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void getStudentDataWithlimit(){
        given()
                .queryParam("limit",3)
                .queryParam("")
                .when()
                .get("/list")
                .then()
                .log().all()
                .statusCode(200);
    }

}
