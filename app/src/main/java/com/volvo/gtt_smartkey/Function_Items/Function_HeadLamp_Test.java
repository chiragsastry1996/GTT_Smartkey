package com.volvo.gtt_smartkey.Function_Items;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volvo.gtt_smartkey.R;
import com.volvo.gtt_smartkey.Utils.HttpHandler;
import com.volvo.gtt_smartkey.Utils.RegisterUserClass;

import static com.volvo.gtt_smartkey.Utils.Constants.isLightOn;

public class Function_HeadLamp_Test extends WearableActivity {

    public static TextView item_name;
    ImageView item_image;
    public static String REGISTER_URL = "http://192.168.43.156/gpio.php?off=OFF";
    public static String REGISTER_URL1 = "http://192.168.43.156/gpio.php?LbOn=LoBeam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_headlamp_test);

        item_name = (TextView)findViewById(R.id.function_item_name);
        if(isLightOn) {
            item_name.setText("ON");
        }else if(!isLightOn) {
            item_name.setText("OFF");
        }
        item_image = (ImageView) findViewById(R.id.function_item_image);
        item_image.setBackgroundResource(R.drawable.headlight);

        item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new GetDetails1().execute();
            }
        });

//        item_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(Function_HeadLamp_Test.this, Function_confirmation.class);
////                intent.putExtra("type", "headlamp");
////                startActivity(intent);
//            }
//        });



    }

    public class GetDetails1 extends AsyncTask<Void, Void, Void> {
        private static final String TAG = "MainActivity";
        @Override
        public void onPreExecute() {
            super.onPreExecute();
                 REGISTER_URL1 = "http://192.168.43.156/gpio.php?LbOn=LoBeam";

        }

        //GET request done in background
        @Override
        public Void doInBackground(Void... arg0) {
            //Calling the HTTPHandler
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(REGISTER_URL1);


            Log.e(TAG, "Response from url1: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Do nothing

                }

                catch (final Exception e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
            new GetDetails().execute();

        }
    }

    public class GetDetails extends AsyncTask<Void, Void, Void> {
        private static final String TAG = "MainActivity";
        String sys_id = "empty";
        @Override
        public void onPreExecute() {
            super.onPreExecute();
            if(isLightOn) {
                REGISTER_URL = "http://192.168.43.156/gpio.php?off=OFF";
            } else if(!isLightOn) {
                REGISTER_URL = "http://192.168.43.156/gpio.php?HbOn=HiBeam";
            }
        }

        //GET request done in background
        @Override
        public Void doInBackground(Void... arg0) {
            //Calling the HTTPHandler
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(REGISTER_URL);


            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    //Do nothing

                    }

                catch (final Exception e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(isLightOn) {
                            item_name.setText("OFF");
                            finish();
                            isLightOn = false;
                        } else if(!isLightOn) {
                            item_name.setText("ON");
                            finish();
                            isLightOn = true;
                        }
                    }
                });
        }
    }

}
