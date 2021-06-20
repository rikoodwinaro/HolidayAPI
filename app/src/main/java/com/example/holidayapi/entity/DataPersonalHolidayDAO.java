package com.example.holidayapi.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface DataPersonalHolidayDAO {
    @Insert
    Long insertData(DataPersonalHoliday dataPersonalHoliday);

    @Query("Select * from personal_holiday")
    List<DataPersonalHoliday> getData();

    @Update
    int updateData(DataPersonalHoliday item);

    @Delete
    void deleteData(DataPersonalHoliday item);
}
