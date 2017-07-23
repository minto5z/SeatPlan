package com.blogspot.larn4android.addmission.unit;

import android.content.Intent;
import android.content.res.AssetManager;
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
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.larn4android.addmission.DisplayResultActivity;
import com.blogspot.larn4android.addmission.R;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DUnitActivity extends AppCompatActivity {

    private String InputRoll = "";
    private int len =0;
    private EditText etDunitRoll ;
    private String RollD = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dunit);


        etDunitRoll = (EditText)findViewById(R.id.etDunitRoll);
        Button btn = (Button)findViewById(R.id.btn_DunitRoll);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollD = etDunitRoll.getText().toString();
                len = RollD.length();
                InputRoll = RollD;
                if(!RollD.equals("")&&len==5) {
                    try {
                        int roll = parse(RollD);
                        if(roll<90001||roll>95498){
                            Toast.makeText(getApplicationContext(),"Please Enter Correct Roll Number!",Toast.LENGTH_SHORT).show();
                        }
                        else processRoll(RollD);
                    } catch (Exception e) {

                    }
                }else {

                    Toast.makeText(getApplicationContext(),"Please Enter your Correct 5 digit Roll",Toast.LENGTH_SHORT).show();
                }

            }
        });



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
    public int parse(String str)
    {
//        if(str==null)return  -1;
        if(str.equals("")) return  -1;
        int res = 0;
        for(int i=0;i<str.length();i++)
        {
            res = res*10 + str.charAt(i) -'0';

        }
        return res;
    }
    public void processRoll(String RollNum) throws Exception
    {






        try {


            int roll = parse(RollNum);

            ArrayList<String > ColList = new ArrayList<>();

            AssetManager am=getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            InputStream is=am.open("dunit2016.xls");
            Workbook wb =Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            int row =s.getRows();
            int col=s.getColumns();

            for (int c=0;c<col;c++)
            {

                Cell z=s.getCell(c,0);
                ColList.add(z.getContents());
            }

            int serialNo = 0;
            String Str []= new String[10] ;

            for(int i =0 ; i<10;i++)Str[i] = "";

            int flag =0;
            for(int i=1;i<row;i++)
            {

                Cell z=s.getCell(0,i);
                String tempSerial = z.getContents();
                int serial  = -1;


                if(tempSerial.equals("")  )serial = serialNo ;
                else{
                    serial = parse(z.getContents());
                }

                if(serial==0)continue;



                if(serial!=serialNo){
                    serialNo = serial;
                    for(int c = 0 ; c<col;c++){
                        Str[c] = s.getCell(c,i).getContents();
                    }
                }else{
                    for(int c = 0 ; c<col;c++){
                        String temp = s.getCell(c,i).getContents();
                        if(temp!="")Str[c] = temp;
                    }
                }

                int startRoll= 0 , endRoll = 0;

                for(int c = 0 ; c<col;c++){
                    if(ColList.get(c).equals("Start")) startRoll = Integer.parseInt(Str[c]);
                    if(ColList.get(c).equals("End") ) endRoll = Integer.parseInt(Str[c]);
                }


//                if(roll>=startRoll && roll<=endRoll)
//                {
//                    String Message = "InRoll" + RollNum +  " Roll = "+roll + " |" + startRoll + " ^^ " + endRoll + "##";
//                    for(int c = 0 ; c<col;c++){
//                        if(Str[c].equals("") ||ColList.get(c).equals("Start") || ColList.get(c).equals("End") );
//                        else {
//                            Message  = Message +  ColList.get(c) + " : ***** " + Str[c] + "\n";
//                        }
//                    }
//                    Log.d("MainActivity.this" , Message);
//                }
//

//                String Message = Message = "InRoll" + RollNum +  " Roll = "+roll;

                if(roll>=startRoll && roll<=endRoll)
                {
                    String buildingName = "", centerName ="" , roomNumber ="",RollNumber=InputRoll;
                    String Message = "";
                    flag =1;
                    for(int c = 0 ; c<col;c++){

                        if(Str[c].equals("") ||ColList.get(c).equals("Start") || ColList.get(c).equals("End") );
                        if(ColList.get(c).equals("Center/Institute"))centerName = Str[c];
                        else if(ColList.get(c).equals("Building/Block"))buildingName = Str[c];
                        else if(ColList.get(c).equals("Room"))roomNumber = Str[c];

                    }


                    Intent intent =new Intent(this,DisplayResultActivity.class);

                    intent.putExtra("roll",RollNumber );
                    intent.putExtra("building",buildingName);
                    intent.putExtra("center",centerName);
                    intent.putExtra("room",roomNumber);
                    startActivity(intent);

                }


            }
            if(flag==0){
                Toast.makeText(getApplicationContext(),"Please Enter your Correct 5 digit Roll",Toast.LENGTH_SHORT).show();
            }

        }

        catch (Exception e)
        {
            Log.d("MainActivity.this" , "Exception  ");
        }


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
