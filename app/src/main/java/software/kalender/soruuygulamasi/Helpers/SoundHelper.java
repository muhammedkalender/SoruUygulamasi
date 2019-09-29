package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import software.kalender.soruuygulamasi.R;
import software.kalender.soruuygulamasi.Statics;

public class SoundHelper {

    private Context context;

    private SoundPool soundPool;

    public SoundHelper(Context context) {
        this.context = context;

        //https://stackoverflow.com/a/19311820
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(attrs)
                .build();
    }

    public void playSound(String fileName) {
        //todo

       // https://stackoverflow.com/a/25073079
        //todo MediaPlayer mp = MediaPlayer.create(Statics.context, R.raw.combo);
        //mp.start();
    }

    public void playSoundFromId(int resId) {
        //todo
    }
}
