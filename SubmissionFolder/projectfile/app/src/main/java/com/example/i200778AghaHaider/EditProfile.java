package com.example.i200778AghaHaider;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class EditProfile extends AppCompatActivity {
    ImageView back_btn;
    TextView SaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_screen);

        back_btn = findViewById(R.id.backButton);
        SaveChanges = findViewById(R.id.saveChanges);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(EditProfile.this, Profile.class);
                startActivity(intent1);
            }
        });
        SaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(EditProfile.this, Profile.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });


    }
}
