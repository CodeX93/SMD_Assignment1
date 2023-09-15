package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.HomeScreenActivity;
import com.example.myapplication.PostItem;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SearchActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    RelativeLayout SearchItem1,SearchItem2,SearchItem3;
    LinearLayout PopularItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
SearchItem1=findViewById(R.id.searchItem1);
        SearchItem2=findViewById(R.id.searchItem2);
        SearchItem3=findViewById(R.id.searchItem3);
        PopularItems=findViewById(R.id.PopularItems);
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
                        startActivity(new Intent(SearchActivity.this, HomeScreenActivity.class));
                        return true;

                    case R.id.navigation_search:
                        // Already in the "Search" activity, no need to navigate to itself.
                        return true;

                    case R.id.navigation_add:
                        // Handle the "Add" item click
                        // Start the AddItemActivity (or perform other actions).
                        startActivity(new Intent(SearchActivity.this, PostItem.class));
                        return true;

                    // Add cases for other navigation items here if needed.

                    default:
                        return false;
                }
            }
        });

        SearchItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(SearchActivity.this, ItemScreen.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        SearchItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(SearchActivity.this, ItemScreen.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        SearchItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(SearchActivity.this, ItemScreen.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        PopularItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(SearchActivity.this, ItemScreen.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });



    }
}
