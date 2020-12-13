package com.example.coctailapp.responses;

import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.models.DetailCoctail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailCoctailResponse {

    @SerializedName("drinks")
    private List<DetailCoctail> detailsCoctails;

    public DetailCoctail getDetailsCoctail(){
        return detailsCoctails.get(0);
    }
}
