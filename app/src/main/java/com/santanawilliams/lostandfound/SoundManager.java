package com.santanawilliams.lostandfound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
    private Context context;
    private SoundPool sp;
    private int stapler;
    private int roar;

    public SoundManager(Context context) {
        this.context = context;
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        stapler = sp.load(this.context, R.raw.stapler, 1);
        roar = sp.load(this.context, R.raw.lion_roar, 1);
    }

    public void playStapler() {
        sp.play(stapler, 1, 1, 1, 0, 1.0f);

    }

    public void playRoar() {
        sp.play(roar, 1, 1, 1, 0, 1.0f);
    }
}
