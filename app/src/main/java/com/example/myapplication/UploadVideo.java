package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class UploadVideo extends AppCompatActivity {


    ImageView cross_btn;
    Button PhotoModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_camera_screen);
        PhotoModeButton=findViewById(R.id.photoMode);
        cross_btn=findViewById(R.id.crossBtn);

        // Initialize NavController (if needed for navigation)

        cross_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(UploadVideo.this, PostItem.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        PhotoModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(UploadVideo.this, UploadImage.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });





    }
}


