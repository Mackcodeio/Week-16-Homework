package com.api.category;

import com.api.model.Category;
import com.api.model.CategoryPath;
import com.api.model.SubCategory;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class CategoryCRUDTest {
    static int idNumber;
    //  static ValidatableResponse response;

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
        SubCategory subCategory = new SubCategory();
        CategoryPath categoryPath = new CategoryPath();

        ArrayList<String> sc = new ArrayList<String>();
        sc.add("Apple");
        sc.add("Mango");
        sc.add("Orange");
        sc.add("Banana");

        ArrayList<String> cp = new ArrayList<String>();
        cp.add("2 Apple");
        cp.add("3 Mango");
        cp.add("5 Orange");
        cp.add("7 Banana");

        category.setName("Fruits");




    }
}
