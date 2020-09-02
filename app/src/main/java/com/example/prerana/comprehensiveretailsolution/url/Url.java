package com.example.prerana.comprehensiveretailsolution.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {
    //home ko ip
   // public static final String base_url = "http://172.26.1.210:3000/";

    public static final String base_url = "http://192.168.0.107:3000/";

//192.168.0.105
     //emulator ko lagi ip
    //public static final String base_url = "http://10.0.2.2:3000/";

    //pasal ko ip
    //public static final String base_url = "http://192.168.1.112:3000/";


   // public static final String base_url = "http://172.100.100.5:3000/";

    //college ko ip
   // public static final String base_url = "http://192.168.0.106:3000/";
    //  public static final String base_url = "http://10.0.2.2:3000/";
    public static String token = "Bearer ";
    public static String imagePath = base_url + "uploads/" ;
    public static String Cookie="";
    public static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}