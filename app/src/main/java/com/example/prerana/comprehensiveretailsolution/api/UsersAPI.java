package com.example.prerana.comprehensiveretailsolution.api;

import com.example.prerana.comprehensiveretailsolution.model.ProfileResponse;
import com.example.prerana.comprehensiveretailsolution.model.User;
import com.example.prerana.comprehensiveretailsolution.serverresponse.ImageResponse;
import com.example.prerana.comprehensiveretailsolution.serverresponse.SignUpResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UsersAPI {

    @POST("users/signup")
    Call<SignUpResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field("emailId") String emailId, @Field("password") String password);

    @POST("users/login")
    Call<SignUpResponse> userLogin(@Body User user);


    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);


    @PUT("users/{id}")
    public Call<User> updateProfile(@Header("Authorization") String token, @Body User user, @Path("id") String id);


    @FormUrlEncoded
    @PUT("users/profiles")
    public Call<ProfileResponse>  updateProfiles(@Header("Authorization") String token, @Field("fullName") String fullName, @Field("gender") String gender, @Field("bloodGroup") String bloodGroup, @Field("country") String country, @Field("contactNumber") String contactNumber, @Field("address") String address, @Field("emailId") String emailId);

    @GET("users/LogoutFragment")
    Call<Void> logout(@Header("Authorization") String token);

}
