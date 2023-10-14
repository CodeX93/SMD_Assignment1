package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set a listener for bottom navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.navigation_home:
                        // Handle the "Home" item click
                        // Start the HomeScreenActivity (or perform other actions).

                        return true;

                    case R.id.navigation_search:
                        startActivity(new Intent(HomeScreenActivity.this, SearchActivity.class));
                        return true;

                    case R.id.navigation_add:
                        // Handle the "Add" item click
                        // Start the AddItemActivity (or perform other actions).
                        startActivity(new Intent(HomeScreenActivity.this, PostItem.class));
                        return true;
                    case R.id.navigation_chats:
                        startActivity(new Intent(HomeScreenActivity.this, ChatActivity.class));
                        return true;
                    case R.id.profile_navigation:
                        // Handle the "Add" item click
                        // Start the AddItemActivity (or perform other actions).
                        startActivity(new Intent(HomeScreenActivity.this, Profile.class));
                        return true;

                    // Add cases for other navigation items here if needed.

                    default:
                        return false;
                }
            }
        });
    }
}
