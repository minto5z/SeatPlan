package com.blogspot.larn4android.addmission;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import com.blogspot.larn4android.addmission.unit.AUnitActivity;
import com.blogspot.larn4android.addmission.unit.BUnitActivity;
import com.blogspot.larn4android.addmission.unit.CUnitActivity;
import com.blogspot.larn4android.addmission.unit.DUnitActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public  String InputRoll ="" ;
    int len=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = (Button)findViewById(R.id.btnA);
        Button two = (Button)findViewById(R.id.btnB);
        Button three = (Button)findViewById(R.id.btnC);
        Button four = (Button)findViewById(R.id.btnD);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        BounceInterpolator interpolator = new BounceInterpolator();
        myAnim.setInterpolator(interpolator);
        one.startAnimation(myAnim);
        two.startAnimation(myAnim);
        three.startAnimation(myAnim);
        four.startAnimation(myAnim);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void onClick(View view) {
        Intent i;
        String stringSeat = "";
        int len = 0;

        switch (view.getId()) {

            case R.id.btnA:

                i = new Intent (this,AUnitActivity.class);
                startActivity(i);
                break;

            case R.id.btnB:


                i = new Intent (this,BUnitActivity.class);
                startActivity(i);
                break;

            case R.id.btnC:

                i = new Intent (this,CUnitActivity.class);
                startActivity(i);

                break;
            case R.id.btnD:
                i = new Intent (this,DUnitActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //ShareActionProvider mShareActionProvider;
        getMenuInflater().inflate(R.menu.main, menu);

//        MenuItem item = menu.findItem(R.id.menu_item_share);
//        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
//        //create the sharing intent
//        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//        sharingIntent.setType("text/plain");
//        String shareBody = "here goes your share content body";
//        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Subject");
//        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//
//        //then set the sharingIntent
//        mShareActionProvider.setShareIntent(sharingIntent);

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

            case R.id.action_settings:
                Intent i = new Intent(this,PictureActivity.class);
                startActivity(i);
                break;
            case R.id.about:
                i = new Intent(this,AboutUs.class);
                startActivity(i);
                break;
            case R.id.help:
                i = new Intent(this,Help.class);
                startActivity(i);
                break;
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

            //About
            Intent i = new Intent(this,AboutUs.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {

            Intent i = new Intent(this,PictureActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            //help
            Intent i = new Intent(this,Help.class);
            startActivity(i);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
