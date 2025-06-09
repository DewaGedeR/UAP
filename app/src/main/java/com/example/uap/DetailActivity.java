package com.example.uap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private Button btn_update;
    private TextView tv_name, tv_price, tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        btn_update = findViewById(R.id.btn_update);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_description = findViewById(R.id.tv_deskripsi);

        Plant plant = (Plant) getIntent().getSerializableExtra("plant_data");

        if (plant != null){
            tv_name.setText(plant.getPlant_name());
            tv_price.setText(plant.getPrice());
            tv_description.setText(plant.getDescription());
        }

        btn_update.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, UpdateActivity.class);
            intent.putExtra("plant", plant);
            startActivity(intent);
        });
    }
}
