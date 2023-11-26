package com.example.i200778AghaHaider;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostItem extends AppCompatActivity {

    Button PostItem_btn;
    Uri imageUri, videoUri; // Include a Uri for videos

    ImageView Back_btn, UploadPhoto, UploadVideo;
    EditText name, hourly_rate, description;
    Spinner city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_item_screen);

        PostItem_btn = findViewById(R.id.upload_item_btn);
        UploadPhoto = findViewById(R.id.uploadImage);
        UploadVideo = findViewById(R.id.uploadvideo);
        Back_btn = findViewById(R.id.backButton);
        name = findViewById(R.id.item_name_input);
        hourly_rate = findViewById(R.id.hourly_rate_input);
        description = findViewById(R.id.item_description_input);
        city = findViewById(R.id.city_input);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        // Event listeners for buttons and image/video uploads
        setupEventListeners();
    }

    private void setupEventListeners() {
        UploadPhoto.setOnClickListener(view -> openGallery("image/*", 100)); // Open gallery for image selection
        UploadVideo.setOnClickListener(view -> openGallery("video/*", 200)); // Open gallery for video selection

        PostItem_btn.setOnClickListener(view -> postItemData());
        Back_btn.setOnClickListener(view -> startActivity(new Intent(PostItem.this, HomeScreenActivity.class)));
    }

    private void openGallery(String type, int requestCode) {
        Intent intent = new Intent();
        intent.setType(type);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select"), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == 100) {
                imageUri = data.getData();
                UploadPhoto.setImageURI(imageUri);
            } else if (requestCode == 200) {
                videoUri = data.getData();
                UploadVideo.setImageURI(videoUri);
            }
        }
    }

    private void postItemData() {
        String itemName = name.getText().toString();
        String tempRate = hourly_rate.getText().toString();
        Double itemHourlyRate = Double.parseDouble(tempRate);
        String itemDescription = description.getText().toString();
        String itemCity = city.getSelectedItem().toString();

        Item newItem = new Item(itemName, itemDescription, itemCity, itemHourlyRate, imageUri.toString()); // Assuming null for imageURL/videoURL
        new UploadItemTask().execute(newItem);
    }

    private class UploadItemTask extends AsyncTask<Item, Void, String> {

        @Override
        protected String doInBackground(Item... items) {
            try {
                URL url = new URL("http://192.168.18.18/spot-it_user/insert-item.php"); // Replace with your API URL
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoOutput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                JSONObject jsonParams = new JSONObject();
                Item item = items[0];
                jsonParams.put("Name", item.getName());
                jsonParams.put("Description", item.getDescription());
                jsonParams.put("City", item.getCity());
                jsonParams.put("Rate", item.getRate());
                jsonParams.put("ImageURI",item.getImageURI());
                // Add other item details to JSON

                writer.write(jsonParams.toString());
                writer.flush();
                writer.close();
                outputStream.close();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("success")) {
                Toast.makeText(PostItem.this, "Item Uploaded Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(PostItem.this, HomeScreenActivity.class));
            } else {
                Toast.makeText(PostItem.this, "Upload failed: " + result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
