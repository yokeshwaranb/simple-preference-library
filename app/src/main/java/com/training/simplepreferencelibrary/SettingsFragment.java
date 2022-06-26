package com.training.simplepreferencelibrary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat {

    private static final String TAG = "MySettingsFragment";

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        Preference accountSettingsPref = findPreference(getResources().getString(R.string.key_account_settings));

        accountSettingsPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {

                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                NavController navController = navHostFragment.getNavController();
                NavDirections direction = SettingsFragmentDirections.actionSettingsFragmentToAccountSettingsFragment();
                navController.navigate(direction);

                return true;
            }
        });

        // Read Preference values in a PreferenceFragment
        // Step 1: Get reference to the SharedPreferences (XML File)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        // Step 2: Get the 'value' using the 'key'
        String autoReplyTime = sharedPreferences.getString(getResources().getString(R.string.key_auto_reply_time), "");
        Log.i(TAG, "Auto Reply Time: " + autoReplyTime);

        Boolean autoDownload = sharedPreferences.getBoolean(getResources().getString(R.string.key_auto_download), false);
        Log.i(TAG, "Auto Download: " + autoDownload);

        EditTextPreference statusPref = findPreference(getResources().getString(R.string.key_status));
        statusPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                String newStatus = (String) newValue;
                if(newStatus.toLowerCase().contains("bad")){

                    Toast.makeText(getContext(), "Inappropriate Status. Please maintain community guidelines.",
                            Toast.LENGTH_SHORT).show();

                    return false; // false: reject the new value.
                }
                Log.i(TAG, "New Status: " + newValue);
                return true; // true: accept the new value.
            }
        });
    }
}
