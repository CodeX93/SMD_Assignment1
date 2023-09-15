package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConversationActivity extends AppCompatActivity {
    ImageView backButton,cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_screen);

        backButton = findViewById(R.id.backButton);
        cameraButton=findViewById(R.id.cameraButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ConversationActivity.this, ChatActivity.class);
                startActivity(intent1);
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ConversationActivity.this, UploadImage.class);
                startActivity(intent1);
            }
        });
    }
}