package com.example.i200778AghaHaider;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.checkerframework.checker.units.qual.C;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3;
    TextView logined_User;

    private RecycleViewAdapter recycleViewAdapter, recycleViewAdapter2, recycleViewAdapter3;
    private SharedPreferences sharedPreferences;
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private ArrayList<Item> itemArrayList, myItemList, recentItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        sharedPreferences = getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        if (!isLoggedIn) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        logined_User = findViewById(R.id.LoginUser);
        recyclerView1 = findViewById(R.id.FeatureProduct_recyclerview);
        recyclerView2 = findViewById(R.id.MyItems_recyclerview);
        recyclerView3 = findViewById(R.id.RecentItem_recyclerview);

        itemArrayList = new ArrayList<>();
        myItemList = new ArrayList<>();
        recentItemList = new ArrayList<>();

        setupRecyclerViews();

        new FetchItemsTask().execute();

        setupBottomNavigationView();
    }

    private void setupRecyclerViews() {
        recyclerView1.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView3.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView1.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView3.setLayoutManager(layoutManager3);

        recycleViewAdapter = new RecycleViewAdapter(HomeScreenActivity.this, itemArrayList);
        recycleViewAdapter2 = new RecycleViewAdapter(HomeScreenActivity.this, myItemList);
        recycleViewAdapter3 = new RecycleViewAdapter(HomeScreenActivity.this, recentItemList);

        recyclerView1.setAdapter(recycleViewAdapter);
        recyclerView2.setAdapter(recycleViewAdapter2);
        recyclerView3.setAdapter(recycleViewAdapter3);
    }

    private void setupBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_search:
                    startActivity(new Intent(HomeScreenActivity.this, SearchActivity.class));
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(HomeScreenActivity.this, PostItem.class));
                    return true;
                case R.id.navigation_chats:
                    startActivity(new Intent(HomeScreenActivity.this, ChatActivity.class));
                    return true;
                case R.id.profile_navigation:
                    Intent intent = new Intent(HomeScreenActivity.this, Profile.class);
                    startActivity(intent);
                    return true;
                default:
                    return false;
            }
        });
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.18.18/spot-it_user/fetchitems.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray itemsArray = new JSONArray(result);
                    itemArrayList.clear();
                    myItemList.clear();
                    recentItemList.clear();

                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject itemObject = itemsArray.getJSONObject(i);
                        String Name = itemObject.getString("Name");
                        String Description = itemObject.getString("Description");
                        Double Rate = itemObject.getDouble("Rate");
                        String City = itemObject.getString("City");
                        String ImageURI = itemObject.getString("ImageURI");
                        // Extract other fields as needed

                        Item item = new Item(); // Assuming you have an Item class
                        item.setName(Name);
                        item.setDescription(Description);
                        item.setRate(Rate);
                        item.setCity(City);
                        item.setImageURI(ImageURI);
                        // Set other fields

                        itemArrayList.add(item);
                        myItemList.add(item);
                        recentItemList.add(item);
                    }
                    recycleViewAdapter.notifyDataSetChanged();
                    recycleViewAdapter2.notifyDataSetChanged();
                    recycleViewAdapter3.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(HomeScreenActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
