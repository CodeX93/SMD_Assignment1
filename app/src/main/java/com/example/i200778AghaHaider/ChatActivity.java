package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class ChatActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    LinearLayout chat1,chat2,chat3,chat4,chat5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        chat1=findViewById(R.id.chat1);
        chat2=findViewById(R.id.chat2);
        chat3=findViewById(R.id.chat3);
        chat4=findViewById(R.id.chat4);
        chat5=findViewById(R.id.chat5);
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
                        startActivity(new Intent(ChatActivity.this, SearchActivity.class));
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

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ChatActivity.this, ConversationActivity.class);
                startActivity(intent1);
            }
        });

        chat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ChatActivity.this, ConversationActivity.class);
                startActivity(intent1);
            }
        });

        chat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ChatActivity.this, ConversationActivity.class);
                startActivity(intent1);
            }
        });

        chat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ChatActivity.this, ConversationActivity.class);
                startActivity(intent1);
            }
        });
        chat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ChatActivity.this, ConversationActivity.class);
                startActivity(intent1);
            }
        });
        chat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                // Start the MainActivity
                Intent intent1 = new Intent(ChatActivity.this, ConversationActivity.class);
                startActivity(intent1);
            }
        });


    }
}
