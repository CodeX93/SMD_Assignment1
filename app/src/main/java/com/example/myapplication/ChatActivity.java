package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.HomeScreenActivity;
import com.example.myapplication.PostItem;
import com.example.myapplication.Profile;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

public class ChatActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);

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
                        startActivity(new Intent(ChatActivity.this, HomeScreenActivity.class));
                        return true;

                    case R.id.navigation_search:
                        // Already in the "Search" activity, no need to navigate to itself.
                        return true;

                    case R.id.navigation_add:
                        // Handle the "Add" item click
                        // Start the AddItemActivity (or perform other actions).
                        startActivity(new Intent(ChatActivity.this, PostItem.class));
                        return true;

                    case R.id.navigation_chats:
                        // Handle the "Chats" item click
                        // Start the ChatsActivity (or perform other actions).
                        // Replace ChatsActivity.class with your actual ChatsActivity class.

                        return true;

                    case R.id.profile_navigation:
                        // Handle the "Profile" item click
                        // Start the ProfileActivity (or perform other actions).
                        startActivity(new Intent(ChatActivity.this, Profile.class));
                        return true;

                    // Add cases for other navigation items here if needed.

                    default:
                        return false;
                }
            }
        });
    }
}
