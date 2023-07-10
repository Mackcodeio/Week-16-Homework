package com.api.stores;

import com.api.model.Stores;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest {

    public static int idNumber;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
    }

    @Test //get all list
    public void test001(){
        given().when().get().then().log().all().statusCode(200);
    }

    @Test // Post new and retrive id
    public void test002(){
        Stores data = new Stores();
        data.setName("HP Mouse D22S");
        data.setType("Mouse");
        data.setAddress("HP world UK House");
        data.setAddress2("John cricle");
        data.setCity("London");
        data.setState("London 2");
        data.setZip("CRK 99J");
        data.setLat(44.969658);
        data.setLng(-93.449539);
        data.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");

        Response response =given().header("Content-Type", "application/json")
                .when()
                .body(data).post();

        response.then().log().all().statusCode(201);

        idNumber= response.then().extract().path("id");
        System.out.println("Created Data ID:"+idNumber);
    }

    @Test //update id (patch)
    public void test003(){
        Stores data = new Stores();
        data.setCity("Mana");
        data.setState("ABC");

        given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id",idNumber)
                .when()
                .body(data)
                .patch("/{id}")
                .then().log().all().statusCode(200);
    }

    @Test // delete
    public void test005(){
        Response response =given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id",idNumber)
                .when()
                .delete("/{id}");
        response.then().statusCode(200);
    }

}
