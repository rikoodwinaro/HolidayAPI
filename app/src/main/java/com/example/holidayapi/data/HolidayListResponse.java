package com.example.holidayapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HolidayListResponse {
    //SerializedName digunakan untuk menyamakan nama variabel di API dengan yang dibuat
    @SerializedName("results")
    private ArrayList<HolidaysItem> results;

    public HolidayListResponse(ArrayList<HolidaysItem> results){
        this.results = results;
    }

    public ArrayList<HolidaysItem> getResults() {
        return results;
    }
}
