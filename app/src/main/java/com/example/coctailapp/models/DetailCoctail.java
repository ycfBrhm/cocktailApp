package com.example.coctailapp.models;

import com.google.gson.annotations.SerializedName;

public class DetailCoctail {

    @SerializedName("idDrink")
    private int id;

    @SerializedName("strDrink")
    private String name;

    @SerializedName("strDrinkThumb")
    private String image;


    @SerializedName("strCategory")
    private String category;

    @SerializedName("strAlcoholic")
    private String alcoholic;

    @SerializedName("strGlass")
    private String glass;

    @SerializedName("strInstructions")
    private String instruction;

    @SerializedName("strIngredient1")
    private String ingredient1;

    @SerializedName("strIngredient2")
    private String ingredient2;

    @SerializedName("strIngredient3")
    private String ingredient3;

    @SerializedName("strIngredient4")
    private String ingredient4;

    @SerializedName("strIngredient5")
    private String ingredient5;

    @SerializedName("strIngredient6")
    private String ingredient6;

    @SerializedName("strIngredient7")
    private String ingredient7;

    @SerializedName("strMeasure1")
    private String mesure1;

    @SerializedName("strMeasure2")
    private String mesure2;

    @SerializedName("strMeasure3")
    private String mesure3;

    @SerializedName("strMeasure4")
    private String mesure4;

    @SerializedName("strMeasure5")
    private String mesure5;

    @SerializedName("strMeasure6")
    private String mesure6;


    @SerializedName("strMeasure7")
    private String mesure7;

    @SerializedName("dateModified")
    private String date;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public String getGlass() {
        return glass;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public String getIngredient2() {
        return ingredient2;
    }

    public String getIngredient3() {
        return ingredient3;
    }

    public String getIngredient4() {
        return ingredient4;
    }

    public String getIngredient5() {
        return ingredient5;
    }

    public String getIngredient6() {
        return ingredient6;
    }

    public String getIngredient7() {
        return ingredient7;
    }

    public String getMesure1() {
        return mesure1;
    }

    public String getMesure2() {
        return mesure2;
    }

    public String getMesure3() {
        return mesure3;
    }

    public String getMesure4() {
        return mesure4;
    }

    public String getMesure5() {
        return mesure5;
    }

    public String getMesure6() {
        return mesure6;
    }

    public String getMesure7() {
        return mesure7;
    }

    public String getDate() {
        return date;
    }





}