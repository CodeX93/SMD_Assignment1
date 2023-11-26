package com.example.i200778AghaHaider;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItemScreen extends AppCompatActivity {

    private Button RentItem;
    private ImageView Back_btn, ItemImage;
    private TextView report, ItemName, Rate, Location, Description, UserName, UserContactNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_screen);

        initializeUI();

        String Item_Name = getIntent().getStringExtra("Item_Name");
        new FetchItemDetails().execute("http://192.168.18.18/spot-it_user/getSpecificItem.php?Item_Name=" + Item_Name);
    }

    private void initializeUI() {
        Back_btn = findViewById(R.id.backBtn);
        RentItem = findViewById(R.id.rentBtn);
        report = findViewById(R.id.Report);
        ItemName = findViewById(R.id.Item_Name);
        Rate = findViewById(R.id.Item_Rate);
        Location = findViewById(R.id.Item_Location);
        Description = findViewById(R.id.Item_Description);
        UserName = findViewById(R.id.UserName);
        UserContactNo = findViewById(R.id.UserContactNo);
        ItemImage = findViewById(R.id.ItemImage);

        Back_btn.setOnClickListener(view -> startActivity(new Intent(ItemScreen.this, HomeScreenActivity.class)));
       report.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(ItemScreen.this, Report.class);
               intent.putExtra("ItemName", ItemName.getText());;
               startActivity(intent);
           }
       });
    }

    private class FetchItemDetails extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
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
            Log.d("API Response", result);

            if (result != null && !result.isEmpty()) {
                try {
                    JSONObject jsonResponse = new JSONObject(result);

                    // Check if the "Items" array exists in the JSON
                    if (jsonResponse.has("Items")) {
                        JSONArray itemsArray = jsonResponse.getJSONArray("Items");

                        // Assuming you have only one item in the array
                        if (itemsArray.length() > 0) {
                            JSONObject itemObject = itemsArray.getJSONObject(0);

                            // Now you can access the properties inside the itemObject
                            String name = itemObject.optString("Name", "N/A");
                            String city = itemObject.optString("City", "N/A");
                            String rate = itemObject.optString("Rate", "N/A");
                            String description = itemObject.optString("Description", "N/A");
                            String imageUrl = itemObject.optString("ImageURI", "");

                            // Now set the values to your TextViews
                            ItemName.setText(name);
                            Location.setText(city);
                            Rate.setText(rate);
                            Description.setText(description);

                            // Load the image using Picasso
                            if (!imageUrl.isEmpty()) {
                                Picasso.get().load(imageUrl).into(ItemImage);
                                Log.d("ImageURI", imageUrl);
                            }
                        } else {
                            // Handle the case where no items were found in the array
                            Toast.makeText(ItemScreen.this, "No items found.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // Handle the case where the "Items" array is missing in the JSON
                        Toast.makeText(ItemScreen.this, "No items found.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ItemScreen.this, "Error parsing item details.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(ItemScreen.this, "Item details not found.", Toast.LENGTH_LONG).show();
            }
        }

    }
}
