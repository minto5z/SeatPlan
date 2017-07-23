package com.blogspot.larn4android.addmission;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed By Mahadiur Jaman Minto", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        switch (AppCompatDelegate.getDefaultNightMode()) {
//            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
//                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_AUTO:
//                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_YES:
//                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_NO:
//                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//            case R.id.menu_night_mode_system:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                break;
//            case R.id.menu_night_mode_day:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                break;
//            case R.id.menu_night_mode_night:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                break;
//            case R.id.menu_night_mode_auto:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
//        AppCompatDelegate.setDefaultNightMode(nightMode);
//
//        if (Build.VERSION.SDK_INT >= 11) {
//            recreate();
//        }
//    }

}
