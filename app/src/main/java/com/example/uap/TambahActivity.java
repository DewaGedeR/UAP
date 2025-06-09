package com.example.uap;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import androidx.appcompat.app.AppCompatActivity;

import api.ApiClient;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    private EditText et_nama, et_harga, et_deskripsi;
    private Button btn_add;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_item);

        et_nama = findViewById(R.id.et_name);
        et_harga = findViewById(R.id.et_price);
        et_deskripsi = findViewById(R.id.et_deskripsi);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(v -> addPlant());
    }

    private void addPlant(){
        String name = et_nama.getText().toString();
        String price = et_harga.getText().toString();
        String description = et_deskripsi.getText().toString();

        if (name.isEmpty() || price.isEmpty() || description.isEmpty()){
            Toast.makeText(TambahActivity.this, "Tidak boleh ada kolom kosong", Toast.LENGTH_SHORT).show();
        }else{
            Call<Void> call =
                    ApiClient.getApiService().addPlant(name, price, description);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            Toast.makeText(TambahActivity.this, "Berhasil menambahakan", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if (response.body() != null){
                            Log.e("", "onFailure: " + response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("Error Retrofit", "onFailure: " + t.getMessage());
                }
            });
        }
    }
}
