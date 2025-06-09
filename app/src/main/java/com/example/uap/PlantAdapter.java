package com.example.uap;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import api.ApiClient;
import api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    private List<Plant> plantList;
    private Context context;

    public PlantAdapter(Context context, List<Plant> plants){
        this.context = context;
        this.plantList = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_plant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Plant plant = plantList.get(position);
        holder.name.setText(plant.getPlant_name());
        holder.price.setText("Rp" + plant.getPrice());

        holder.btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("plant_name", plant.getPlant_name());
            context.startActivity(intent);
        });

        holder.btnHapus.setOnClickListener(v -> {
            deletePlant(plant.getPlant_name(), position);
        });
    }

    @Override
    public int getItemCount(){
        return plantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView image;
        Button btnDetail, btnHapus;

        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            price = itemView.findViewById(R.id.tv_price);
            image = itemView.findViewById(R.id.img_plant);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            btnHapus = itemView.findViewById(R.id.btn_delete);
        }
    }

    public void deletePlant(String name, int position){
        String encode = Uri.encode(name);
        Call<Void> call = ApiClient.getApiService().deletePlant(encode);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    plantList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, plantList.size());
                }else{
                    Toast.makeText(context, "Gagal menghapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
