package com.api.categoryinfo;

import com.api.model.Category;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoryCRUDTest {
    static String idNumber;
   // static ValidatableResponse response;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost"; // base URI
        RestAssured.port = 3030; // port number
        RestAssured.basePath = "/categories"; // endpoints
    }

    // get all list
    @Test
    public void test001(){
        given().when().get().then().log().all().statusCode(200);
    }

    @Test //  post new and retrive ID
    public void test002(){
        Category category = new Category();

        category.setId("asd123562");
        category.setName("Fruits");

         Response response= given()
                .log().all()
                .header("Content-Type","application/json")
                 .body(category)
                 .when()
                 .post();

        response.then().log().all().statusCode(201);

        idNumber = response.then().extract().path("id");
        System.out.println(idNumber);
    }

    @Test // get single data
    public void getSingleCategoryData(){
        Response response = given()
                .log().all()
                .pathParam("id","asd123562")
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200);
    }

    @Test  // Update
    public void updateSingleCategoryData(){
        Category category = new Category();
        category.setId("AK47");
        category.setName("Python Progamming");

        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","asd123562")
                .body(category)
                .when()
                .patch("/{id}");
                response.then().log().all().statusCode(200);
    }

    @Test// delete it
    public void deleteCategoryData() {
        Response response =given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","asd123562")
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

}
