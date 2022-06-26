package com.training.simplepreferencelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
}