package com.example.tommylee.myapplication.detail;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.viewpager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tommylee on 17/12/2017.
 */

public class BasicInfoFrag extends Fragment {
    ViewPager viewPager;
    LinearLayout dots;
    private int dotscount;
    private ImageView[] dotsview;
    RecyclerView infolist;
    private LinkedHashMap<String, String> infoData = new LinkedHashMap<String, String>();
    private RecyclerView.Adapter resultmAdapter;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_basic_info,container,false);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Bundle fragdetail = getArguments();

        String[] fetchdetail;
        TextView cname=(TextView)view.findViewById(R.id.cname);

        //1.id,2.name,3.location
        try{
            infoData.put("技師名冊","");
            fetchdetail=fragdetail.getStringArray("datafrag");
            cname.setText(fetchdetail[1]);

            infoData.put("地址",fetchdetail[2]);
            infoData.put("電話","123-456-789");
            Log.d("zajaa",fetchdetail[0]+" "+fetchdetail[1]+" "+fetchdetail[2]);
        }
        catch(Exception e){

        }
        for (String key: infoData.keySet()) {
            System.out.println("key : " + key);
            System.out.println("value : " +infoData.get(key));
        }
        resultmAdapter=new BasicInfoAdapter(getActivity(),infoData);

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        dots = (LinearLayout) view.findViewById(R.id.DotsDetail);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerDetail);
        viewpager viewPagerAdapter = new viewpager(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.getLayoutParams().height = (int) (height / 3.3);
        dotscount = viewPagerAdapter.getCount();
        dotsview = new ImageView[dotscount];


        for (int i = 0; i < dotscount; i++) {
            dotsview[i] = new ImageView(getActivity());
            dotsview[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            dots.addView(dotsview[i], params);
        }
        dotsview[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dotsview[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dot));
                }
                dotsview[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        infolist=(RecyclerView)view.findViewById(R.id.multi_info_recycler);
        infolist.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//Layout manager for Recycler view
        infolist.setHasFixedSize(true);
        infolist.setLayoutManager(new LinearLayoutManager(getActivity()));
        infolist.setAdapter(resultmAdapter);
        infolist.setHasFixedSize(true);
        return view;
    }


}

