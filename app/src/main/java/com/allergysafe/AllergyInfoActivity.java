package com.allergysafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class AllergyInfoActivity  extends AppCompatActivity {

    private ListView allergyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_info);
        initWidgets();
        loadFromDBToMemory();
        setAllergyAdapter();
        setOnClickListener();
    }


    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateNoteListArray();
    }

    private void initWidgets(){
        allergyListView = findViewById(R.id.allergyListView);
    }

    private void setAllergyAdapter() {
        AllergyAdapter allergyAdapter = new AllergyAdapter(getApplicationContext(), Allergy.nonDeletedAllergies());
        allergyListView.setAdapter(allergyAdapter);
    }

    private void setOnClickListener() {
        allergyListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                Allergy selectedAllergy = (Allergy) allergyListView.getItemAtPosition(position);
                Intent editAllergyIntent = new Intent(getApplicationContext(), AllergyDetailActivity.class);
                editAllergyIntent.putExtra(Allergy.ALLERGY_EDIT_EXTRA, selectedAllergy.getId());
                startActivity(editAllergyIntent);
            }
        });
    }

    public void newAllergy(View view) {
        Intent newAllergyIntent = new Intent(this, AllergyDetailActivity.class);
        startActivity(newAllergyIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAllergyAdapter();
    }
}
