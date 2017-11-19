package com.example.tommylee.myapplication;

/**
 * Created by tommylee on 17/11/2017.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;


public class SavedResultAdapter extends BaseAdapter {

    Context context;
    int layoutRes;
    private ArrayList<String> temp=new ArrayList<String>();
    LayoutInflater inflater;
    ListView mListView;
    public SavedResultAdapter(Context context,ArrayList<String> temp) {
        this.temp=temp;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return temp.size();
    }

    @Override
    public Object getItem(int position) {
        return temp.get(getCount()-position-1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder2 holder;
        String tempstr;
        Hawk.init(context).build();
        tempstr=temp.get(getCount()-position-1);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.savedresult_item, null);
            holder=new ViewHolder2();
            holder.saved_result=(TextView)convertView.findViewById(R.id.result_recent);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder2) convertView.getTag();
        }
        Log.d("tempstr",tempstr);
        holder.saved_result.setText(tempstr);

        ImageButton delete = convertView.findViewById(R.id.delete_recent);
        delete.setTag(getCount()-position-1);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arg0=(int)v.getTag();
Log.d("check11",String.valueOf(arg0));
                for(int a=0;a<temp.size();a++) {

                    Log.d("searcharray",a+" "+ temp.get(a));
                }
                int tempcount=(int)Hawk.count()-1;
                Log.d("searcharray2",String.valueOf(tempcount));
                for(int b=arg0;b<Hawk.count()-1;b++)
                {

                    Hawk.put(String.valueOf(b),Hawk.get(String.valueOf(b+1)).toString());
                    Log.d("checkp",b+" "+String.valueOf(b)+" "+Hawk.get(String.valueOf(b)).toString());
                }
                temp.remove(arg0);
                Hawk.delete(String.valueOf(tempcount));
                for(int c=0;c<Hawk.count();c++)
                    Log.d("yakuza",c+" "+ Hawk.get(String.valueOf(c)).toString());
               SavedResultAdapter.this.notifyDataSetChanged();
            }
        });


        notifyDataSetChanged();
        return convertView;
    }
    static class ViewHolder2
    {
        TextView saved_result;


    }
}