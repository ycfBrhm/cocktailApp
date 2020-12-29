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


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
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
}
