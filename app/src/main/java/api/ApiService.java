package api;

import com.example.uap.Plant;

import java.util.List;

import model.PlantsResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("plant/all")
    Call<PlantsResponse> getAllPlants(@Query("plant_name") String plant_name);

    @DELETE("plant/{plant_name}")
    Call<Void> deletePlant(@Path("plant_name") String name);

    @POST("plant/new")
    @FormUrlEncoded
    Call<Void> addPlant(
        @Field("plant_name") String plant_name,
        @Field("description") String description,
        @Field("price") String price
    );

    @PUT("plant/{plant_name}")
    @FormUrlEncoded
    Call<Void> updatePlant(
            @Field("plant_name") String plant_name,
            @Field("description") String description,
            @Field("price") String price
    );

    @GET("plant/{plant_name}")
    Call<Plant> getPlantByName(@Path("plant_name") String name);
}
