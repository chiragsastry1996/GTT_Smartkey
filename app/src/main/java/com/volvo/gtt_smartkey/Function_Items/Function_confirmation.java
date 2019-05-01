package com.volvo.gtt_smartkey.Function_Items;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.wear.widget.CircularProgressLayout;
import android.support.wearable.activity.ConfirmationActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.volvo.gtt_smartkey.R;

import static java.lang.Integer.parseInt;

public class Function_confirmation extends Activity implements
        CircularProgressLayout.OnTimerFinishedListener, View.OnClickListener {

    TextView textView;
    CircularProgressLayout circularProgress;
    ImageView accept, reject;
    String action;

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
            String status =  bundle.get("position").toString();

            switch (parseInt(status)) {
                case 0 : textView.setText("Allow Doors of the truck be opened?");
                    action = "Door Unlocked";
                    break;
                case 1 : textView.setText("Allow Headlamps to be switched on?");
                    action = "Headlamps switched on";
                    break;
                case 2 : textView.setText("Allow Hazard Lights to be switched on?");
                    action = "Hazard lights switched on";
                    break;
                case 3 : textView.setText("Allow HVAC to be controlled?");
                    action = "HVAC value changed";
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

    @Override
    public void onTimerFinished(CircularProgressLayout circularProgressLayout) {
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
