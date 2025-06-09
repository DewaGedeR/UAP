package com.example.uap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Api;

import api.ApiClient;
import api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private Button btn_update;
    private TextView tv_name, tv_price, tv_description;
    private Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        btn_update = findViewById(R.id.btn_update);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_description = findViewById(R.id.tv_deskripsi);

        String encodedName = getIntent().getStringExtra("plant_name");
        String plantName = Uri.decode(encodedName);

        ApiService apiService = ApiClient.getApiService();

        Call<Plant> call = apiService.getPlantByName(plantName);
        call.enqueue(new Callback<Plant>() {
            @Override
            public void onResponse(Call<Plant> call, Response<Plant> response) {
                if (response.isSuccessful() && response.body() != null){
                    plant = response.body();
                    tv_name.setText(plant.getPlant_name());
                    tv_price.setText("Rp" + plant.getPrice());
                    tv_description.setText(plant.getDescription());
                }else{
                    Toast.makeText(DetailActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Plant> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
            intent.putExtra("plant_name", plant.getPlant_name());
            startActivity(intent);
        });
    }
}
