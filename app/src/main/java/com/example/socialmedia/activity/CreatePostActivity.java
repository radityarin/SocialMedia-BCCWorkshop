package com.example.socialmedia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialmedia.R;
import com.example.socialmedia.api.ApiClient;
import com.example.socialmedia.api.MyApi;
import com.example.socialmedia.model.Content;
import com.example.socialmedia.responses.CreatePostResponse;
import com.example.socialmedia.utils.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity {

    private EditText edtInputContent;
    private String inputContent;
    private MyApi myApi;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        edtInputContent = findViewById(R.id.edt_input_content);
        inputContent = edtInputContent.getText().toString();

        myApi = ApiClient.getClient().create(MyApi.class);
        appPreference = new AppPreference(this);
    }

    public void submitPost(View view) {

        inputContent = edtInputContent.getText().toString();

        Content content = new Content(inputContent);

        String token = appPreference.getToken();

        Call<CreatePostResponse> createPostCall = myApi.createPost("Bearer " + token, content);

        createPostCall.enqueue(new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                if (response.isSuccessful()){
                    String message = response.body().getMessage();
                    Toast.makeText(CreatePostActivity.this, message, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreatePostActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreatePostActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                Toast.makeText(CreatePostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}