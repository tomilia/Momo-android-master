package com.example.tommylee.myapplication.detail;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.detail.tabbed.CommentSectionTab;
import com.example.tommylee.myapplication.detail.tabbed.PriceSectionTab;
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

public class TabbedBottomFrag extends Fragment {
    private ViewPager viewPager;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view=inflater.inflate(R.layout.fragment_tabbed_bottom,container,false);
        TabbedBottomFragment tabbedBottomFragment=new TabbedBottomFragment(getFragmentManager());

        viewPager=(ViewPager)view.findViewById(R.id.tabbedvp);
        setupViewPager(viewPager);

        if(savedInstanceState==null) {
            TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabbedbutton);
            tabLayout.setupWithViewPager(viewPager);
            for (int i = tabLayout.getTabCount()-1; i >=0 ; i--) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                RelativeLayout relativeLayout = (RelativeLayout)
                        LayoutInflater.from(getContext()).inflate(R.layout.tablayout_divider, tabLayout, false);

                TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
                tabTextView.setText(tab.getText());
                tab.setCustomView(relativeLayout);
                tab.select();
            }
        }
        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        TabbedBottomFragment tabbedfrag=new TabbedBottomFragment(getFragmentManager());
        tabbedfrag.addFragment(new PriceSectionTab(),"項目列表");
        tabbedfrag.addFragment(new CommentSectionTab(),"評論");


        viewPager.setAdapter(tabbedfrag);
    }

}

