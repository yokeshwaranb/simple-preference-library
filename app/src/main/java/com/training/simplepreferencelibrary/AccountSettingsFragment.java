package com.training.simplepreferencelibrary;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class AccountSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {

//        setPreferencesFromResource(R.xml.account_settings, rootKey);

        // Step 1: Define all the Preference objects with appropriate properties (attributes)
        MultiSelectListPreference publicIfo = new MultiSelectListPreference(getContext());
        publicIfo.setEntries(getResources().getStringArray(R.array.entries_public_info));
        publicIfo.setEntryValues(getResources().getStringArray(R.array.values_public_info));
        publicIfo.setKey(getString(R.string.key_public_info));
        publicIfo.setTitle(getString(R.string.title_public_info));
        publicIfo.setIconSpaceReserved(false);

        Preference logoutPref = new Preference(getContext());
        logoutPref.setKey(getString(R.string.key_log_out));
        logoutPref.setTitle(getString(R.string.title_log_out));
        logoutPref.setIconSpaceReserved(false);

        Preference deleteAccPref = new Preference(getContext());
        deleteAccPref.setKey(getString(R.string.key_delete_account));
        deleteAccPref.setSummary(getString(R.string.summary_delete_account));
        deleteAccPref.setTitle(getString(R.string.title_delete_account));
        deleteAccPref.setIcon(ResourcesCompat.getDrawable(getResources(), android.R.drawable.ic_menu_delete, null));

        PreferenceCategory privacyCategory = new PreferenceCategory(getContext());
        privacyCategory.setTitle(getString(R.string.title_privacy));
        privacyCategory.setIconSpaceReserved(false);

        PreferenceCategory securityCategory = new PreferenceCategory(getContext());
        securityCategory.setTitle(getString(R.string.title_security));
        securityCategory.setIconSpaceReserved(false);

        PreferenceScreen preferenceScreen = getPreferenceManager().createPreferenceScreen(getContext());

        // Step 2: Add all the Preference objects in hierarchy
        preferenceScreen.addPreference(privacyCategory);
        preferenceScreen.addPreference(securityCategory);

        privacyCategory.addPreference(publicIfo);

        securityCategory.addPreference(logoutPref);
        securityCategory.addPreference(deleteAccPref);

        // Step 3: Set the preference screen
        setPreferenceScreen(preferenceScreen);
    }
}
