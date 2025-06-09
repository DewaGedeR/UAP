package model;

import com.example.uap.Plant;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlantsResponse {
    @SerializedName("data")
    private List<Plant> data;

    @SerializedName("status")
    private boolean status;

    public List<Plant> getData(){
        return data;
    }

    public boolean isStatus(){
        return status;
    }
}
