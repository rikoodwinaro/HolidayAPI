package com.example.holidayapi.ui;

import android.view.View;

import com.example.holidayapi.entity.AppDatabase;
import com.example.holidayapi.entity.DataPersonalHoliday;

import java.util.List;

public interface AddContact {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataPersonalHoliday> list);
        void editData(DataPersonalHoliday item);
        void deleteData(DataPersonalHoliday item);
    }

    interface presenter{
        void insertData(String name , String date, AppDatabase database);
        void editData(String name, String date, int id, AppDatabase database);
        void deleteData(DataPersonalHoliday dataPersonalHoliday, AppDatabase appDatabase);
    }
}
