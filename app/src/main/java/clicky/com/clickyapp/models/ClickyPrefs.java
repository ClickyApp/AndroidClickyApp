package clicky.com.clickyapp.models;

import android.content.Context;
import android.content.SharedPreferences;

public class ClickyPrefs {

    private static ClickyPrefs sClickyPrefs;
    private static final String PREFS_AUTH = "clicky.com.clickyapp.models.auth";
    private static final String PREFS_EMAIL = "clicky.com.clickyapp.models.email";
    private static final String PREFS_FIRST_NAME = "clicky.com.clickyapp.models.first_name";
    private static final String PREFS_LAST_NAME = "clicky.com.clickyapp.models.last_name";
    private Context mContext;

    private ClickyPrefs(Context context) {
        mContext = context;
    }

    public static ClickyPrefs get(Context context) {
        if(sClickyPrefs == null) {
            sClickyPrefs = new ClickyPrefs(context);
        }
        return sClickyPrefs;
    }

    public boolean setEmail(String email) {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return editor.putString(PREFS_EMAIL, email).commit();
    }

    public boolean setFirstName(String firstName) {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return editor.putString(PREFS_FIRST_NAME, firstName).commit();
    }

    public boolean setLastName(String lastName) {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        return editor.putString(PREFS_LAST_NAME, lastName).commit();
    }

    public String getEmail() {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_EMAIL, "");
    }

    public String getFirstName() {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_FIRST_NAME, "");
    }

    public String getLastName() {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFS_AUTH, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_LAST_NAME, "");
    }
}
