package com.api.studentinfo;

import com.api.model.StudentPojo;
import com.api.testbase.TestBase;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPatchData extends TestBase {
    @Test
    public void updateStudentDataWithPatchMethod(){
        ArrayList<String> courses= new ArrayList<String>();
        courses.add("Mahesh");
        courses.add("Java");

        StudentPojo studentData= new StudentPojo();
        studentData.setFirstName("Manager1");
        studentData.setLastName("TestAPI2");
        studentData.setEmail("abc1234MM@gmail.com");
        studentData.setCourses(courses);


        given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id","110")
                .body(studentData)
                .when()
                .patch("/{id}")
                .then().log().all().statusCode(200);
    }
}
