package com.example.i200778AghaHaider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.checkerframework.checker.units.qual.C;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, contactNoEditText;
    private Spinner country, city;
    private Button signupButton;
    private ExecutorService executorService;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        nameEditText = findViewById(R.id.Name_Input);
        emailEditText = findViewById(R.id.email_input);
        passwordEditText = findViewById(R.id.password_input);
        contactNoEditText = findViewById(R.id.contact_input);
        country = findViewById(R.id.country_input);
        city = findViewById(R.id.city_input);

        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this,
                R.array.country_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(countryAdapter);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        signupButton = findViewById(R.id.signup_btn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String contactNo = contactNoEditText.getText().toString();
                String Country = country.getSelectedItem().toString();
                String City = city.getSelectedItem().toString();


                RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask();
                registerAsyncTask.execute(name, email, password, contactNo, Country, City);
            }
        });
    }
    private class RegisterAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String Name = params[0];
            String Email = params[1];
            String Password = params[2];
            String ContactNo = params[3];
            String Country = params[4];
            String City = params[5];
            Log.d("JSON",Name+" "+Country);
            try {
                URL url = new URL("http://192.168.18.18/spot-it_user/insert.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoOutput(true);

                // Now that we have set the request properties, we can get the output stream
                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                // Create JSON object
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("name", Name);
                jsonParams.put("email", Email);
                jsonParams.put("password", Password);
                jsonParams.put("contactNo", ContactNo);
                jsonParams.put("country", Country);
                jsonParams.put("city", City);


                writer.write(jsonParams.toString());
                writer.flush();
                writer.close();
                outputStream.close();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String responseLine;
                StringBuilder response = new StringBuilder();

                while ((responseLine = reader.readLine()) != null) {
                    response.append(responseLine);
                }

                return response.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return "Error";
            } catch (JSONException e) {
                Log.e("JsonError", "JSONException", e);
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.contains("Data inserted successfully")) {
                Toast.makeText(SignUpActivity.this, "Registration Success: "+result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            } else if (result.equals("Error")) {
                Toast.makeText(SignUpActivity.this, "Network error or server not reachable", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(SignUpActivity.this, "Registration failed: "+result, Toast.LENGTH_LONG).show();
            }
        }
    }



}

