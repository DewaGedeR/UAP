package com.example.uap;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import api.ApiClient;
import api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_addList;
    private RecyclerView recyclerView;
    private PlantAdapter plantAdapter;
    private List<Plant> plantList = new ArrayList<>();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        btn_addList = findViewById(R.id.btn_addList);

        btn_addList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TambahActivity.class);
            startActivity(intent);
        });

        plantAdapter = new PlantAdapter(MainActivity.this, plantList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(plantAdapter);
    }

    public void fetchPlants(){
        Call<List<Plant>> call = apiService.getAllPlants();
        call.enqueue(new Callback<List<Plant>>() {
            @Override
            public void onResponse(Call<List<Plant>> call, Response<List<Plant>> response) {
                if (response.isSuccessful() && response.body() != null){
                    plantList.clear();
                    plantList.addAll(response.body());
                    plantAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Gagal memuat data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Plant>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deletePlant(String name, int position){
        String encode = Uri.encode(name);

        Call<Void> call = apiService.deletePlant(encode);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    plantList.remove(position);
                    plantAdapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity.this, "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}