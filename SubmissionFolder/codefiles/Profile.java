package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView EditProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        EditProfile=findViewById(R.id.EditProfile);

        // Set a listener for bottom navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.navigation_home:
                        // Handle the "Home" item click
                        // Start the HomeScreenActivity (or perform other actions).
                        startActivity(new Intent(Profile.this, HomeScreenActivity.class));
                        return true;

                    case R.id.navigation_search:
                        startActivity(new Intent(Profile.this, SearchActivity.class));
                        return true;

                    case R.id.navigation_add:
                        // Handle the "Add" item click
                        // Start the AddItemActivity (or perform other actions).
                        startActivity(new Intent(Profile.this, PostItem.class));
                        return true;

                    case R.id.navigation_chats:
                        startActivity(new Intent(Profile.this, ChatActivity.class));

                        return true;

                    case R.id.profile_navigation:

                        return true;

                    // Add cases for other navigation items here if needed.

                    default:
                        return false;
                }
            }
        });

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(Profile.this, EditProfile.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
    }
}
