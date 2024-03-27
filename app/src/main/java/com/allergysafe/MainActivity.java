package com.allergysafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button buttonAllergyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAllergyInfo = findViewById(R.id.allergyInfoButton);
        buttonAllergyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AllergyInfoActivity
                Intent intent = new Intent(MainActivity.this, AllergyInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}