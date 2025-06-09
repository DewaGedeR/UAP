package com.example.uap;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import api.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private EditText et_nama, et_harga, et_deskripsi;
    private Button btn_save;
    private Plant plant;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item);

        et_nama = findViewById(R.id.et_name);
        et_harga = findViewById(R.id.et_price);
        et_deskripsi = findViewById(R.id.et_deskripsi);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(v -> updatePlant());
    }

    private void updatePlant(){
        String nama = et_nama.getText().toString();
        String harga = et_harga.getText().toString();
        String deskripsi = et_deskripsi.getText().toString();

        Call<Void> call = ApiClient.getApiService().updatePlant(
            plant.getPlant_name(), plant.getPrice(), plant.getDescription()
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateActivity.this, "Berhasil update", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Update", "Gagal: " + t.getMessage());
            }
        });
    }
}
