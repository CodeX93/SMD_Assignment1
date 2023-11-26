package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    RecyclerView MyItem_RecycleView, RentItem_RecycleView;
    private RecycleViewAdapter MyItem_RecycleViewAdapter, RentItem_RecycleViewAdapter;
    BottomNavigationView bottomNavigationView;
    ImageView EditProfile;
    ArrayList<Item> myItemList, rentedItemList;
    TextView ProfileName, Location, ItemCount, RentCount;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);
        Intent intent = getIntent();
        userID = intent.getStringExtra("UserID");

        // Initialize UI components
        initializeUI();

        // Fetch items from Web API
        new FetchItemsTask().execute("http://192.168.18.18/spot-it_user/fetchitems.php");

        setupBottomNavigation();
    }

    private void initializeUI() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        EditProfile = findViewById(R.id.EditProfile);
        ProfileName = findViewById(R.id.ProfileName);
        Location = findViewById(R.id.Location);
        ItemCount = findViewById(R.id.TotalItemsCount);
        RentCount = findViewById(R.id.TotalRentedCount);
        MyItem_RecycleView = findViewById(R.id.MyAllItems_RecyclerView);
        RentItem_RecycleView = findViewById(R.id.RentedItems_RecyclerView);

        myItemList = new ArrayList<>();
        rentedItemList = new ArrayList<>();

        setupRecyclerViews();
    }

    private void setupRecyclerViews() {
        MyItem_RecycleView.setHasFixedSize(true);
        RentItem_RecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        MyItem_RecycleView.setLayoutManager(layoutManager1);
        RentItem_RecycleView.setLayoutManager(layoutManager2);

        MyItem_RecycleViewAdapter = new RecycleViewAdapter(Profile.this, myItemList);
        RentItem_RecycleViewAdapter = new RecycleViewAdapter(Profile.this, rentedItemList);
        MyItem_RecycleView.setAdapter(MyItem_RecycleViewAdapter);
        RentItem_RecycleView.setAdapter(RentItem_RecycleViewAdapter);
    }

    private void setupBottomNavigation() {
        // Set a listener for bottom navigation item clicks
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.navigation_home:
                    startActivity(new Intent(Profile.this, HomeScreenActivity.class));
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(Profile.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(Profile.this, PostItem.class));
                    return true;
                case R.id.navigation_chats:
                    startActivity(new Intent(Profile.this, ChatActivity.class));
                    return true;
                case R.id.profile_navigation:
                    return true;
                default:
                    return false;
            }
        });

        EditProfile.setOnClickListener(view -> startActivity(new Intent(Profile.this, EditProfile.class)));
    }

    private class FetchItemsTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Item item = new Item(
                                jsonObject.getString("Name"),
                                jsonObject.getString("Description"),
                                jsonObject.getString("City"),
                                jsonObject.getDouble("Rate"),
                                jsonObject.getString("ImageURI")
                        );
                        myItemList.add(item); // Populate the list with items
                        rentedItemList.add(item);
                    }
                    MyItem_RecycleViewAdapter.notifyDataSetChanged();
                    RentItem_RecycleViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Handle error
            }
        }
    }
}
