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
import com.example.socialmedia.model.User;
import com.example.socialmedia.responses.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //mendeklarasikan tiap view
    private EditText edtName, edtEmail, edtPassword;
    private Button btnSign_Up, btnTo_sign_in;
    private String name, email, password;
    private MyApi myApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //menghubungkan dengan XML melalui idnya
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSign_Up = findViewById(R.id.btn_sign_up);
        btnTo_sign_in = findViewById(R.id.btn_to_sign_in);

        //mengfungsikan click di button
        btnSign_Up.setOnClickListener(this);
        btnTo_sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_up:
                registerUser();
                break;
            case R.id.btn_to_sign_in:
                Intent intent_toSignIn = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent_toSignIn);
                finishAffinity();
                break;
        }
    }

    private void registerUser() {
        // ngambil text dari edit text
        name = edtName.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();

        User user = new User(name, email, password);

        myApi = ApiClient.getClient().create(MyApi.class);

        Call<BaseResponse> registerCall = myApi.register(user);

        registerCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()){
                    String message = response.body().getMessage();

                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                    //untuk berpindah activity dari parameter kiri ke parameter kanan
                    Intent intent_signup = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent_signup);
                    finish();

                } else {
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
