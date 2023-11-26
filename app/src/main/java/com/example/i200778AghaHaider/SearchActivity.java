package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    SearchView searchView;
    ArrayList<Item> allItems_List, filter_SearchList;
    RecyclerView SearchItem_recycleView;
    SearchItemRecycleViewAdapter SearchItem_recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        searchView = findViewById(R.id.simpleSearchView);
        SearchItem_recycleView = findViewById(R.id.search_result_recycleView);
        SearchItem_recycleView.setHasFixedSize(true);
        SearchItem_recycleView.setLayoutManager(new LinearLayoutManager(this));
        allItems_List = new ArrayList<>();
        filter_SearchList = new ArrayList<>();

        SearchItem_recycleViewAdapter = new SearchItemRecycleViewAdapter(this, filter_SearchList);
        SearchItem_recycleView.setAdapter(SearchItem_recycleViewAdapter);

        // Fetch data from the API
        new FetchItemsTask().execute();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterlist(s);
                return true;
            }

            private void filterlist(String text) {
                filter_SearchList.clear();
                for (Item item : allItems_List) {
                    if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                        filter_SearchList.add(item);
                    }
                }

                if (filter_SearchList.isEmpty()) {
                    Log.d("Data found", "Data Not Found");
                } else {
                    SearchItem_recycleViewAdapter.setDataList(filter_SearchList);
                }
            }
        });

        setupBottomNavigationView();
    }

    private void setupBottomNavigationView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(SearchActivity.this, HomeScreenActivity.class));
                    return true;
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_add:
                    startActivity(new Intent(SearchActivity.this, PostItem.class));
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
                URL url = new URL("http://192.168.18.18/spot-it_user/fetchitems.php"); // Replace with your API URL
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
                    allItems_List.clear();

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

                        allItems_List.add(item);
                    }
                    SearchItem_recycleViewAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(SearchActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
