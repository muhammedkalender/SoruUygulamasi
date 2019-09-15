package software.kalender.soruuygulamasi;

import android.content.Context;
import android.widget.TextView;

import org.w3c.dom.Text;

import software.kalender.soruuygulamasi.Database.Database;
import software.kalender.soruuygulamasi.Helpers.Config;
import software.kalender.soruuygulamasi.Helpers.FirebaseHelper;
import software.kalender.soruuygulamasi.Helpers.PlayerHelper;
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
}
