package com.api.model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<SubCategory> subCategories;
    private ArrayList<CategoryPath> categoryPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public ArrayList<CategoryPath> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(ArrayList<CategoryPath> categoryPath) {
        this.categoryPath = categoryPath;
    }


}
