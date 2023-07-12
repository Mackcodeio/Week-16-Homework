package com.api.studentinfo;

import com.api.testbase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {
    @Test
    public void deleteStudentData(){
        given()
                .log().all()
                .pathParam("id","110")
                .when()
                .delete("/{id}")
                .then().log().all()
                .statusCode(204);
    }
}
