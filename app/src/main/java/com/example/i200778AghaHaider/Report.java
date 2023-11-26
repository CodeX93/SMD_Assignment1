package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.json.JSONObject;

import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Report extends AppCompatActivity {

    ImageView Back_btn;
    Button Submitreport;
    EditText reportText;
    String ItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_report_screen);

        // Initialize UI elements
        Back_btn = findViewById(R.id.backBtn);
        Submitreport = findViewById(R.id.SubmitReport);
        reportText = findViewById(R.id.report_reason_input);

        // Get the item name from the previous activity
        Intent intent = getIntent();
        ItemName = intent.getStringExtra("ItemName");

        // Initialize NavController (if needed for navigation)
        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to go back to the ItemScreen activity
                Intent intent = new Intent(Report.this, ItemScreen.class);
                startActivity(intent);
            }
        });

        Submitreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the report text entered by the user
                String userReportText = reportText.getText().toString();
//                Log.d("ItemNAMEEE",ItemName);
                // Execute the AsyncTask to submit the report
                if(!userReportText.isEmpty() && !ItemName.isEmpty()) {
                    new SubmitReportTask().execute(userReportText, ItemName);
                }else {Toast.makeText(Report.this, "Please provide report Text", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private class SubmitReportTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String apiUrl = "http://192.168.18.18/spot-it_user/postItemReport.php";
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                // Create a JSON object
                JSONObject reportDetails = new JSONObject();
                reportDetails.put("report_text", params[0]);
                reportDetails.put("item_name", params[1]);

                // Write the JSON object to the output stream
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(reportDetails.toString());
                writer.flush();
                writer.close();
                os.close();

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Successfully submitted report
                    return "Report submitted successfully";
                } else {
                    // Handle the error case
                    return "Failed to submit report";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the result, e.g., show a toast message
            Toast.makeText(Report.this, result, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Report.this,HomeScreenActivity.class));
        }
    }

}


