package com.example.holidayapi.data.service;

import com.example.holidayapi.data.Holiday;
import com.example.holidayapi.data.HolidayListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HolidayApiInterface {
    // request ambil data dari API dengan link tertentu
    // GET sebagai metode akses yaitu mengambil data
    // Call untuk memanggil object untuk menampung data yang diambil
    // Path untuk membuat link menjadi dinamis
    @GET("v1/holidays?pretty&key=d5dcd3d5-8c3e-48eb-88df-75bbcb4ea2ca&country=ID&year=2020")
    Call<Holiday> getListHoliday();

}
