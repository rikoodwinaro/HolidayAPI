package com.example.holidayapi.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.holidayapi.entity.DataPersonalHoliday;
import com.example.holidayapi.entity.DataPersonalHolidayDAO;

@Database(entities = DataPersonalHoliday.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataPersonalHolidayDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase inidb(Context context){
        if (appDatabase==null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dbPersonalHoliday").allowMainThreadQueries().build();
        return appDatabase;
    }
}
