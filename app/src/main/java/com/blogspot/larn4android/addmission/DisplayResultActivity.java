package com.blogspot.larn4android.addmission;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DisplayResultActivity extends AppCompatActivity {

    private TextView tvroll,tvunit,tvcenter,tvbuilding,tvroom;

    private String roll,center,building,room;
    private String unit;

    private String TAG = "DisplayResultActivity.this";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        tvunit = (TextView)findViewById(R.id.textViewUnit);
        tvroll = (TextView)findViewById(R.id.tv_roll);
        tvbuilding = (TextView)findViewById(R.id.building);
        tvcenter = (TextView)findViewById(R.id.center);
        tvroom = (TextView)findViewById(R.id.room);

        roll = getIntent().getStringExtra("roll");
        center = getIntent().getStringExtra("center");
        building = getIntent().getStringExtra("building");
        room = getIntent().getStringExtra("room");

        if(building.equals(""))building="N/A";
        if(room.equals(""))room="N/A";
        int rollNum = Integer.parseInt(roll);
        if(rollNum>=40001&&rollNum<=67500 ){
            unit = "Unit-B";
        }
        else if(rollNum>=10001&&rollNum<=33700 ){
            unit = "Unit-A";
        }
        else if(rollNum>=70001&&rollNum<=80193){
            unit = "Unit-C";
        }
        else if(rollNum>=90001||rollNum<=95498){
            unit = "Unit-D";
        }
        tvunit.setText(unit);
        tvcenter.setText("Center : "+ center);
        tvroll.setText("Roll No. "+roll);
        tvroom.setText("Room No : "+room);
        tvbuilding.setText("Building : "+building);


        Button btn = (Button)findViewById(R.id.mapView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean connected = false;

                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else
                    connected = false;

                if(connected) {


                    try {

                        AssetManager am1=getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
                        InputStream is1=am1.open("center.xls");
                        Workbook wb1 =Workbook.getWorkbook(is1);
                        Sheet s1=wb1.getSheet(0);
                        int row1 =s1.getRows();
                        String xx="";
                        for(int r=0;r<row1;r++)
                        {
                            Cell z1=s1.getCell(0,r);
                            xx=z1.getContents();
                            Log.d(TAG,xx);
                            if(center.equals(xx)){
                                z1 = s1.getCell(1,r);
                                center = z1.getContents();
                            }
                        }
                    }

                    catch (Exception e)
                    {

                    }


                    Log.d(TAG,center);
                    if(center!=null && !center.equals("")){
                        new GeocoderTask().execute(center);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please cheak your Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });



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

    // An AsyncTask class for accessing the GeoCoding Web Service
    private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

        @Override
        protected List<Address> doInBackground(String... locationName) {
            // Creating an instance of Geocoder class
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = null;

            try {
                // Getting a maximum of 3 Address that matches the input text
                addresses = geocoder.getFromLocationName(locationName[0], 3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> addresses) {

            if (addresses == null || addresses.size() == 0) {
                Toast.makeText(getBaseContext(), "No Location found", Toast.LENGTH_LONG).show();
            }
            else {

                Intent i = new Intent(DisplayResultActivity.this, MapsActivity.class);



                i.putExtra("Center", center);
                startActivity(i);
            }

            }
        }


//    public void Location(View view){
//        Intent intent=new Intent(this,MapsActivity.class);
//        intent.putExtra("Center",stringSeat);
//        startActivity(intent);
//    }

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
