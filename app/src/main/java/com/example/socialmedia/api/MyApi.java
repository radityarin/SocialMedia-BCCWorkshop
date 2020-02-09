package com.example.socialmedia.api;

import com.example.socialmedia.model.Content;
import com.example.socialmedia.model.Login;
import com.example.socialmedia.model.User;
import com.example.socialmedia.responses.AllPostResponse;
import com.example.socialmedia.responses.CreatePostResponse;
import com.example.socialmedia.responses.LoginResponse;
import com.example.socialmedia.responses.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApi {

    //USER
    //Register
    @POST("user/register")
    Call<BaseResponse> register(
        @Body
        User user
    );

    //Login
    @POST("user/login")
    Call<LoginResponse> login(
            @Body
            Login login
    );

    //Get all post
    @GET("post")
    Call<AllPostResponse> getAllPost();

    //Create post
    @POST("post")
    Call<CreatePostResponse> createPost(
            @Header("Authorization")
            String authorization,
            @Body
            Content content
    );

    //Delete post
    @DELETE("post/{id}")
    Call<BaseResponse> deletePost(
            @Path("id")
            int id,
            @Header("Authorization")
            String authorization
    );
}
