package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class InventoryAdd extends Activity {
    private SoundManager sm;
    private EditText nameET;
    private EditText contactET;
    private EditText descriptionET;
    private Spinner typeSP;
    private LostDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);
        initComponents();
        setSpinnerAdapter();
    }

    private void initComponents() {
        nameET = (EditText) findViewById(R.id.nameInput);
        contactET = (EditText) findViewById(R.id.contactInput);
        descriptionET = (EditText) findViewById(R.id.descriptionInput);
        typeSP = (Spinner) findViewById(R.id.typeSpinner);
        sm = new SoundManager(this);
        db = new LostDB(this);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this, R.array.types, android.R.layout.simple_spinner_item);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSP.setAdapter(typeAdapter);
    }

    private boolean checkForEmpties() {
        String errorMsg = "";
        boolean isEmpty = false;
        if (nameET.getText().toString().matches("")) {
            errorMsg = "Please enter the name of your item.";
            isEmpty = true;
        }
        else if (contactET.getText().toString().matches("")) {
            errorMsg = "Please enter some contact information.";
            isEmpty = true;
        }

        if(isEmpty)
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        return isEmpty;
    }

    public void onAdd(View v) {
        if (checkForEmpties())
            return;

        Item item = new Item();
        item.setName(nameET.getText().toString());
        item.setContactInfo(contactET.getText().toString());
        item.setDescription(descriptionET.getText().toString());
        item.setType(typeSP.getSelectedItem().toString());
        db.insertItem(item);

        sm.playStapler();
        finish();
    }
}
