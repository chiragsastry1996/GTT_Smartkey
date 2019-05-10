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
import com.volvo.gtt_smartkey.Utils.RegisterUserClass;

import static com.volvo.gtt_smartkey.Utils.Constants.isLightOn;

public class Function_HeadLamp_Test extends WearableActivity {

    public static TextView item_name;
    ImageView item_image;
    public static String REGISTER_URL;

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

//        item_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isLightOn) {
//                    REGISTER_URL = "http://192.168.12.1:33080/api/vds/switchOffLight";
//                    item_name.setText("OFF");
//                    Intent intent = new Intent(Function_HeadLamp_Test.this, Function_confirmation.class);
//                    intent.putExtra("type", "headlamp");
//                    startActivity(intent);
//                    finish();
//                    isLightOn = false;
//                } else if(!isLightOn) {
//                    REGISTER_URL = "http://192.168.12.1:33080/api/vds/switchOnLight";
//                    item_name.setText("ON");
//                    Intent intent = new Intent(Function_HeadLamp_Test.this, Function_confirmation.class);
//                    intent.putExtra("type", "headlamp");
//                    startActivity(intent);
//                    finish();
//                    isLightOn = true;
//                }
//            }
//        });

        item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Function_HeadLamp_Test.this, Function_confirmation.class);
                intent.putExtra("type", "headlamp");
                startActivity(intent);
            }
        });



    }

    public static void register() {
        class RegisterUser extends AsyncTask<String, Void, String> {
            RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

//                HashMap<String, String> data = new HashMap<String, String>();
//                data.put("name", params[0]);
//                data.put("clg", params[1]);
//                data.put("num", params[2]);
//                data.put("email", params[3]);
//                data.put("sub", params[4]);

                String result = ruc.sendPostRequest(REGISTER_URL);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();
    }

}
