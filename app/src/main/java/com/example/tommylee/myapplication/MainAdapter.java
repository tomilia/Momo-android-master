package com.example.tommylee.myapplication;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
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
import java.util.Random;

/**
 * Created by tommylee on 1/11/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DataFetch> mDataset;
    private Context context;
    private int type;
    public static int ITEM_TYPE_ROW=0;
    public static int ITEM_TYPE_COLUMN=1;
    private Random mRandom = new Random();
    public MainAdapter(Context context,ArrayList<DataFetch> mDataset,int type) {
        this.context=context;this.mDataset = mDataset;this.type=type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        View v;
            Log.d("cheess",String.valueOf(ViewType));
            if(ViewType ==ITEM_TYPE_ROW)
            {
                v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
                return new ViewHolder1(v);
            }
            else
            {
                v= LayoutInflater.from(context).inflate(R.layout.custom_view,parent,false);
                return new ViewHolder2(v);
            }

        }

@Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position){
    Log.d("csccs",String.valueOf(position));
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int height = size.y;
      if (holder instanceof ViewHolder1) {
            ((ViewHolder1)holder).mTitle.setText(mDataset.get(position).getDescription());
          ((ViewHolder1)holder).mAddress.setText(mDataset.get(position).getImage_link());
          Glide.with(context).load("https://image.freepik.com/free-photo/horizon-texture-aged-scene-weather_1088-693.jpg").into(((ViewHolder1)holder).mImage);
        }
        else if(holder instanceof ViewHolder2)
      {
            ViewHolder2 viewHolder2 = (ViewHolder2)holder;
            viewHolder2.mTextView.setText(mDataset.get(position).getDescription());

            Log.d("xxt",String.valueOf(height));
            switch(position) {
                case 0:
                    StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) viewHolder2.itemView.getLayoutParams();
                    layoutParams.setFullSpan(true);
                    Glide.with(context).load("http://is5.mzstatic.com/image/thumb/Purple1/v4/a2/c6/e1/a2c6e172-53a9-ba4e-79f8-5051d53989fd/source/175x175bb.jpg").into(((ViewHolder2)holder).mImage);
                    viewHolder2.mImage.getLayoutParams().height = (int)(height/4.6);
                    viewHolder2.mBlayer.getBackground().setAlpha(128);
                    viewHolder2.mRankIcon.setImageResource(R.drawable.medal1);
                    break;

                case 1:
                    viewHolder2.mImage.getLayoutParams().height=(height/6);
                    viewHolder2.mBlayer.getBackground().setAlpha(128);
                    viewHolder2.mRankIcon.setImageResource(R.drawable.medal1);
                    break;
                case 2:
                    viewHolder2.mImage.getLayoutParams().height=(int)(height/6);

                    viewHolder2.mRankIcon.setImageResource(R.drawable.medal1);
                    break;
            }
            // Set a random color for TextView background
    }


}
@Override
    public int getItemCount(){
    return mDataset.size();
}
    @Override
    public int getItemViewType(int position) {
        if (type==0) {
            return ITEM_TYPE_ROW;
        } else {
            return ITEM_TYPE_COLUMN;
        }
    }
    private class ViewHolder1 extends RecyclerView.ViewHolder{
        public TextView mTitle;
        public ImageView mImage;
        public TextView mAddress;
        public ViewHolder1(View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.recycler_title);
            mImage=itemView.findViewById(R.id.image);
            mAddress=itemView.findViewById(R.id.recycler_address);
        }
    }
    private class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View mBlayer;
        public ImageView mImage;
        public ImageView mRankIcon;
        public ViewHolder2(View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.tv);
            mImage=itemView.findViewById(R.id.rankimage);
            mRankIcon=itemView.findViewById(R.id.rankicon);
            mBlayer=itemView.findViewById(R.id.vw_blacklayer);
        }
    }
    protected int getRandomHSVColor(){
        // Generate a random hue value between 0 to 360
        int hue = mRandom.nextInt(361);
        // We make the color depth full
        float saturation = 1.0f;
        // We make a full bright color
        float value = 1.0f;
        // We avoid color transparency
        int alpha = 255;
        // Finally, generate the color
        int color = Color.HSVToColor(alpha, new float[]{hue, saturation, value});
        // Return the color
        return color;
    }
}
