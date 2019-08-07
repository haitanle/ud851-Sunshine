package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    // Do steps 5 - 11 within SettingsFragment
    // done (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

    // done (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference

    // done (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource

    // Do step 9 within onCreatePreference
    // done (9) Set the preference summary on each preference that isn't a CheckBoxPreference

    // done (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

    // done (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart

    // done (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed

    /**
    * On preference setup, display the Fragment
     * and get all Preferences (not checkbox) and the selected value for each Preference send to setPreferenceSummary
    * @param bundle
    * @param s
    * @return void send to setPreferenceSummary(Preference, selectValue)
     */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();
        int count = preferenceScreen.getPreferenceCount();

        for (int i = 0; i < count; i++){
            Preference preference = preferenceScreen.getPreference(i);
            if (!(preference instanceof CheckBoxPreference)){
                String entryValue = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference, entryValue);
            }
        }
    }

    /**
     * Get the set value for each respective Preference and set it to its Preference summary
     * @param preference the preference pass in
     * @param entryValue the entry value choosen
     * @return void set summary for each preference
     */
    private void setPreferenceSummary(Preference preference, String entryValue){
        if (preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int indexValue = listPreference.findIndexOfValue(entryValue);
            if (indexValue >= 0){
                CharSequence label = listPreference.getEntries()[indexValue];
                listPreference.setSummary(label);
            }
        }
        else if (preference instanceof EditTextPreference){
            preference.setSummary(entryValue);
        }
    }

    /**
     * Called when Preference are changed
     * @param sharedPreferences links to the SharedPreference XML file containing selected value
     * @param key key of the preference that changed
     * @return void send tp setPreferenceSummary when changed
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference != null){
            if (!(preference instanceof CheckBoxPreference)){
                String entryValue = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,entryValue);
            }
        }
    }

//    /**
//     * start of life cycle, register the preferenceChangeListener
//     * @param savedInstanceState
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
//    }

    /**
     * when start up app, register the preferenceChangeListener
     */
    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    /**
     * when onStop lifecycle, unregister the preferenceChangeListener
     */
    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

//    /**
//     * end of life cycle, unregister the preferenceChangeListener
//     */
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
//    }
}
