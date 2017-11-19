package com.example.tommylee.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

/**
 * Created by tommylee on 12/11/2017.
 */

class SearchResultAdapter extends BaseAdapter
{
    private LayoutInflater layoutInflater;

    private ArrayList<DataFetch> productDetails=new ArrayList<>();
    int count;
    Typeface type;
    Context context;

    //constructor method
    public SearchResultAdapter(Context context, ArrayList<DataFetch> product_details) {

        layoutInflater = LayoutInflater.from(context);

        this.productDetails=product_details;
        this.count= product_details.size();
        this.context = context;


    }

    @Override
    public int getCount() {
        return productDetails.size();
    }

    @Override
    public Object getItem(int arg0) {
        return productDetails.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder holder;
        DataFetch tempProduct;

        Log.d("mommy",String.valueOf(position));
        try {
           tempProduct = productDetails.get(position);
        }
        catch(IndexOutOfBoundsException e){
            Log.d("haha","bugs");
            tempProduct=null;
    }
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.product_name = (TextView) convertView.findViewById(R.id.product_name);
            holder.product_mrp = (TextView) convertView.findViewById(R.id.product_mrp);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(tempProduct!=null) {
            holder.product_name.setText(tempProduct.getDescription());

            holder.product_mrp.setText(tempProduct.getImage_link());
        }

        notifyDataSetChanged();
        return convertView;
    }

    static class ViewHolder
    {
        TextView product_name;
        TextView product_mrp;


    }

}

