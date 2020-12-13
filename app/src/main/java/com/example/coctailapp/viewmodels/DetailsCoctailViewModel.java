package com.example.coctailapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.coctailapp.repositories.DeatilsCoctailRepository;

import com.example.coctailapp.responses.DetailCoctailResponse;

public class DetailsCoctailViewModel extends ViewModel {


    private DeatilsCoctailRepository deatilsCoctailRepository;

    public DetailsCoctailViewModel() {
        deatilsCoctailRepository = new DeatilsCoctailRepository();
    }

    public LiveData<DetailCoctailResponse> getDetailsCoctail(String idCoctail){
        return deatilsCoctailRepository.getDetailsCoctail(idCoctail);
    }
}
