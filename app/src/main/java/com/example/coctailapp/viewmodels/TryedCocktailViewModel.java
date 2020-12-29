package com.example.coctailapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.coctailapp.database.CoctailDatabase;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.repositories.AlcoholicCoctailRepository;
import com.example.coctailapp.responses.CoctailResponse;

import java.util.List;

import io.reactivex.Flowable;

public class TryedCocktailViewModel extends AndroidViewModel {

    private CoctailDatabase coctailDatabase;

    public TryedCocktailViewModel(@NonNull Application application){
        super(application);
        coctailDatabase = CoctailDatabase.getCoctailDatabase(application);
    }

    public Flowable<List<Coctail>> loadTryedList(){
        return coctailDatabase.coctailDao().getTryedList();
    }
}


