package com.example.coctailapp.models;

import com.google.gson.annotations.SerializedName;

public class Coctail {

    @SerializedName("idDrink")
    private int id;

    @SerializedName("strDrink")
    private String name;

    @SerializedName("strDrinkThumb")
    private String image;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }


}
