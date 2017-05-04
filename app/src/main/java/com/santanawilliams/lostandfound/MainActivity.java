package com.santanawilliams.lostandfound;

/*
* MainActivity Activity
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

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

        // Fade a picture to nothing
        lionAnim = new AlphaAnimation(1.0f, 0.0f);
        // Fade in a picture from nothing to full
        reverseAnim = new AlphaAnimation(0.0f, 1.0f);

        lionAnim.setDuration(2000);
        reverseAnim.setDuration(2000);

        //Listener for Nittany Lion Picture
        lionPic.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v){
                // Start the forward animation
                lionPic.startAnimation(lionAnim);
                // Play the roar
                sm.playRoar();
                return true;
            }
        });

        lionAnim.setAnimationListener(new Animation.AnimationListener() {
            // After forward animation is finished, play the reverse animation
            @Override
            public void onAnimationEnd(Animation animation) {
                lionPic.startAnimation(reverseAnim);
            }
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    // Add a sound effect to any click
    public void onAnyClick(Class t) {
        Intent i = new Intent(this, t);
        sm.playStapler();
        // Launch a specified activity
        startActivity(i);
    }

    // onClick handler for Inventory button
    public void onInventoryClick(View v) {
        // Launch Inventory activity
        onAnyClick(Inventory.class);
    }

    // onClick handler for Contact button
    public void onContactClick(View v){
        // Launch the Contact activity
        onAnyClick(Contact.class);
    }

    // onClick handler for Links button
    public void onLinksClick(View v){
        // Launch the Links activity
        onAnyClick(Links.class);
    }
}
