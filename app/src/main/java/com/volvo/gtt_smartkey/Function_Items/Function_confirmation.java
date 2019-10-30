package com.volvo.gtt_smartkey.Function_Items;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wear.widget.CircularProgressLayout;
import android.support.wearable.activity.ConfirmationActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volvo.gtt_smartkey.R;
import com.volvo.gtt_smartkey.SplashScreen.SocketService;


import static com.volvo.gtt_smartkey.SplashScreen.SocketService.door_lock;
import static com.volvo.gtt_smartkey.SplashScreen.SocketService.door_unlock;
import static com.volvo.gtt_smartkey.SplashScreen.SocketService.hazardlight_off;
import static com.volvo.gtt_smartkey.SplashScreen.SocketService.hazardlight_on;
import static com.volvo.gtt_smartkey.Utils.Constants.isLightOn;
import static com.volvo.gtt_smartkey.Utils.Constants.status_value;

public class Function_confirmation extends Activity implements
        CircularProgressLayout.OnTimerFinishedListener, View.OnClickListener {

    TextView textView;
    CircularProgressLayout circularProgress;
    ImageView accept, reject;
    String action;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_confirmation);

        textView = (TextView)findViewById(R.id.title);
        accept = (ImageView) findViewById(R.id.accept);
        reject = (ImageView) findViewById(R.id.reject);

        circularProgress =
                (CircularProgressLayout) findViewById(R.id.circular_progress);
        circularProgress.setOnTimerFinishedListener(this);
        circularProgress.setOnClickListener(this);

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            status =  bundle.get("type").toString();
            Log.e("SocketTest", status);

            switch (status) {
                case "door" :
                    if(SocketService.door_status.equals("LOCK")){
                        textView.setText("Allow Doors of the truck be opened?");
                        break;
                    }
                    else if(SocketService.door_status.equals("UNLOCK")) {
                        textView.setText("Allow Doors of the truck be closed?");
                        break;
                    }
                case "headlamp" :
                    if(isLightOn){
                        textView.setText("Allow Headlamps to be switched off?");
                        break;
                    }
                    else if(!isLightOn) {
                        textView.setText("Allow Headlamps to be switched on?");
                        break;
                    }
                case "hazardlight" : if(SocketService.hazardlight_status.equals("ON")){
                    textView.setText("Allow HAzard Lights to be switched off?");

                    break;
                }
                else if(SocketService.hazardlight_status.equals("OFF")) {
                    textView.setText("Allow Hazard Lights to be switched on?");
                    break;
                }
                case "hvac" : textView.setText("Allow Function_Hvac to be controlled?");
                    break;
            }

        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circularProgress.setTotalTime(1000);
                circularProgress.startTimer();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

//    @Override
//    public void onTimerFinished(CircularProgressLayout circularProgressLayout) {
//
//        switch (status) {
//            case "door" :
//                if(SocketService.door_status.equals("LOCK")){
//
//                    action = "Door Unlocked";
//                    door_unlock();
//                    break;
//                }
//                else if(SocketService.door_status.equals("UNLOCK")) {
//                    action = "Door Locked";
//                    door_lock();
//                    break;
//                }
//            case "headlamp" :
//                if(SocketService.headlight_status.equals("ON")){
//                    action = "Headlamps switched off";
//                    headlight_off();
//                    break;
//                }
//                else if(SocketService.headlight_status.equals("OFF")) {
//                    action = "Headlamps switched on";
//                    headlight_on();
//                    break;
//                }
//            case "hazardlight" : if(SocketService.hazardlight_status.equals("ON")){
//                action = "Hazard Lights switched off";
//                hazardlight_off();
//                break;
//            }
//            else if(SocketService.hazardlight_status.equals("OFF")) {
//                action = "Hazard Lights switched on";
//                hazardlight_on();
//                break;
//            }
//            case "hvac" :
//                switch (Function_Hvac_Test.status_value) {
//                    case 0 : Log.e("HVAC", "OFF"); break;
//                    case 1 : Log.e("HVAC", "LOW"); break;
//                    case 2 : Log.e("HVAC", "MID"); break;
//                    case 3 : Log.e("HVAC", "HIGH"); break;
//                }
//                action = "HVAC mode changed";
//                break;
//        }

    @Override
    public void onTimerFinished(CircularProgressLayout circularProgressLayout) {

        switch (status) {
            case "door" :
                if(SocketService.door_status.equals("LOCK")){

                    action = "Door Unlocked";
                    door_unlock();
                    break;
                }
                else if(SocketService.door_status.equals("UNLOCK")) {
                    action = "Door Locked";
                    door_lock();
                    break;
                }
            case "headlamp" :
                if(isLightOn) {
//                    Function_HeadLamp_Test.REGISTER_URL = "http://192.168.12.1:33080/api/vds/switchOffLight";
//                    Function_HeadLamp_Test.item_name.setText("OFF");
                    action = "Headlamp switched OFF";

//                    isLightOn = false;
                    Log.e("HEADLAMP",  String.valueOf(isLightOn));
                    break;
                } else if(!isLightOn) {
//                    Function_HeadLamp_Test.REGISTER_URL = "http://192.168.12.1:33080/api/vds/switchOnLight";
//                    Function_HeadLamp_Test.item_name.setText("ON");
                    action = "Headlamp switched ON";
//                    isLightOn = true;
                    Log.e("HEADLAMP",  String.valueOf(isLightOn));
                    break;
                }
            case "hazardlight" : if(SocketService.hazardlight_status.equals("ON")){
                action = "Hazard Lights switched off";
                hazardlight_off();
                break;
            }
            else if(SocketService.hazardlight_status.equals("OFF")) {
                action = "Hazard Lights switched on";
                hazardlight_on();
                break;
            }
            case "hvac" :
                switch (status_value) {
                    case 0 :
                        Log.e("HVAC", "SOFF");
                        break;
                    case 1 :
                        Log.e("HVAC", "LOW");
                        break;
                    case 2 :
                        Log.e("HVAC", "Medium");
                        break;
                    case 3 :
                        Log.e("HVAC", "High");
                        break;
                }
                action = "HVAC mode changed";
                break;
        }

        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                ConfirmationActivity.SUCCESS_ANIMATION);
        intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                action);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(circularProgress)) {
            //mhv
        }

    }

}
