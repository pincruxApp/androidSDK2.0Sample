package com.pincrux.offerwall.javasample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    public static Preference instance;

    public static final String PREF_FILE_NAME = "pincruxsample.pref";
    public static final String PREF_PINCRUX_PUBKEY = "PREF_PINCRUX_SAMPLE_PUBKEY";

    public static Preference getInstance() {
        if (instance == null)
            instance = new Preference();
        return instance;
    }

    private SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Activity.MODE_PRIVATE);
    }

    public void setPubKey(Context context, String pubkey) {
        prefs(context).edit().putString(PREF_PINCRUX_PUBKEY, pubkey).apply();
    }

    public String getPubKey(Context context) {
        return prefs(context).getString(PREF_PINCRUX_PUBKEY, "");
    }
}
