package com.example.coctailapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity(tableName = "coctails")
public class Coctail implements Serializable {

    @PrimaryKey
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

    public String getDate() {
        return date;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
