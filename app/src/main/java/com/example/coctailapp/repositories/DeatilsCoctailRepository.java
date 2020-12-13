package com.example.coctailapp.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.coctailapp.network.ApiClient;
import com.example.coctailapp.network.ApiService;
import com.example.coctailapp.responses.CoctailResponse;
import com.example.coctailapp.responses.DetailCoctailResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeatilsCoctailRepository {

    private ApiService apiService;

    public DeatilsCoctailRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<DetailCoctailResponse> getDetailsCoctail(String idCoctail) {
        MutableLiveData<DetailCoctailResponse> data = new MutableLiveData<>();
        apiService.getDetailsCoctail(idCoctail).enqueue(new Callback<DetailCoctailResponse>() {
            @Override
            public void onResponse(@NonNull Call<DetailCoctailResponse> call, @NonNull Response<DetailCoctailResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<DetailCoctailResponse> call,@NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }


}
