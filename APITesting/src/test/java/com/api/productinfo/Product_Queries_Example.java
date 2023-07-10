package com.api.productinfo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Product_Queries_Example {
    static int idNumber;
    //  static ValidatableResponse response;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost"; // base URI
        RestAssured.port = 3030; // port number
        RestAssured.basePath = "/products"; // endpoints
    }

    @Test  // get single data
    public void test001(){
        Response response = given()
                .log().all()
                .pathParam("id","9132294")
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200);
    }

    @Test  // Get all products  (/products)
    public void test002(){
        Response response=given().when().get();
        response.then().log().all().statusCode(200);
    }

    @Test // Get all products, limit to 1 result ( /products?$limit=2)
    public void test003(){
        Response response = given()
                .queryParam("$limit","2")
                .when()
                .get();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004() {

        given()
                .queryParam("$skip","2500")
                .when()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test
    public void test005() {
        Response response = given()
                .queryParam("$sort[price]","-1")
                .when()
                .get();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test006(){
        given()
                .queryParam("$sort[price]","1")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test007(){
        given()
                .queryParam("$select[]","name")
                .queryParam("$select[]","price")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test008(){
        given()
                .queryParam("type","HardGood")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test009(){
        given()
                .queryParam("price[$lte]","1")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test010(){
        given()
                .queryParam("name[$like]","*star+wars*")
                .queryParam("price[$lt]","30")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test011(){
        given()
                .queryParam("price[$in]","0.99")
                .queryParam("price[$in]","1.99")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test012(){
        given()
                .queryParam("shipping[$gt]","10")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test013(){
        given()
                .queryParam("type[$nin][]","HardGood")
                .queryParam("type[$nin][]","HardGood")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test014(){
        given()
                .queryParam("category.name","Coffee Pods")
                .when().get().then().log().all().statusCode(200);
    }

    @Test
    public void test015(){
        given()
                .queryParam("category.id","abcat0106004")
                .when().get().then().log().all().statusCode(200);
    }
}
