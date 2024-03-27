package com.allergysafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AllergyDetailActivity extends AppCompatActivity {
    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private Allergy selectedAllergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergy_detail);
        initWidgets();
        checkForEditNote();
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteAllergyButton);
    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();
        int passedAllergyID = previousIntent.getIntExtra(Allergy.ALLERGY_EDIT_EXTRA,-1);
        selectedAllergy = Allergy.getAllergyForID(passedAllergyID);

        if (selectedAllergy != null) {
            titleEditText.setText(selectedAllergy.getAllergy());
            descEditText.setText(selectedAllergy.getDescription());
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveAllergy(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if (selectedAllergy == null) {
            int id = Allergy.allergyArrayList.size();
            Allergy newAllergy = new Allergy(id, title, desc);
            Allergy.allergyArrayList.add(newAllergy);
            sqLiteManager.addAllergyToDatabase(newAllergy);
        } else {
            selectedAllergy.setAllergy(title);
            selectedAllergy.setDescription(desc);
            sqLiteManager.updateNoteInDB(selectedAllergy);
        }
        finish();
    }

    public void deleteAllergy(View view) {
        selectedAllergy.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateNoteInDB(selectedAllergy);
        finish();
    }

}