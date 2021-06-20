package com.example.holidayapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.holidayapi.R;
import com.example.holidayapi.entity.AppDatabase;
import com.example.holidayapi.entity.DataPersonalHoliday;
import com.example.holidayapi.ui.adapter.AddAdapter;

import java.util.List;

public class AddActivity extends AppCompatActivity implements AddContact.view {

    private AppDatabase appDatabase;
    private AddPresenter addPresenter;
    private AddAdapter addAdapter;

    private Button submit;
    private RecyclerView recyclerView;
    private EditText etName, etDate;

    private int id = 0;
    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        submit = findViewById(R.id.submit);
        etName = findViewById(R.id.etHolidayName);
        etDate = findViewById(R.id.etHolidayDate);
        recyclerView = findViewById(R.id.rv_add);

        appDatabase = AppDatabase.inidb(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addPresenter = new AddPresenter(this);
        addPresenter.readData(appDatabase);
        submit.setOnClickListener(this);
    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        addPresenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil Menghapus Data", Toast.LENGTH_SHORT).show();
        addPresenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etName.setText("");
        etDate.setText("");
        submit.setText("Submit");
    }

    @Override
    public void getData(List<DataPersonalHoliday> list) {
        addAdapter = new AddAdapter(this, list, this);
        recyclerView.setAdapter(addAdapter);
    }

    @Override
    public void editData(DataPersonalHoliday item) {
        etName.setText(item.getName());
        etDate.setText(item.getDate());
        id = item.getId();
        edit = true;
        submit.setText("EDIT DATA");
    }

    @Override
    public void deleteData(DataPersonalHoliday item) {
        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        addPresenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_menu_delete)
                .show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit){
            if (etName.getText().toString().equals("") || etDate.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Harap isi secara lengkap!", Toast.LENGTH_SHORT).show();
            } else {
                if (!edit){
                    addPresenter.insertData(etName.getText().toString(), etDate.getText().toString(), appDatabase);
                } else {
                    addPresenter.editData(etName.getText().toString(), etDate.getText().toString(), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            //tombol back pada action bar
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}