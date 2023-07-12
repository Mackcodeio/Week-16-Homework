package com.api.studentinfo;

import com.api.model.StudentPojo;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StudentCRUD {

    ValidatableResponse response;
    static Object studentId;

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
               response.log().all().statusCode(200);
    }

    @Test // Get 1 ID data
    public void getSingleStudentInfo(){
        given().log().all()
                .pathParam("id",103)
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

    @Test // Post a new Data
    public void createNewStudentInfo(){
        StudentPojo data = new StudentPojo();
        data.setFirstName("Keyur");
        data.setLastName("Patel");
        data.setEmail("keyurpatel.97@gmail.com");
        data.setProgramme("API Testing");

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Manual Testing");
        courses.add("JS Programming");
        courses.add("Java Programming");

        data.setCourses(courses);

        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(data)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }

    @Test //post new data and retrive id
    public void retriveID(){
        HashMap<String, Object> studentMap = given()

                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path("findAll{it.firstName == 'Gary'}.get(0)");

        studentId = studentMap.get("id");
        System.out.println(studentId);
    }

    @Test// update data with id
    public void test003() {
        StudentPojo data = new StudentPojo();
        data.setFirstName("Keyur");
        data.setLastName("Patel");
        data.setEmail("keyurpatel.97@gmail.com");
        data.setProgramme("API Testing with Automation Testing");

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Manual Testing");
        courses.add("JS Programming");
        courses.add("Java Programming");
        courses.add("Automation Testing");

        data.setCourses(courses);

        given()
                .header("Content-Type","application/json")
                .pathParam("id",106)
                .when()
                .body(data)
                .patch("/{id}")
                .then().log().all().statusCode(200);

    }


    @Test // delete above id
    public void test004() {
        given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id",106)
                .when()
                .delete("/{id}").then().statusCode(200);;
    }

    @Test // retrieve id and validate id has delete
    public void test005() {
        given()
                .log().all()
                .pathParam("id",106)
                .when()
                .get("/{id}").then().log().all().statusCode(404);
    }

}