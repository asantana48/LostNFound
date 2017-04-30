package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class InventoryAdd extends Activity {
    private EditText nameET;
    private EditText contactET;
    private EditText descriptionET;
    private Spinner typeSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);
        initComponents();

        // Set adapter
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this, R.array.types, android.R.layout.simple_spinner_item);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSP.setAdapter(typeAdapter);
    }

    private void initComponents() {
        nameET = (EditText) findViewById(R.id.nameInput);
        contactET = (EditText) findViewById(R.id.contactInput);
        descriptionET = (EditText) findViewById(R.id.descriptionInput);
        typeSP = (Spinner) findViewById(R.id.typeSpinner);
    }

    public void onAdd(View v) {

    }
}
