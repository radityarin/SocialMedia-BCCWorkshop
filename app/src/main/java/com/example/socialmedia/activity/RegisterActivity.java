package com.example.socialmedia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.socialmedia.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //mendeklarasikan tiap view
    private EditText edtName, edtEmail, edtPassword;
    private Button btnSign_Up, btnTo_sign_in;
    private String name, email, password;

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
                // ngambil text dari edit text
                name = edtName.getText().toString();
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                //untuk berpindah activity dari parameter kiri ke parameter kanan
                Intent intent_signup = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent_signup);
                finishAffinity();
                break;
            case R.id.btn_to_sign_in:
                Intent intent_toSignIn = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent_toSignIn);
                finishAffinity();
                break;
        }
    }
}
