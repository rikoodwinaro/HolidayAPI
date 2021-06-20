package com.example.holidayapi.data.service;


public interface HolidayListener <T> {
    void onSuccess(T body);
    void onFailed(String message);
    // membuat interface sebagai callback / jembatan
    // yang mengantarkan data dari pengambilan data (HolidayApi) kepada menampilkan data (Activity)

}
