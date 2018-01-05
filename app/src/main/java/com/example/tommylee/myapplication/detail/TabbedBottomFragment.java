package com.example.tommylee.myapplication.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by tommylee on 17/12/2017.
 */

public class TabbedBottomFragment extends FragmentPagerAdapter {
    private final List<Fragment> FragmentList=new ArrayList<>();
    private final List<String> FragmentTitleList=new ArrayList<>();
    public TabbedBottomFragment(FragmentManager fm)
    {
        super(fm);
    }
    public void addFragment(Fragment frag,String title){
        FragmentList.add(frag);
        FragmentTitleList.add(title);
    }

@Override
    public CharSequence getPageTitle(int position){
 return FragmentTitleList.get(position);
}

    @Override
    public Fragment getItem(int position) {
        return FragmentList.get(position);
    }

    @Override
    public int getCount() {
        return FragmentList.size()  ;
    }
}

