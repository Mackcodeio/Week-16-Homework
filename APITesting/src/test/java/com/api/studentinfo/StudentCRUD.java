package com.api.studentinfo;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentCRUD {

    ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath ="/student";
    }

    @Test // Get List of Student
    public void getAllStudentInfo(){
       response = given()
                .log().all()
                .when()
                .get("/list").then();
               response.statusCode(200);
    }

    @Test // Get 1 ID data
    public void getSingleStudentInfo(){
        given().log().all()
                .pathParam("id",100)
                .when()
                .get("/{id}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void searchStudentWithParameter(){
        response =given()
                .queryParam("programme","Financial Analysis")
                .queryParam("limit",3)
                .when()
                .get("/list").then().log().all().statusCode(200);
    }
}