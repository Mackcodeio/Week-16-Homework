package com.api.studentinfo;

import com.api.model.StudentPojo;
import com.api.testbase.TestBase;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class StudentPostTest  extends TestBase {

    @Test
    public void createStudent(){
        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Manual Testing");
        courses.add("Programming with JAVA");
        courses.add("Postman");


        StudentPojo data = new StudentPojo();
        data.setFirstName("John");
        data.setLastName("Smith");
        data.setEmail("johnsmith90@gmail.com");
        data.setProgramme("Computer Science");
        data.setCourses(courses);

        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(data)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }
}
