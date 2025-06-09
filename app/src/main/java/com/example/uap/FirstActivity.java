package com.example.uap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    private Button btn_login;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.opsi_layout);

        btn_login = findViewById(R.id.btn_login);
        tv_register = findViewById(R.id.opsi_register);

        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        tv_register.setOnClickListener(v -> {
            Intent intent = new Intent(FirstActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
