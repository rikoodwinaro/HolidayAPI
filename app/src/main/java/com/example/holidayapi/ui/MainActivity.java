package com.example.holidayapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.holidayapi.R;
import com.example.holidayapi.data.service.HolidayApi;
import com.example.holidayapi.data.service.HolidayApiInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //inisialisasi key pada data yang akan dikirim lewat intent
    public static final String EXTRA_USERNAME =  "USERNAME";

    // deklarasi variabel view sesuai dengan layout xml
    EditText inputUsername, inputPassword;
    Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mengganti tulisan di action bar (Paling atas)
        getSupportActionBar().setTitle("Login");

        // koneksikan variabel dengan view berdasarkan id
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch ((v.getId())){
            case R.id.btn_login:
                login();
                break;

            case R.id.btn_signup:
                signup();
                break;
        }

    }

    private void signup() {
        // menampilkan notif
        Toast.makeText(this, "you have been registered", Toast.LENGTH_SHORT).show();
    }

    private void login() {
        // mengambil nilai string pada text input di view
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        //memeriksa input kosong atau tidak, jika kosong maka error "username couldn't be empty"
        if (username.isEmpty()) inputUsername.setError("Username couldn't be empty");
        else if (password.isEmpty()) inputPassword.setError("Password couldn't be empty");

        else {
            if (username.equals("admin") && password.equals("admin")) {
                //deklarasi intent untuk pindah ke halaman HomeActivity
                Intent intent = new Intent(this, HomeActivity.class);
                //memasukkan data yang akan dikirimkan dengan nama kunci EXTRA_USERNAME
                intent.putExtra(EXTRA_USERNAME, username);
                //memulai pindah halaman
                startActivity(intent);
                //agar tidak kembali ke halaman ini, kita tambahkan
                finish();
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
