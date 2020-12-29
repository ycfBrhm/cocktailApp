package com.example.coctailapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.coctailapp.database.CoctailDatabase;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.repositories.DeatilsCoctailRepository;

import com.example.coctailapp.responses.DetailCoctailResponse;

import io.reactivex.Completable;

public class DetailsCoctailViewModel extends AndroidViewModel {


    private DeatilsCoctailRepository deatilsCoctailRepository;
    private CoctailDatabase coctailDatabase;

    public DetailsCoctailViewModel(@NonNull Application application) {
        super(application);
        deatilsCoctailRepository = new DeatilsCoctailRepository();
        coctailDatabase = CoctailDatabase.getCoctailDatabase(application);
    }

    public LiveData<DetailCoctailResponse> getDetailsCoctail(String idCoctail){
        return deatilsCoctailRepository.getDetailsCoctail(idCoctail);
    }

    public Completable addToTryedList(Coctail coctail){
        return coctailDatabase.coctailDao().addToTryedList(coctail);
    }
}
