package com.example.prerana.comprehensiveretailsolution.api;

import com.example.prerana.comprehensiveretailsolution.model.Purchases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseAPI {

    @POST("purchases/addingPurchases")
    Call<Purchases> addingPurchases(@Header("Authorization")String token, @Body Purchases purchases);

//    @GET("users/me")
//    Call<User> getUserDetails(@Header("Authorization")String token);
//
//
//    @PUT("users/{id}")
//    public Call<User> updateProfile(@Header("Authorization") String token, @Body User user, @Path("id") String id);


    @GET("purchases/addingPurchases")
    Call<List<Purchases>> getAllPurchases();

    @GET("purchases/{supplierStatus}/search")
    Call<List<Purchases>> searchPurchaseStatus(@Header("Authorization") String token, @Path("supplierStatus") String supplierStatus);


//    @PUT("users/{id}")
//    public Call<User> updateProfile(@Header("Authorization") String token, @Body User user, @Path("id") String id);


    @DELETE("purchases/deletePurchases/{id}")
    public Call<Purchases> deletePurchases(@Header("Authorization") String token, @Path("id") String id);


}
