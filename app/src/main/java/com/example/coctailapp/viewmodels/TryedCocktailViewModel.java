package com.example.coctailapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.coctailapp.database.CoctailDatabase;
import com.example.coctailapp.models.Coctail;

import java.util.List;

import io.reactivex.Completable;
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

    public Completable removeCocktailFromTryedList(Coctail coctail){
        return coctailDatabase.coctailDao().removeFromTryedList(coctail);
    }
}


