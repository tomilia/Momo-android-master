package com.example.tommylee.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Created by tommylee on 5/1/2018.
 */

public class MainLocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private ArrayList<String> e=new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    public MainLocationAdapter(Context context, ArrayList<String> mData) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        e=mData;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position){
        Log.d("csccs",String.valueOf(position));
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

            LocationHolder viewHolder2 = (LocationHolder)holder;

                    Glide.with(context).load(R.drawable.tstpier).into((viewHolder2).imageView);
                 viewHolder2.tv1.setText("aaa");

            // Set a random color for TextView background



    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v = inflater.inflate(R.layout.main_location_row, parent, false);
                LocationHolder viewHolder = new LocationHolder(v);
                return viewHolder;


    }
    @Override
    public int getItemCount(){
        return e.size();
    }

    class LocationHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        ImageView imageView;

        public LocationHolder(View itemView) {
            super(itemView);

            tv1= (TextView) itemView.findViewById(R.id.list_title);
            imageView= (ImageView) itemView.findViewById(R.id.list_avatar);

        }

    }
}

