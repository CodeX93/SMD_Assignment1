package com.example.i200778AghaHaider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

public class PostItem extends AppCompatActivity {

    Button PostItem_btn, UploadPhoto,UploadVideo;
    ImageView Back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_item_screen);
PostItem_btn=findViewById(R.id.upload_item_btn);
UploadPhoto=findViewById(R.id.uploadImage);
UploadVideo=findViewById(R.id.uploadvideo);
        Back_btn=findViewById(R.id.backButton);
        // Initialize NavController (if needed for navigation)

        PostItem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(PostItem.this, HomeScreenActivity.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(PostItem.this, HomeScreenActivity.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(PostItem.this, UploadImage.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        UploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(PostItem.this, UploadImage.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });




    }
}
