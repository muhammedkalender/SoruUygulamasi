package software.kalender.soruuygulamasi.Helpers;

import android.util.Log;

public class Reporter {
    public static void error(String _CODE, Exception _E) {
        //todo
        Log.e("ERR_" + _CODE, "" + _E.getMessage());
    }

    public static void error(String _CODE, OutOfMemoryError _E) {
        //todo
        Log.e("OOM_ERR_" + _CODE, "" + _E.getMessage());
    }
}
