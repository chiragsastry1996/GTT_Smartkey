package com.volvo.gtt_smartkey.Function_Items;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wear.widget.CircularProgressLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volvo.gtt_smartkey.R;

import static java.lang.Integer.parseInt;

public class Function_General extends Activity {

    public static TextView item_name;
    public static ImageView item_image;
    public static String door;
    public static String headlamp;
    public static String hazard_light;
    public static String engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_general);

        item_name = (TextView)findViewById(R.id.function_item_name);
        item_image = (ImageView) findViewById(R.id.function_item_image);

        set_state();

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            String status =  bundle.get("position").toString();
        }

    }

    public static void set_state() {
        item_name.setText("Door");
        item_image.setBackgroundResource(R.drawable.door);
    }

}
