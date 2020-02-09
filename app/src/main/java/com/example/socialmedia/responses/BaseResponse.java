package com.example.socialmedia.responses;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
