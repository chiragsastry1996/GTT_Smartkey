package com.volvo.gtt_smartkey.MainActivity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageView;

import com.volvo.gtt_smartkey.R;
import com.volvo.gtt_smartkey.Utils.CenterZoomLayoutManager;
import com.volvo.gtt_smartkey.Utils.LinePagerIndicatorDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WearableActivity {

    private List<Model> feedList;
    private Adapter adapter;
    private RecyclerView recyclerView;
    public static ImageView backbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        feedList = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        backbutton = (ImageView)findViewById(R.id.back);
        backbutton.setVisibility(View.VISIBLE);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final RecyclerView.LayoutManager mLayoutManager = new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        feedList.add(new Model("ABCD"));
        adapter = new Adapter(MainActivity.this, feedList);
        recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    backbutton.setVisibility(View.GONE);
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int firstVisibleItem = ((CenterZoomLayoutManager) mLayoutManager).findFirstCompletelyVisibleItemPosition();
            if(firstVisibleItem == 0) {
                backbutton.setVisibility(View.VISIBLE);
            }
            /* Log.e ("VisibleItem", String.valueOf(firstVisibleItem));*/

        }
    });

    }

}
