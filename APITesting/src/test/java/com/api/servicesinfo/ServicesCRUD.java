package com.api.servicesinfo;

import com.api.model.ServicesPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesCRUD {
    static int idNumber;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost"; // base URI
        RestAssured.port = 3030; // port number
        RestAssured.basePath = "/services"; // endpoints
    }

    @Test // get all list
    public void test001(){
        given()
                .when()
                .log().all()
                .get()
                .then().log().all()
                .statusCode(200);
    }

    @Test  // post new Data and retrive that ID number
    public void test002(){
        ServicesPojo data = new ServicesPojo();
        data.setName("Testing");

       Response response= given()
                .header("Content-Type", "application/json")
                .when()
                .body(data)
                .post();

        response.then().log().all().statusCode(201);

        idNumber=response.then().extract().path("id");
        System.out.println("ID Number of POST "+idNumber);
    }

    @Test // get single data
    public void test003(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","22")
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200);
    }

    @Test // Update id
    public void test004(){
        ServicesPojo data = new ServicesPojo();
        data.setName("Orange");

        given()
                .header("Content-Type","application/json")
                .pathParam("id","22")
                .when()
                .body(data)
                .patch("/{id}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void test005(){
        given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","22")
                .when()
                .delete("/{id}")
                .then().log().all().statusCode(200);
    }

}
