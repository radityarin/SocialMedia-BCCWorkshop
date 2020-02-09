package com.example.socialmedia.responses;

import com.example.socialmedia.model.Post;

import java.util.ArrayList;

public class AllPostResponse {
    private boolean success;
    private String message;
    private ArrayList<Post> data;

    public AllPostResponse(boolean success, String message, ArrayList<Post> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Post> getData() {
        return data;
    }

    public void setData(ArrayList<Post> data) {
        this.data = data;
    }
}
