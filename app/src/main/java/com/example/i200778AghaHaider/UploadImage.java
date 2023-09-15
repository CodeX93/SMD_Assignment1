package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UploadImage extends AppCompatActivity {


    ImageView cross_btn;
    Button videoModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_camera_screen);
        videoModeButton=findViewById(R.id.videoModeButton);
        cross_btn=findViewById(R.id.crossBtn);

        // Initialize NavController (if needed for navigation)

        cross_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(UploadImage.this, PostItem.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        videoModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(UploadImage.this, UploadVideo.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });





    }
}


