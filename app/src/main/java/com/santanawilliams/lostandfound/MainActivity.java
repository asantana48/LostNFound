package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private SoundManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = new SoundManager(this);

        //Listener for Nittany Lion Picture
        final ImageView lionPic = (ImageView) findViewById(R.id.lionImg);
        lionPic.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                lionPic.setImageResource(R.mipmap.psu_lion_red);
                sm.playRoar();
                return true;
            }
        });
    }

    public void onAnyClick(Class t) {
        Intent i = new Intent(this, t);
        sm.playStapler();
        startActivity(i);
    }

    public void onInventoryClick(View v) {
        onAnyClick(Inventory.class);
    }

    public void onContactClick(View v){
        onAnyClick(Contact.class);
    }

    public void onLinksClick(View v){
        onAnyClick(Links.class);
    }
}
