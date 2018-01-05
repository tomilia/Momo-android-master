package com.example.tommylee.myapplication;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

/**
 * Created by tommylee on 16/12/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.xml.transform.Result;

public class Result_Page_Adapter extends RecyclerView.Adapter<Result_View_Holder> {
    private ArrayList<DataFetch> mResults= new ArrayList<>();

        Context context;
        LayoutInflater inflater;
        public Result_Page_Adapter(Context context, ArrayList<DataFetch> result) {
            this.context=context;
            inflater=LayoutInflater.from(context);
            mResults=result;
        }
        @Override
        public Result_View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v=inflater.inflate(R.layout.result_list_view, parent, false);

            Result_View_Holder viewHolder=new Result_View_Holder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(Result_View_Holder holder, int position) {
            holder.tv1.setText(mResults.get(position).getDescription());
            holder.tv2.setText(mResults.get(position).getImage_link());
            holder.tv3.setText(" ¥"+mResults.get(position).getAveragePrice());
            holder.imageView.setOnClickListener(clickListener);
            holder.imageView.setTag(holder);
        }

        View.OnClickListener clickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Result_View_Holder vholder = (Result_View_Holder) v.getTag();
                int position = vholder.getPosition();

                Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

            }
        };



        @Override
        public int getItemCount() {
            return mResults.size();
        }


}
