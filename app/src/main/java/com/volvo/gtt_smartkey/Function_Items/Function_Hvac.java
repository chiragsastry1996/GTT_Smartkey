package com.volvo.gtt_smartkey.Function_Items;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.volvo.gtt_smartkey.R;

public class Function_Hvac extends WearableActivity {

    TextView increase, decrease, display;
    Integer status = 16;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_hvac);

        increase = (TextView)findViewById(R.id.increase);
        decrease = (TextView)findViewById(R.id.decrease);
        display = (TextView)findViewById(R.id.display);
        set = (Button)findViewById(R.id.set);

        display.setText(String.valueOf(status));


        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status++;
                display.setText(String.valueOf(status));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status--;
                display.setText(String.valueOf(status));
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Function_Hvac.this, ConfirmationActivity.class);
                intent.putExtra(ConfirmationActivity.EXTRA_ANIMATION_TYPE,
                        ConfirmationActivity.SUCCESS_ANIMATION);
                intent.putExtra(ConfirmationActivity.EXTRA_MESSAGE,
                        "Function_Hvac set to" + status.toString());
                startActivity(intent);
                finish();
            }
        });

    }
}
