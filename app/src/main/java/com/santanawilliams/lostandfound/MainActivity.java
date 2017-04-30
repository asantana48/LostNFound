package com.santanawilliams.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    AlphaAnimation lionAnim, reverseAnim;
    private SoundManager sm;
    private ImageView lionPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = new SoundManager(this);
        lionPic = (ImageView) findViewById(R.id.blueLionImg);

        lionAnim = new AlphaAnimation(1.0f, 0.0f);
        reverseAnim = new AlphaAnimation(0.0f, 1.0f);
        lionAnim.setDuration(2000);
        reverseAnim.setDuration(2000);
        lionAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                lionPic.startAnimation(reverseAnim);
            }
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        //Listener for Nittany Lion Picture
        lionPic.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                lionPic.startAnimation(lionAnim);
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
