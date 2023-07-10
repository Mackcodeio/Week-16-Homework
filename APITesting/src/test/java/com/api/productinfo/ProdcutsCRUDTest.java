package com.api.productinfo;

import com.api.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProdcutsCRUDTest {
    static int idNumber;
  //  static ValidatableResponse response;

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost"; // base URI
        RestAssured.port = 3030; // port number
        RestAssured.basePath = "/products"; // endpoints
    }

    @Test // get all list
    public void test001(){

        given()
                .when()
                .log().all()
                .get()
                .then().log().all().statusCode(200);
    }

    @Test // post new and retrive id
    public void test002(){
        ProductPojo datum = new ProductPojo();
        datum.setName("Leptop");
        datum.setType("HardGood");
        datum.setPrice(499);
        datum.setUpc("039800011229");
        datum.setShipping(0);
        datum.setDescription("4-pack AA alkaline batteries; battery tester included");
        datum.setManufacturer("nike");
        datum.setModel("tesla");
        datum.setUrl("http://www.bestbuy.com/site/energizer-max-batteries-aa-4-pack/150115.p?id=1051384046217&skuId=150115&cmp=RMXCC");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(datum)
                .post();


        response.then().log().all().statusCode(201);

        idNumber = response.then().extract().path("id");
        System.out.println(idNumber);
    }

    @Test // get single data
    public void test003(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999688")
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200);
    }

    @Test // Update id
    public void test004(){
        ProductPojo datum = new ProductPojo();
        datum.setModel("B179789");
        datum.setPrice(1600);

        Response response = given().log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999683")
                .when()
                .body(datum)
                .patch("/{id}");
        response.then().statusCode(200);
    }

    @Test   // delete it
    public void test005(){
        Response response = given()
                .log().all()
                .header("Content-Type","application/json")
                .pathParam("id","9999683")
                .when()
                .delete("/{id}");

        response.then().log().all().statusCode(200);
    }

    /********************* Querying the API ***********************/

    @Test  // get single data
    public void test006(){
        Response response = given()
                .log().all()
                .pathParam("id","9132294")
                .when()
                .get("/{id}");
        response.then().log().all().statusCode(200);
    }

    @Test  // Get all products  (/products)
    public void test007(){
        Response response=given().when().get();
        response.then().log().all().statusCode(200);
    }

    @Test // Get all products, limit to 1 result ( /products?$limit=2)
    public void test008(){
        Response response = given()
                            .queryParam("$limit","2")
                            .when()
                            .get();
                response.then().log().all().statusCode(200);
    }

    @Test
    public void test009() {

        given()
                .queryParam("$skip","2500")
                .when()
                .get()
                .then().log().all().statusCode(200);
    }
}
