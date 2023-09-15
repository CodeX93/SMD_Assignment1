package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {
    Button resetPassword,GoBackToLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_screen);

        resetPassword = findViewById(R.id.resetPassword);
        GoBackToLogin = findViewById(R.id.gobacktoLogin);

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ForgotPassword.this, EmailAuthenticator.class);
                startActivity(intent1);
            }
        });
        GoBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(ForgotPassword.this, MainActivity.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });


    }
}
