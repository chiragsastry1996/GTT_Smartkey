package com.volvo.gtt_smartkey.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.volvo.gtt_smartkey.Function_Items.HVAC;
import com.volvo.gtt_smartkey.Function_Items.Function_General;
import com.volvo.gtt_smartkey.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    List<Model> model;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;
        View circle1, circle2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            textView = (TextView)itemView.findViewById(R.id.function_name);
            imageView = (ImageView)itemView.findViewById(R.id.circledImageView);

        }
    }

    public Adapter(Context mContext, List<Model> model) {
        this.mContext = mContext;
        this.model = model;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.circle_view, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder myViewHolder, final int position) {
//        Glide.with(mContext).asGif().load(R.raw.image_gif).into(myViewHolder.imageView);
        switch (position){
            case 0 : myViewHolder.textView.setText("Door Lock/Unlock");
                     myViewHolder.imageView.setBackgroundResource(R.drawable.door);
                     break;
            case 1 : myViewHolder.textView.setText("Head Lamp");
                myViewHolder.imageView.setBackgroundResource(R.drawable.headlight);
                break;
            case 2 : myViewHolder.textView.setText("Hazard Lighting");
                myViewHolder.imageView.setBackgroundResource(R.drawable.hazard_light);
                break;
            case 3 : myViewHolder.textView.setText("HVAC");
                myViewHolder.imageView.setBackgroundResource(R.drawable.hvac);
                break;
        }

        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 3) {
                    Intent intent = new Intent(mContext, HVAC.class);
                    intent.putExtra("position", position);
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, Function_General.class);
                    intent.putExtra("position", position);
                    mContext.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }

}


