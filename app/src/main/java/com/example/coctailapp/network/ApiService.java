package com.example.coctailapp.network;

import com.example.coctailapp.responses.CoctailResponse;
import com.example.coctailapp.responses.DetailCoctailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("filter.php")
    Call<CoctailResponse> getAlcoholicCoctail(@Query("a") String alcohol);


    @GET("lookup.php")
    Call <DetailCoctailResponse> getDetailsCoctail(@Query("i") String idCoctal);

}
