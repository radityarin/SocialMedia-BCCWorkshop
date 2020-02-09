package com.example.socialmedia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.socialmedia.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //mendeklarasikan tiap view
    private EditText edtEmail, edtPassword;
    private Button btnSign_in, btnTo_sign_up;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                // ngambil text dari edit text
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                //untuk berpindah activity dari parameter kiri ke parameter kanan
                Intent intent_signin = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent_signin);
                break;
            case R.id.btn_to_sign_up:
                Intent intent_toSignUp = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent_toSignUp);
                break;
        }
    }
}