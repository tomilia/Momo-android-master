package com.example.tommylee.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tommylee on 17/12/2017.
 */

public class Result_View_Holder extends RecyclerView.ViewHolder {

        TextView tv1,tv2,tv3;
        ImageView imageView;

        public Result_View_Holder(View itemView) {
            super(itemView);

            tv1= (TextView) itemView.findViewById(R.id.list_title);
            tv2= (TextView) itemView.findViewById(R.id.list_desc);
            tv3= (TextView) itemView.findViewById(R.id.list_price);
            imageView= (ImageView) itemView.findViewById(R.id.list_avatar);

        }

}
