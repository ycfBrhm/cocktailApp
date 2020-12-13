package com.example.coctailapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.coctailapp.repositories.AlcoholicCoctailRepository;
import com.example.coctailapp.responses.CoctailResponse;

public class AlcoholicCoctailsViewModel extends ViewModel {

    private AlcoholicCoctailRepository alcoholicCoctailRepository;

    public AlcoholicCoctailsViewModel() {
        alcoholicCoctailRepository = new AlcoholicCoctailRepository();
    }

    public LiveData<CoctailResponse> getAlcoholicCoctails(String alcoholic){
        return alcoholicCoctailRepository.getAlcoholicCoctails(alcoholic);
    }

}
