package com.example.tvsample.utils;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by JasonWu on 28/12/2017
 */
public class AudioUtil {

    private AudioUtil() {}

    private static final Object mSingletonLock = new Object();
    private static AudioManager audioManager;

    private static AudioManager getInstance(Context context) {
        synchronized (mSingletonLock) {
            if (audioManager != null)
                return audioManager;
            if (context != null)
                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            return audioManager;
        }
    }

    public static void adjustMusicVolume(Context context, boolean up) {
        int direction = up ? AudioManager.ADJUST_RAISE : AudioManager.ADJUST_LOWER;
        int flag = AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE | AudioManager.FLAG_SHOW_UI ;
        getInstance(context).adjustStreamVolume(AudioManager.STREAM_MUSIC, direction, flag);
    }

//    public static void playKeyClickSound(Context context, int volume) {
//        if (volume == 0) return;
//        getInstance(context).playSoundEffect(AudioManager.FX_KEY_CLICK, (float) volume / 100.0f);
//    }
}
