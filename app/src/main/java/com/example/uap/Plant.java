package com.example.uap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Plant implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("plant_name")
    private String plant_name;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private String price;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public int getId(){
        return id;
    }

    public String getPlant_name(){
        return plant_name;
    }

    public String getDescription(){
        return description;
    }

    public String getPrice(){
        return price;
    }

    public String getCreated_at(){
        return created_at;
    }

    public String getUpdated_at(){
        return updated_at;
    }
}
