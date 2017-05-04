package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_blue_light;
import static android.R.color.holo_green_dark;
import static android.R.color.holo_green_light;
import static android.R.color.holo_red_dark;

public class Links extends Activity {

    RelativeLayout background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        background = (RelativeLayout) findViewById(R.id.activity_links);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.backgroundGreen:
                background.setBackgroundColor(getResources().getColor(holo_green_dark));
                return true;
            case R.id.backgroundBlue:
                background.setBackgroundColor(getResources().getColor(holo_blue_dark));
                return true;
            case R.id.backgroundRed:
                background.setBackgroundColor(getResources().getColor(holo_red_dark));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
