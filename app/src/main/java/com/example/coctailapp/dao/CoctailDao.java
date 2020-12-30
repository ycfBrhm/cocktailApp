package com.example.coctailapp.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.coctailapp.models.Coctail;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface CoctailDao {

    @Query("SELECT * FROM coctails")
    Flowable<List<Coctail>> getTryedList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToTryedList(Coctail coctail);

    @Delete
    Completable removeFromTryedList(Coctail coctail);

    @Query("SELECT * FROM COCTAILS WHERE id = :coctailId")
    Flowable<Coctail> getCoctailFromTryedList(String coctailId);
}
