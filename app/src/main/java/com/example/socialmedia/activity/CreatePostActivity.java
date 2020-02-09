package com.example.socialmedia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.socialmedia.R;

public class CreatePostActivity extends AppCompatActivity {

    private EditText edtInputContent;
    private String inputContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        edtInputContent = findViewById(R.id.edt_input_content);
        inputContent = edtInputContent.getText().toString();
    }

    public void submitPost(View view) {
        Intent intent = new Intent(CreatePostActivity.this, MainActivity.class);
        startActivity(intent);
    }
}