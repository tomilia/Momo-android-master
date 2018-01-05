package com.example.tommylee.myapplication.detail.tabbed;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;

import java.util.ArrayList;

/**
 * Created by tommylee on 17/12/2017.
 */

public class PriceSectionTab extends Fragment {
    private ViewPager viewPager;
    private RecyclerView pricelist;
    private RecyclerView.Adapter resultmAdapter;
    private ArrayList<DataFetch> result;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_price_section_tab,container,false);
        pricelist=(RecyclerView)view.findViewById(R.id.pricelist);
        pricelist.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //Layout manager for Recycler view
        result=new ArrayList<>();
        pricelist.setHasFixedSize(true);
        pricelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        DataFetch fetch = new DataFetch("name","url",234,"odos");
        DataFetch fetch2 = new DataFetch("name","url",234,"odos");
        result.add(fetch);
        result.add(fetch2);
        resultmAdapter=new PriceSectionAdapter(getActivity(),result);
        pricelist.setAdapter(resultmAdapter);
        pricelist.setHasFixedSize(true);

        return view;
    }
}