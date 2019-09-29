package software.kalender.soruuygulamasi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.awt.font.TextAttribute;
import java.util.Random;

import software.kalender.soruuygulamasi.Animations.TextAnimation;
import software.kalender.soruuygulamasi.Database.Database;
import software.kalender.soruuygulamasi.Helpers.Config;
import software.kalender.soruuygulamasi.Helpers.FirebaseHelper;
import software.kalender.soruuygulamasi.Helpers.PlayerHelper;
import software.kalender.soruuygulamasi.Helpers.PopUpHelper;
import software.kalender.soruuygulamasi.Helpers.Reporter;
import software.kalender.soruuygulamasi.Helpers.SoundHelper;
import software.kalender.soruuygulamasi.Helpers.StaticsHelper;
import software.kalender.soruuygulamasi.Helpers.TextHelper;

public class Statics {

    public static String googleID = "";

    public static Context context;

    public static Database database;
    public static Config config;
    public static SoundHelper soundHelper;
    public static PlayerHelper player;
    public static TextHelper textHelper;
    public static StaticsHelper staticsHelper;
    public static FirebaseHelper firebase;

    public static PopUpHelper popUp;

    public static Random random = new Random();


    public static int getColor(int resID){
        return context.getResources().getColor(resID);
    }

    public static Drawable getDrawable(int resID){
        return context.getResources().getDrawable(resID);
    }

    public static String getString(int description, String... args) {
        try {
            return context.getString(description, args);
        }catch (Exception e){
            Reporter.error("STATICS_GS", e);
            return "&lang&";
        }
    }
}
