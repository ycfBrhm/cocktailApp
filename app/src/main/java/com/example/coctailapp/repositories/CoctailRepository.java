package com.example.coctailapp.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coctailapp.network.ApiClient;
import com.example.coctailapp.network.ApiService;
import com.example.coctailapp.responses.CoctailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoctailRepository {

    private ApiService apiService;

    public CoctailRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<CoctailResponse> getAlcoholicCoctails(String alcoholic) {
        MutableLiveData<CoctailResponse> data = new MutableLiveData<>();
        apiService.getAlcoholicCoctail(alcoholic).enqueue(new Callback<CoctailResponse>() {
            @Override
            public void onResponse(@NonNull Call<CoctailResponse> call,@NonNull Response<CoctailResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CoctailResponse> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
