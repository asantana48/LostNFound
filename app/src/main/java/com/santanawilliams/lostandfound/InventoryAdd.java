package com.santanawilliams.lostandfound;


/*
* The InventoryAdd activity class
* Parents: MainActivity --> Inventory
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InventoryAdd extends Activity {
    private SoundManager sm;
    private LostDB db;

    private EditText nameET;
    private EditText contactET;
    private EditText descriptionET;
    private Spinner typeSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);
        initComponents();
        setSpinnerAdapter();
    }

    // Initialize components
    private void initComponents() {
        nameET = (EditText) findViewById(R.id.nameInput);
        contactET = (EditText) findViewById(R.id.contactInput);
        descriptionET = (EditText) findViewById(R.id.descriptionInput);
        typeSP = (Spinner) findViewById(R.id.typeSpinner);
        sm = new SoundManager(this);
        db = new LostDB(this);
    }

    // Set a spinner adapter for the spinner
    private void setSpinnerAdapter() {
        // Adapt the array in Strings.XML file
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(
                this, R.array.types, android.R.layout.simple_spinner_item);

        // Set an android.R.layout instead of a custom layout
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSP.setAdapter(typeAdapter);
    }

    // Check the EditTexts for emptiness
    private boolean checkForEmpties() {
        String errorMsg = "";
        boolean isEmpty = false;

        // Check Name text
        if (nameET.getText().toString().matches("")) {
            errorMsg = "Please enter the name of your item.";
            isEmpty = true;
        }
        // Check Contact text
        else if (contactET.getText().toString().matches("")) {
            errorMsg = "Please enter some contact information.";
            isEmpty = true;
        }

        if(isEmpty)
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        return isEmpty;
    }

    public void onAdd(View v) {
        // If name and contact are empty, return
        if (checkForEmpties())
            return;

        // Populate an item with text from the EditTexts
        Item item = new Item();
        item.setName(nameET.getText().toString());
        item.setContactInfo(contactET.getText().toString());
        item.setDescription(descriptionET.getText().toString());
        item.setType(typeSP.getSelectedItem().toString());

        // Insert the item into the database
        db.insertItem(item);

        sm.playStapler();
        finish();
    }
}
