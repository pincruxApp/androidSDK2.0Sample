package com.pincrux.offerwall.kotlinsample

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Preference(val context: Context) {
    companion object {
        const val PREF_FILE_NAME: String = "pincruxsample.pref"
        const val PREF_PINCRUX_SAMPLE_PUBKEY = "PREF_PINCRUX_PUBKEY"
    }


    private fun prefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE_NAME, Activity.MODE_PRIVATE)
    }

    var pubkey: String
        get() = prefs(context).getString(PREF_PINCRUX_SAMPLE_PUBKEY, "").toString()
        set(value) {
            prefs(context).edit().putString(PREF_PINCRUX_SAMPLE_PUBKEY, value).apply()
        }
}