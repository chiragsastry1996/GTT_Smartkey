package com.volvo.gtt_smartkey.Function_Items;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.ConfirmationActivity;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.volvo.gtt_smartkey.R;

import static com.volvo.gtt_smartkey.Utils.Constants.status_value;

public class Function_Hvac_Test extends WearableActivity {

    TextView increase, decrease, display;
    String[] status = new String[]{"OFF", "LOW", "MID", "HIGH"};
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_hvac_test);

        increase = (TextView)findViewById(R.id.increase);
        decrease = (TextView)findViewById(R.id.decrease);
        display = (TextView)findViewById(R.id.display);
        set = (Button)findViewById(R.id.set);

        if(status_value == 0)
        decrease.setVisibility(View.GONE);
        else if(status_value == 3)
            increase.setVisibility(View.GONE);
        display.setText(String.valueOf(status[status_value]));


        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status_value<3) {
                    status_value++;
                    if(status_value == 3) {
                        increase.setVisibility(View.GONE);
                        display.setText(String.valueOf(status[status_value]));
                    } else {
                        decrease.setVisibility(View.VISIBLE);
                        increase.setVisibility(View.VISIBLE);
                        display.setText(String.valueOf(status[status_value]));
                    }

                }

            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status_value>0) {
                    status_value--;
                    if(status_value == 0) {
                    decrease.setVisibility(View.GONE);
                        display.setText(String.valueOf(status[status_value]));
                } else {
                        increase.setVisibility(View.VISIBLE);
                        decrease.setVisibility(View.VISIBLE);
                        display.setText(String.valueOf(status[status_value]));
                    }

                }

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Function_Hvac_Test.this, Function_confirmation.class);
                intent.putExtra("type", "hvac");
                startActivity(intent);
            }
        });

    }
}


