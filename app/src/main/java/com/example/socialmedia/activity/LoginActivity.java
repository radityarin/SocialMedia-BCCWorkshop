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
import com.example.socialmedia.model.Login;
import com.example.socialmedia.responses.LoginResponse;
import com.example.socialmedia.utils.AppPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //mendeklarasikan tiap view
    private EditText edtEmail, edtPassword;
    private Button btnSign_in, btnTo_sign_up;
    private String email, password;
    private MyApi myApi;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appPreference = new AppPreference(LoginActivity.this);

        //menghubungkan dengan XML melalui idnya
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSign_in = findViewById(R.id.btn_sign_in);
        btnTo_sign_up = findViewById(R.id.btn_to_sign_up);

        //mengfungsikan click di button
        btnSign_in.setOnClickListener(this);
        btnTo_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                loginUser();
                break;
            case R.id.btn_to_sign_up:
                Intent intent_toSignUp = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_toSignUp);
                break;
        }
    }

    private void loginUser() {
        // ngambil text dari edit text
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();

        Login login = new Login(email, password);

        myApi = ApiClient.getClient().create(MyApi.class);

        Call<LoginResponse> loginCall = myApi.login(login);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    String token = response.body().getToken();

                    Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();

                    appPreference.saveToken(token);

                    //untuk berpindah activity dari parameter kiri ke parameter kanan
                    Intent intent_signin = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent_signin);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (appPreference.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}