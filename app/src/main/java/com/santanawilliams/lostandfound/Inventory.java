package com.santanawilliams.lostandfound;

/*
* The Inventory activity class
* Parent: MainActivity
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Inventory extends Activity {
    private LostDB db;
    private SoundManager sm;

    private ListView itemLV;
    private List<Item> itemList;

    // Variables used to determine what information is displayed in each item of the list
    // UNUSED AT THIS TIME
    boolean displayName;
    boolean displayType;
    boolean displayContact;
    boolean displayDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        initComponents();
    }

    // Populate item list only on resume
    // This allows us to make changes to the item list later by simply pausing and resuming the app
    @Override
    protected void onResume(){
        super.onResume();
        // Get all items from the database
        itemList = db.getAllItems();
        // Adapt the List
        itemLV.setAdapter(new ItemAdapter());
    }

    // Initialize all components of the class
    private void initComponents() {
        displayName = true;
        displayType = false;
        displayContact = false;
        displayDescription = true;
        db = new LostDB(this);
        itemLV = (ListView) findViewById(R.id.itemList);
        sm = new SoundManager(this);
    }

    // onClick for add button
    public void onAddClick(View v) {
        Intent i = new Intent(this, InventoryAdd.class);
        sm.playStapler();
        startActivity(i);
    }

    // Adapter class for list of items
    // Credit goes to Android Programming Concepts, pg 805
    private class ItemAdapter extends ArrayAdapter<Item>{
        // TextViews which are part of every Item entry
        private TextView nameTV;
        private TextView typeTV;
        private TextView contactTV;
        private TextView descriptionTV;

        public ItemAdapter() {
            super(getApplicationContext(), R.layout.listview_elem, itemList);
        }

        // Generate one View for every item in our Item array using its position in the array
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_elem, parent, false);

            Item current = itemList.get(position);

            nameTV = (TextView) view.findViewById(R.id.nameTxt);
            typeTV = (TextView) view.findViewById(R.id.typeTxt);
            contactTV = (TextView) view.findViewById(R.id.contactTxt);
            descriptionTV = (TextView) view.findViewById(R.id.descriptionTxt);

            nameTV.setText(current.getName());
            typeTV.setText(current.getType());
            contactTV.setText(current.getContactInfo());
            descriptionTV.setText(current.getDescription());

            return view;
        }
    }
}
