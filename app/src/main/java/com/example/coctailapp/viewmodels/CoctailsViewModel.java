package com.example.coctailapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.coctailapp.repositories.CoctailRepository;
import com.example.coctailapp.responses.CoctailResponse;

public class CoctailsViewModel extends ViewModel {

    private CoctailRepository coctailRepository;

    public CoctailsViewModel() {
        coctailRepository = new CoctailRepository();
    }

    public LiveData<CoctailResponse> getAlcoholicCoctails(String alcoholic){
        return coctailRepository.getAlcoholicCoctails(alcoholic);
    }

}
