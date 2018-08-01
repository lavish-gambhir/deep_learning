package com.like.app01

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.preference.*
import android.widget.Toast

class SetFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_visual)
        val sharedPreferences: SharedPreferences = preferenceScreen.sharedPreferences
        val prefScreen: PreferenceScreen = preferenceScreen
        val count = prefScreen.preferenceCount

        for (i in 0..count) {
            val p: Preference = prefScreen.getPreference(i)
            if (p !is CheckBoxPreference) {
                val value: String = sharedPreferences.getString(p.key, "")
                setPreferenceSummary(p, value)
            }
        }

    }

    private fun setPreferenceSummary(preference: Preference, value: String): Unit {
        if (preference is ListPreference) {
            val listPreference: ListPreference = preference as ListPreference;
            val prefIndex: Int = listPreference.findIndexOfValue(value)
            if (prefIndex >= 0) {
                listPreference.summary = listPreference.entries[prefIndex]
            }
        } else if (preference is EditTextPreference) {
            preference.summary = value
        }
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        val error: Toast = Toast.makeText(context, "Please select a number between 1 and 3", Toast.LENGTH_SHORT)
        val sizeKey: String = getString(R.string.pref_size_key)
        if (preference?.key!! == sizeKey) {
            val stringSize: String = newValue as String
            try {

            }
        }
    }

}