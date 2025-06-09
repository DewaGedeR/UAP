package api;

import com.example.uap.Plant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("plant/all")
    Call<List<Plant>> getAllPlants();

    @DELETE("plant/{name}")
    Call<Void> deletePlant(@Path("name") String name);

    @POST("plant/new ")
    @FormUrlEncoded
    Call<Void> addPlant(
        @Field("plant_name") String plant_name,
        @Field("description") String description,
        @Field("price") String price
    );

    @PUT("plant/{name}")
    @FormUrlEncoded
    Call<Void> updatePlant(
            @Field("plant_name") String plant_name,
            @Field("description") String description,
            @Field("price") String price
    );

    @GET("plant/{name}")
    Call<Void> getPlantByName(@Path("name") String name);
}
