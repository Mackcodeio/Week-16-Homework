package com.api.studentinfo;

import com.api.model.StudentPojo;
import com.api.testbase.TestBase;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {

    @Test
    public void updateStudentData(){

        ArrayList<String> courses= new ArrayList<String>();
        courses.add("selenium1");
        courses.add("Restassured1");

        StudentPojo studentData= new StudentPojo();
        studentData.setFirstName("Test1");
        studentData.setLastName("Test2");
        studentData.setEmail("testing.app@gmail.com");
        studentData.setProgramme("Manual Testing");
        studentData.setCourses(courses);


        given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","110")
                .body(studentData)
                .when()
                .put("/{id}")
                .then().log().all().statusCode(200);
    }
}
