package com.example.iloventub;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Service;


import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private Button DesBtn, FpBtn, TiBtn, DiBtn, FoodBtn, EndBtn, AboutUsBtn,UsageBtn, WmiBtn;
    Vibrator mVibrator;

    //===================================
    LocationManager locationManager;
    double toLat = 25.033611;  //25.042469, 121.525471
    double toLon = 121.565000;
    double fromLat;
    double fromLon;
    String bestProv;
    private Location currentLocation;
    int minTime = 1000; // 毫秒
    float minDistance = 5; // 公尺
//===================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DesBtn = (Button)findViewById(R.id.DesBtn);
        FpBtn = (Button)findViewById(R.id.FpBtn);
        TiBtn = (Button)findViewById(R.id.TiBtn);
        DiBtn = (Button)findViewById(R.id.DiBtn);
        FoodBtn = (Button)findViewById(R.id.FoodBtn);
        EndBtn = (Button)findViewById(R.id.EndBtn);
        AboutUsBtn = (Button)findViewById(R.id.AboutUsBtn);
        UsageBtn = (Button)findViewById(R.id.UsageBtn);
        WmiBtn = (Button)findViewById(R.id.WmiBtn);

        DesBtn.setOnClickListener(btnDesBtnListener);
        FpBtn.setOnClickListener(btnFpBtnListener);
        TiBtn.setOnClickListener(btnTiBtnListener);
        DiBtn.setOnClickListener(btnDiBtnListener);
        FoodBtn.setOnClickListener(btnFoodBtnListener);
        EndBtn.setOnClickListener(btnEndBtnListener);
        AboutUsBtn.setOnClickListener(btnAboutUsBtnListener);
        UsageBtn.setOnClickListener(btnUsageBtnListener);
        WmiBtn.setOnClickListener(btnWmiBtnListener);
    }

    private Button.OnClickListener btnDesBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            //setTitle("Hello NTUB");
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener btnFpBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            //setTitle("Hello Fooler Plan");
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main3Activity.class);
            startActivity(intent);

        }
    };

    private Button.OnClickListener btnTiBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            //setTitle("Hello Traffic Information");
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main4Activity.class);
            startActivity(intent);

        }
    };

    private Button.OnClickListener btnDiBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            //setTitle("Hello Department Introduce");
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main5Activity.class);
            startActivity(intent);

        }
    };

    private Button.OnClickListener btnFoodBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            //setTitle("Hello FOOOOOOOODDDDDD!!!!!!!!");
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main6Activity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener btnAboutUsBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main7Activity.class);
            startActivity(intent);
        }
    };

    private Button.OnClickListener btnUsageBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent();
            intent.setClass(MainActivity.this,Main8Activity.class);
            startActivity(intent);
        }
    };

    private  Button.OnClickListener btnWmiBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v)  {
        }
    };

    private Button.OnClickListener btnEndBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            finish();

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onLocationChanged(Location location) {
//
//        StringBuffer str = new StringBuffer();
//        str.append("定位提供者(Provider)ÿ "+location.getProvider());
//        str.append("\n 緯度(Latitude)ÿ " + Double.toString(location.getLatitude()));
//        str.append("\n 經度(Longitude)ÿ " + Double.toString(location.getLongitude()));
//        str.append("\n 高度(Altitude)ÿ " + Double.toString(location.getAltitude()));
//        String x="緯度=" + Double.toString(location.getLatitude());
//        String y="經度=" + Double.toString(location.getLongitude());
//        String z="定位提供者=" +location.getProvider();
//        Toast.makeText(this, x + "\n" + y+ "\n"+ z, Toast.LENGTH_LONG).show();

        float[] result = new float[1];
        mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042443,  //25.043188, 121.526505
                121.525503,result);
        if(result[0] < 100.00) {
            mVibrator.vibrate(new long[]{1000, 3000, 1000, 3000, 1000, 3000},-1);
            Toast.makeText(this, "【在校區內】",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "【不在校區內】" + "\n"+"距離"+ result[0] + " 公尺", Toast.LENGTH_LONG).show();
        }

        //承曦樓	25.042839, 121.524827
        //圖書館	25.042535, 121.524792
        //六藝樓	25.042032, 121.525111
        //行政大樓	25.041889, 121.525953
        //學生活動中心	25.042329, 121.526004
        //中正紀念館	25.042654, 121.526200
        //五育樓	25.042810, 121.525535
        float[] results = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042839,  //25.043188, 121.526505
                121.524827,results);
        if(results[0] < 30.00){
            Toast.makeText(this, "承曦樓",Toast.LENGTH_LONG).show();
        }

        float[] results1 = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042535, 121.524792,results1);
        if(results1[0] < 30.00){
            Toast.makeText(this, "圖書館",Toast.LENGTH_LONG).show();
        }
        float[] results2 = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042032, 121.525111,results2);
        if(results2[0] < 30.00){
            Toast.makeText(this, "六藝樓",Toast.LENGTH_LONG).show();
        }
        float[] results3 = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.041889, 121.525953,results3);
        if(results3[0] < 30.00){
            Toast.makeText(this, "行政大樓",Toast.LENGTH_LONG).show();
        }

        float[] results4 = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042329, 121.526004,results4);
        if(results4[0] < 30.00){
            Toast.makeText(this, "學生活動中心",Toast.LENGTH_LONG).show();
        }
        float[] results6 = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042654, 121.526200,results6);
        if(results6[0] < 30.00){
            Toast.makeText(this, "中正紀念館",Toast.LENGTH_LONG).show();
        }
        float[] results7 = new float[1];
        Location.distanceBetween(location.getLatitude(),
                location.getLongitude(), 25.042810, 121.525535,results7);
        if(results7[0] < 30.00){
            Toast.makeText(this, "五育樓",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Criteria criteria = new Criteria();

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        //當 GPS 或網路定位功能關閉時
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //當系統進入onPause()時，則停止更新
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
                bestProv = locationManager.getBestProvider(criteria, true);//選擇精準度最高的提供者
        // 如果 GPS 或網路定位開啟，更新位置
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // 確認 ACCESS_FINE_LOCATION 權限是否授權
            if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) ==  PackageManager.PERMISSION_GRANTED) {
                //服務提供者、更新頻率 1000 毫秒=1 秒、最短距離、地點改變時呼叫物件
                locationManager.requestLocationUpdates(bestProv, minTime, minDistance,
                        this);
                        currentLocation = locationManager.getLastKnownLocation(bestProv);
                        fromLat = currentLocation.getLatitude();
                        fromLon = currentLocation.getLongitude();
                Toast.makeText(this, fromLat + "\n" + fromLon,
                        Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "權限未授權", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this,
                        new String[] {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},0
                );
            }
        } else {
            Toast.makeText(this, "請開啟定位服務", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//開啟設定頁面
        }
    }

    void getLocation(){
        Toast.makeText(this, "Location" ,
                Toast.LENGTH_LONG).show();
        try {
            locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
}
