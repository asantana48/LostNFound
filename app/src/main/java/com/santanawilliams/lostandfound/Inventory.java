package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inventory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }

    public void onAddClick(View v) {
        Intent i = new Intent(this, InventoryAdd.class);
        startActivity(i);
    }
}
