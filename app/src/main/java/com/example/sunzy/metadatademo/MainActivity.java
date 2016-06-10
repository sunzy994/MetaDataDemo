package com.example.sunzy.metadatademo;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这种方法是不行的，得到的bundle=null
        //Bundle bundle = getApplicationInfo().metaData;


        Bundle bundle = null;
        try {
            bundle = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData;
        } catch (Exception e){
            Log.wtf("sunzy", e);
        }
        Log.v("sunzy", "bundle = " + bundle);
        if(bundle != null){
            for(String str : bundle.keySet()){
                Log.v("sunzy", "key = " + str + ", value = " + bundle.get(str));
            }
        }

        //通过这种方式得到activity的meta data,两个flag必须都要有
        try {
            ActivityInfo info  = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES|PackageManager.GET_META_DATA).activities[0];
            bundle = info.metaData;
            Log.v("sunzy", "target = " + info.targetActivity + ", bundle = " + info.metaData);
        } catch (Exception e){
            Log.wtf("sunzy", e);
        }

        Log.v("sunzy", "bundle = " + bundle);
        if(bundle != null){
            for(String str : bundle.keySet()){
                Log.v("sunzy", "key = " + str + ", value = " + bundle.get(str));
            }
        }

        //Log.v("sunzy", "baidu key = " + getApplicationInfo().metaData.get("baidu_key"));
    }
}
