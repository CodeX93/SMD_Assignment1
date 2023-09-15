package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Report extends AppCompatActivity {


    ImageView Back_btn;
    Button Submitreport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_report_screen);
        Back_btn = findViewById(R.id.backBtn);

        Submitreport = findViewById(R.id.SubmitReport);

        // Initialize NavController (if needed for navigation)

        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(Report.this, ItemScreen.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        Submitreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(Report.this, ItemScreen.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
    }
}

