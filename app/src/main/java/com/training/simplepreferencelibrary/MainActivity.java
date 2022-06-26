package com.training.simplepreferencelibrary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import java.util.Set;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "MyMainActivity";

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get NavHost and NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        // Get AppBarConfiguration
        // (ktx eg) appBarConfiguration = AppBarConfiguration(navController.graph);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        // Link ActionBar with NavController
        // (ktx eg) setupActionBarWithNavController(navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // (ktx eg) return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    // Called only after the Preference value is changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key == getResources().getString(R.string.key_status)) {
            String newStatus = sharedPreferences.getString(key, "");
            Toast.makeText(this, "New Status: " + newStatus, Toast.LENGTH_SHORT).show();
        }

        if(key == getResources().getString(R.string.key_auto_reply)) {
            boolean autoReply = sharedPreferences.getBoolean(key, false);

            if (autoReply) {
                Toast.makeText(this, "Auto Reply: ON", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Auto Reply: OFF", Toast.LENGTH_SHORT).show();
            }
        }

        if(key == getResources().getString(R.string.key_public_info)) {
            Set<String> publicInfo = sharedPreferences.getStringSet(key, null);
            Log.i(TAG, "Public Info: " + publicInfo.toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}