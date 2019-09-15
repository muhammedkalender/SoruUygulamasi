package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AssetsHelper {
    public String read(String PATH, Context context) {
        try {
            String text = "";

            InputStream inputStream = context.getAssets().open(PATH);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            text = new String(buffer);


            return text;
        } catch (OutOfMemoryError e) {
           Reporter.error("ASS_HELPER_OOM", e);
            return "";
        } catch (FileNotFoundException e) {
            Reporter.error("ASS_HELPER_FNF", e);
            return "";
        } catch (Exception e) {
            Reporter.error("ASS_HELPER", e);
            return "";
        }
    }
}
