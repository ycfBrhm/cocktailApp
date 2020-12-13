package com.example.coctailapp.responses;

import com.example.coctailapp.models.Coctail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoctailResponse {

    @SerializedName("drinks")
    private List<Coctail> coctails;

    public List<Coctail> getCoctails() {
        return coctails;
    }
}
