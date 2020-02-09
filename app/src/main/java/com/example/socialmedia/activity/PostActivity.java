package com.example.socialmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialmedia.R;
import com.example.socialmedia.api.ApiClient;
import com.example.socialmedia.api.MyApi;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.responses.BaseResponse;
import com.example.socialmedia.utils.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private Post post;
    private TextView tv_person_name;
    private TextView tv_content;
    private RecyclerView rv_comment;
    private EditText edtInputComment;
    private Button btnInputComment, btnDeletePost;
    private MyApi myApi;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post = getIntent().getParcelableExtra("post");

        myApi = ApiClient.getClient().create(MyApi.class);
        appPreference = new AppPreference(this);

        tv_person_name = findViewById(R.id.tv_person_name);
        tv_content = findViewById(R.id.tv_content);

        tv_person_name.setText(String.valueOf(post.getId_user()));
        tv_content.setText(post.getContent());

        rv_comment = findViewById(R.id.rv_comment);
        edtInputComment = findViewById(R.id.edt_input_comment);
        btnInputComment = findViewById(R.id.btn_submit_comment);
        btnDeletePost = findViewById(R.id.btn_delete_post);

        btnDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = appPreference.getToken();
                int id_post = post.getId();

                Call<BaseResponse> deleteCall = myApi.deletePost(
                        id_post,
                        "Bearer " + token
                );

                deleteCall.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()){
                            String message = response.body().getMessage();
                            Toast.makeText(PostActivity.this, message, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(PostActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
