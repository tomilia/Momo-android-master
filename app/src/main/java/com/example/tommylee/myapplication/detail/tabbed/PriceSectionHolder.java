package com.example.tommylee.myapplication.detail.tabbed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tommylee.myapplication.R;

/**
 * Created by tommylee on 4/1/2018.
 */

public class PriceSectionHolder extends RecyclerView.ViewHolder {

    TextView tv1,tv2,tv3;
    ImageView imageView;

    public PriceSectionHolder(View itemView) {
        super(itemView);

        tv1= (TextView) itemView.findViewById(R.id.list_title);
        tv2= (TextView) itemView.findViewById(R.id.list_desc);
        tv3= (TextView) itemView.findViewById(R.id.list_price);
        imageView= (ImageView) itemView.findViewById(R.id.list_avatar);

    }

}