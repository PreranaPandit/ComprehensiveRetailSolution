package com.example.prerana.comprehensiveretailsolution.serverresponse;

public class SignUpResponse {

    private String status;
    private String token;

    private boolean success;

    public SignUpResponse(String status, String token) {
        this.status = status;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }
}
