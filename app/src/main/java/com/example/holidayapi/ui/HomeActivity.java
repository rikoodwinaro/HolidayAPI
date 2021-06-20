package com.example.holidayapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.holidayapi.R;
import com.example.holidayapi.data.HolidaysItem;
import com.example.holidayapi.data.service.HolidayApi;
import com.example.holidayapi.data.service.HolidayListener;
import com.example.holidayapi.ui.adapter.HolidayAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    // inisialisasi key pada data yang akan dikirim lewat intent
    private TextView welcome;

    // deklarasi variabel view sesuai dengan layout xml
    private RecyclerView rvHoliday;

    // deklarasi adapter yang telah dibuat
    private HolidayAdapter holidayAdapter;

    //inisialisasi Callback
    private HolidayListener<ArrayList<HolidaysItem>> listHolidayListener = new HolidayListener<ArrayList<HolidaysItem>>() {
        @Override
        public void onSuccess(ArrayList<HolidaysItem> body) {
            //data dimasukkan ke dalam list
            holidayAdapter.setHolidays(body);
        }

        @Override
        public void onFailed(String message) {
            Toast.makeText(HomeActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // mengganti tulisan di action bar (paling atas)
        getSupportActionBar().setTitle("Home");

        // koneksikan variabel dengan view berdasarkan id
        welcome = findViewById(R.id.tv_welcome);

        // koneksikan variabel dengan view berdasarkan id
        rvHoliday =findViewById(R.id.rv_holiday_list);

        //menyambungkan adapter yang telah dibuat ke dalam variabel
        holidayAdapter = new HolidayAdapter();
        // memberi jenis layout yang akan ditampilkan ke view
        rvHoliday.setLayoutManager(new LinearLayoutManager(this));
        //memasukkan adapter ke dalam view
        rvHoliday.setAdapter(holidayAdapter);

        //inisialisasi API
        HolidayApi holidayApi = new HolidayApi();
        //memanggil method getListHoliday untuk request data dengan callback
        holidayApi.getListHoliday(listHolidayListener);

        // menerima kiriman data dari halaman sebelumnya menggunakan kuncinya (EXTRA_USERNAME)
        String username = getIntent().getStringExtra(MainActivity.EXTRA_USERNAME);

        // menampilkan text ke dalam view
        welcome.setText("Welcome, " + username);

        new HolidayApi().getListHoliday(listener);
    }

    HolidayListener<ArrayList<HolidaysItem>> listener = new HolidayListener<ArrayList<HolidaysItem>>() {

        @Override
        public void onSuccess(ArrayList<HolidaysItem> body) {
            for(int i = 0; i < body.size(); i++){
                Log.d("Hasil : NAMA LIBURAN-> ", body.get(i).getCountry());
                Log.d("Hasil : CONFIRMED -> ", String.valueOf(body.get(i).getName()));
            }
        }

        @Override
        public void onFailed(String message) {
            Log.d("ISI ERROR", message);
        }
    };


    // memunculkan tombol yang ada di action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menghubungkan tombol-tombol dengan menu yang telah dibuat sebelumnya
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // membuat aksi pada tombol yang ada di action bar (paling atas)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // identifikasi tombol mana yang diklik berdasarkan id
        switch (item.getItemId()){
            // tombol profile
            case R.id.action_profile:
                // deklarasi intent seperti biasa
                Intent profile = new Intent(this, ProfileActivity.class);
                // memulai pindah halaman
                startActivity(profile);
                break;
            case R.id.action_add :
                // deklarasi intent seperti biasa
                Intent add = new Intent(this, AddActivity.class);
                // memulai pindah halaman
                startActivity(add);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
