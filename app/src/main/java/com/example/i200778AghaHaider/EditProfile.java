package com.example.i200778AghaHaider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class EditProfile extends AppCompatActivity {
    ImageView back_btn;
    TextView SaveChanges;
    Spinner country, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_screen);
        String currentUserName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        Toast.makeText(EditProfile.this, currentUserName, Toast.LENGTH_SHORT).show();
        back_btn = findViewById(R.id.backButton);
        SaveChanges = findViewById(R.id.saveChanges);

        // Initialize the country and city Spinners
        country = findViewById(R.id.country_input);
        city = findViewById(R.id.city_input);

        // Set the adapters for the Spinners
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this,
                R.array.country_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(countryAdapter);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditProfile.this, Profile.class);
                startActivity(intent1);
            }
        });

        SaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(EditProfile.this, Profile.class);
                startActivity(intent2);
            }
        });
    }
}
