package com.volvo.gtt_smartkey.Function_Items;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.volvo.gtt_smartkey.R;
import com.volvo.gtt_smartkey.SplashScreen.SocketService;

public class Function_Hazardlight extends Activity {

    public static TextView item_name;
    public static ImageView item_image;
    public static String hazardlight = "nothing";
    private DataUpdateReceiver dataUpdateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function__hazardlight);

        item_name = (TextView)findViewById(R.id.function_item_name);
        item_image = (ImageView) findViewById(R.id.function_item_image);

        item_name.setText(SocketService.hazardlight_status);

        item_image.setBackgroundResource(R.drawable.hazard_light);

        item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SocketService.hazardlight_status.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Unknown Status", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Function_Hazardlight.this, Function_confirmation.class);
                    intent.putExtra("type", "hazardlight");
                    startActivity(intent);
                    finish();
                }

            }
        });

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            String status =  bundle.get("position").toString();
        }

    }

    @Override
    protected void onResume() {

        if (dataUpdateReceiver == null) dataUpdateReceiver = new DataUpdateReceiver();
        IntentFilter intentFilter = new IntentFilter("hazardlight_status");
        registerReceiver(dataUpdateReceiver, intentFilter);

        super.onResume();
    }

    @Override
    protected void onPause() {

        if (dataUpdateReceiver != null) unregisterReceiver(dataUpdateReceiver);

        super.onPause();
    }

    private class DataUpdateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("hazardlight_status")) {
                if(intent.getExtras().get("hazardlight_status").equals("ON")) {
                    hazardlight = "ON";
                    item_name.setText(hazardlight);
                } else if(intent.getExtras().get("hazardlight_status").equals("OFF")) {
                    hazardlight = "OFF";
                    item_name.setText(hazardlight);
                }

            }
        }
    }



}
