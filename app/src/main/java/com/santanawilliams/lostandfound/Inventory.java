package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends Activity {
    private LostDB db;

    private ListView itemLV;
    private List<Item> itemList;

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

    @Override
    protected void onResume(){
        super.onResume();
        itemList = db.getAllItems();
        itemLV.setAdapter(new ItemAdapter());

    }

    private void initComponents() {
        displayName = true;
        displayType = false;
        displayContact = false;
        displayDescription = true;
        db = new LostDB(this);
        itemLV = (ListView) findViewById(R.id.itemList);
    }

    public void onAddClick(View v) {
        Intent i = new Intent(this, InventoryAdd.class);
        startActivity(i);
    }

    // Adapter class for list of items
    // Credit goes to Android Programming Concepts, pg 805
    private class ItemAdapter extends ArrayAdapter<Item>{
        private TextView nameTV;
        private TextView typeTV;
        private TextView contactTV;
        private TextView descriptionTV;

        public ItemAdapter() {
            super(getApplicationContext(), R.layout.listview_elem, itemList);
        }

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
