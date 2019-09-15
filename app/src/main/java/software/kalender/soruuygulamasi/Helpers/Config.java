package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import software.kalender.soruuygulamasi.Statics;

public class Config {
    private Context context;

    //region Getters & Setters

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    //endregion

    //region Constructors

    public Config() {
        this.context = Statics.context;
    }

    public Config(Context context) {
        this.context = context;
    }

    //endregion

    //region Set Configs

    public boolean setLong(String _ID, long _VALUE) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Statics.context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong(_ID, _VALUE);
            editor.apply();
            return true;
        } catch (Exception e) {
            Reporter.error("SET_CONFIG_LONG", e);
            return false;
        }
    }

    public boolean setInt(String _ID, int _VALUE) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Statics.context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(_ID, _VALUE);
            editor.apply();
            return true;
        } catch (Exception e) {
            Reporter.error("SET_CONFIG_INT", e);
            return false;
        }
    }

    public boolean setString(String _ID, String _VALUE) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Statics.context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(_ID, _VALUE);
            editor.apply();
            return true;
        } catch (Exception e) {
            Reporter.error("SET_CONFIG_STRING", e);
            return false;
        }
    }

    public boolean setFloat(String _ID, float _VALUE) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Statics.context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat(_ID, _VALUE);
            editor.apply();
            return true;
        } catch (Exception e) {
            Reporter.error("SET_CONFIG_FLOAT", e);
            return false;
        }
    }

    public boolean setBoolean(String _ID, boolean _VALUE) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Statics.context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(_ID, _VALUE);
            editor.apply();
            return true;
        } catch (Exception e) {
            Reporter.error("SET_CONFIG_BOOLEAN", e);
            return false;
        }
    }

    //endregion

    //region Get Configs

    public int getInt(String _ID, int _DEFAULT) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            return sharedPreferences.getInt(_ID, _DEFAULT);
        } catch (Exception e) {
            Reporter.error("GET_CONFIG_INT", e);

            return _DEFAULT;
        }
    }

    public String getString(String _ID, String _DEFAULT) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            return sharedPreferences.getString(_ID, _DEFAULT);
        } catch (Exception e) {
            Reporter.error("GET_CONFIG_STRING", e);

            return _DEFAULT;
        }
    }

    public boolean getBoolean(String _ID, boolean _DEFAULT) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            return sharedPreferences.getBoolean(_ID, _DEFAULT);
        } catch (Exception e) {
            Reporter.error("GET_CONFIG_BOOLEAN", e);

            return _DEFAULT;
        }
    }

    public float getFloat(String _ID, float _DEFAULT) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            return sharedPreferences.getFloat(_ID, _DEFAULT);
        } catch (Exception e) {
            Reporter.error("GET_CONFIG_FLOAT", e);

            return _DEFAULT;
        }
    }

    public long getLong(String _ID, long _DEFAULT) {
        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            return sharedPreferences.getLong(_ID, _DEFAULT);
        } catch (Exception e) {
            Reporter.error("GET_CONFIG_LONG", e);

            return _DEFAULT;
        }
    }

    //endregion
}
