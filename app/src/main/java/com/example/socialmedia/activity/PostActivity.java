package com.example.socialmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.socialmedia.R;
import com.example.socialmedia.model.Post;

public class PostActivity extends AppCompatActivity {

    private Post post;
    private TextView tv_person_name;
    private TextView tv_content;
    private RecyclerView rv_comment;
    private EditText edtInputComment;
    private Button btnInputComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post = getIntent().getParcelableExtra("post");

        tv_person_name = findViewById(R.id.tv_person_name);
        tv_content = findViewById(R.id.tv_content);

        tv_person_name.setText(String.valueOf(post.getId_user()));
        tv_content.setText(post.getContent());

        rv_comment = findViewById(R.id.rv_comment);
        edtInputComment = findViewById(R.id.edt_input_comment);
        btnInputComment = findViewById(R.id.btn_submit_comment);
    }
}
