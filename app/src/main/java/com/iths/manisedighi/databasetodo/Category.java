package com.iths.manisedighi.databasetodo;

/**
 * Created by manisedighi on 09/02/2018.
 */

public class Category {

    private int categoryId;
    private String categoryName;

    public Category(){

    }

    public Category(int categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public String toString(){
        return this.categoryName;
    }



}
