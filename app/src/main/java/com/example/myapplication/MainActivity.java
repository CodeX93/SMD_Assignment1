package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button loginBtn,signup_btn;
    TextView forgotpassword;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        // Initialize NavController (if needed for navigation)


        loginBtn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.signup_btn);
        forgotpassword=findViewById(R.id.forgotpassword);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the SignupScreen using the navigate action
                Intent intent1 = new Intent(MainActivity.this, HomeScreenActivity.class);
                startActivity(intent1);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the SignupScreen using the navigate action
                Intent intent1 = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent1);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the SignupScreen using the navigate action
                Intent intent1 = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent1);
            }
        });

    }
}
