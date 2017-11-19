package com.example.tommylee.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tommylee on 18/11/2017.
 */public class smartsearchAdapter extends RecyclerView.Adapter<smartsearchAdapter.CustomViewHolder> {
    private String[] feedItemList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    public smartsearchAdapter(Context context,String[] feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }
    public interface OnItemClickListener {

        void onClick(View view, int position);
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selection_box, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view,mContext,feedItemList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        String feedItem = feedItemList[i];

        //Render image using Picasso library


        //Setting text view title
        customViewHolder.textView.setText(feedItem);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.length : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView textView;
        String[] list=new String[2];
        Context ctx;
        public CustomViewHolder(View view,Context ctx,String[] list) {
            super(view);
            this.list=list;
            this.ctx=ctx;
            view.setOnClickListener(this);
            this.textView = (TextView) view.findViewById(R.id.title);
        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            String name=this.list[position];
            Log.d("scaca",name);
        }
    }
}