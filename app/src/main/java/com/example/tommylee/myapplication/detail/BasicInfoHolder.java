package com.example.tommylee.myapplication.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tommylee.myapplication.R;

/**
 * Created by tommylee on 2/1/2018.
 */

public class BasicInfoHolder extends RecyclerView.ViewHolder {

    TextView tv1,tv2;

    public BasicInfoHolder(View itemView) {
        super(itemView);

        tv1= (TextView) itemView.findViewById(R.id.infokey);
        tv2= (TextView) itemView.findViewById(R.id.infovalue);

    }


}
