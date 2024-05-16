package Maryan1200861.Hamza1201619;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String SHARED_PREF_NAME = "MySharedPreference";
    private static SharedPreferencesManager instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setRememberMe(boolean rememberMe, String email) {
        editor.putBoolean("remember_me", rememberMe);
        editor.putString("email", email);
        editor.apply();
    }

    public boolean isRememberMe() {
        return sharedPreferences.getBoolean("remember_me", false);
    }

    public String getRememberedEmail() {
        return sharedPreferences.getString("email", "");
    }

    public void clearRememberMe() {
        editor.remove("remember_me");
        editor.remove("email");
        editor.apply();
    }
}

