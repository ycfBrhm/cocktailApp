package com.example.coctailapp.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.coctailapp.dao.CoctailDao;
import com.example.coctailapp.models.Coctail;

@Database(entities = Coctail.class, version = 1, exportSchema = false)
public abstract class CoctailDatabase extends RoomDatabase {

    private static CoctailDatabase coctailDatabase;

    public static synchronized CoctailDatabase getCoctailDatabase(Context context){
        if(coctailDatabase == null){
            coctailDatabase = Room.databaseBuilder(
                    context,
                    CoctailDatabase.class,
                    "cocktails_db"
            ).build();
        }
        return coctailDatabase;
    }


    public abstract CoctailDao coctailDao();
}
