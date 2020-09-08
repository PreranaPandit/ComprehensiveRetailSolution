package com.example.prerana.comprehensiveretailsolution.api;

import com.example.prerana.comprehensiveretailsolution.model.AddSales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddSalesAPI {

    @POST("addsales/addingSales")
    Call<AddSales> addingSales(@Header("Authorization")String token, @Body AddSales addSales);

//    @GET("users/me")
//    Call<User> getUserDetails(@Header("Authorization")String token);
//
//
//    @PUT("users/{id}")
//    public Call<User> updateProfile(@Header("Authorization") String token, @Body User user, @Path("id") String id);


    @GET("addsales/addingSales")
    Call<List<AddSales>> getAllSales();

    @GET("addsales/{salesStatus}/search")
    Call<List<AddSales>> searchSalesStatus(@Header("Authorization") String token, @Path("salesStatus") String salesStatus);


//    @PUT("users/{id}")
//    public Call<User> updateProfile(@Header("Authorization") String token, @Body User user, @Path("id") String id);


    @DELETE("addsales/deleteSales/{id}")
    public Call<AddSales> deleteSales(@Header("Authorization") String token, @Path("id") String id);

}
