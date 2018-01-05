package com.example.tommylee.myapplication.detail.tabbed;

/**
 * Created by tommylee on 17/11/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.detail.tabbed.PriceSectionHolder;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;


public class PriceSectionAdapter extends RecyclerView.Adapter<PriceSectionHolder> {
    private ArrayList<DataFetch> mResults= new ArrayList<>();

    Context context;
    LayoutInflater inflater;
    public PriceSectionAdapter(Context context, ArrayList<DataFetch> result) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        mResults=result;
    }
    @Override
    public PriceSectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.detail_price_list_view, parent, false);

        PriceSectionHolder viewHolder=new PriceSectionHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PriceSectionHolder holder, int position) {
        holder.tv1.setText(mResults.get(position).getDescription());
        holder.tv2.setText(mResults.get(position).getImage_link());
        holder.tv3.setText("¥"+mResults.get(position).getAveragePrice());
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            PriceSectionHolder vholder = (PriceSectionHolder) v.getTag();
            int position = vholder.getPosition();

            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        }
    };



    @Override
    public int getItemCount() {
        return mResults.size();
    }

}