package com.example.prerana.comprehensiveretailsolution.bll;

import android.util.Log;

import com.example.prerana.comprehensiveretailsolution.api.UsersAPI;
import com.example.prerana.comprehensiveretailsolution.model.User;
import com.example.prerana.comprehensiveretailsolution.serverresponse.SignUpResponse;
import com.example.prerana.comprehensiveretailsolution.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    private String emailAddress;
    private String password;


    boolean isSuccess = false;

    public LoginBLL() {
    }

    public LoginBLL(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public boolean checkUser(String emailId, String password) {

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);

        Call<SignUpResponse> usersCall = usersAPI.checkUser(emailId, password);


        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
//
    public boolean checkUsers() {

        UsersAPI userInterface = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> logincall = userInterface.userLogin(new User(emailAddress, password));

        try {
            Response<SignUpResponse> userResponse = logincall.execute();
            if (userResponse.isSuccessful()) {
                if (userResponse.body().isSuccess()) {
                    Url.Cookie = userResponse.headers().get("Set-Cookie");
                    isSuccess = true;
                }
            } else {
                Log.d("Wrong", "credentials");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    }
