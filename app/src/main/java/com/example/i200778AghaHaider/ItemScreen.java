package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemScreen extends AppCompatActivity {

    Button RentItem;
    ImageView Back_btn;
    TextView report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_screen);
        Back_btn=findViewById(R.id.backBtn);
        RentItem=findViewById(R.id.rentBtn);
        report=findViewById(R.id.Report);

        // Initialize NavController (if needed for navigation)

        Back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)
                Intent intent2 = new Intent(ItemScreen.this, HomeScreenActivity.class);

                // Start the MainActivity
                startActivity(intent2);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the MainActivity (login screen)

                Intent intent2 = new Intent(ItemScreen.this, Report.class);

                // Start the MainActivity
                startActivity(intent2);
                // Start the MainActivity

            }
        });
//        RentItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Create an Intent to start the MainActivity (login screen)
//                Intent intent2 = new Intent(PostItem.this, UploadImage.class);
//
//                // Start the MainActivity
//                startActivity(intent2);
//            }
//        });




    }
}
