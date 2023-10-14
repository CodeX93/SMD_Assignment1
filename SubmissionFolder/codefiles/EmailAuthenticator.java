package com.example.i200778AghaHaider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.R;

public class EmailAuthenticator extends AppCompatActivity {


    ImageView Back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_authentication_code_screen);
Back_btn=findViewById(R.id.backButton);
        // Initialize NavController (if needed for navigation)
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(EmailAuthenticator.this, ForgotPassword.class);
                // Start the MainActivity
                startActivity(intent2);
            }
        });





    }
}
