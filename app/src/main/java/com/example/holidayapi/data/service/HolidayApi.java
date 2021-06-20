package com.example.holidayapi.data.service;

import android.util.Log;

import com.example.holidayapi.data.Holiday;
import com.example.holidayapi.data.HolidayListResponse;
import com.example.holidayapi.data.HolidaysItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HolidayApi {
    // membuat variabel yang menampung url dari API yang digunakan
    public static final String BASE_URL = "https://holidayapi.com/";

    //inisialisasi retrofit sebagai library untuk mengakses API
    private Retrofit retrofit = null;

    // fungsi untuk membuat retrofit dan mengkoneksikan dengan request yang ada di HolidayApiInterface
    HolidayApiInterface getHolidayApi() {
        Log.d("insialisasi", "getHolidayApi: ");
        if (retrofit == null) {
            //membangun atau instansiasi data retrofit
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(HolidayApiInterface.class);
    }

    //mengambil list movie dari API
    public void getListHoliday(final HolidayListener<ArrayList<HolidaysItem>> listener) {
        //eksekusi pemanggilan request ke API
        getHolidayApi().getListHoliday().enqueue(new Callback<Holiday>() {
            //ketika berhasil mengambil data
            @Override
            public void onResponse(Call<Holiday> call, Response<Holiday> response) {
                try {
                    // menyimpan hasil pengambilan data
                    Holiday holidayResponse = response.body();
                    Log.d("TAG", "sebelum IF");
                    if (holidayResponse != null){
                        //menyimpan list data dari nested data yang berasal dari API
                        ArrayList<HolidaysItem> listHoliday =  holidayResponse.getHolidays();
                        //mengirimkan data melalui Callback/jembatan listener
                        listener.onSuccess(listHoliday);
                    }
                } catch (Exception e){
                    // mengirimkan pesan error melalui callback / jembatan listener
                    Log.d("TAG", "gagal");
                    listener.onFailed(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Holiday> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }

            //ketika gagal atau error pada saat mengambil data

        });
    }
}
