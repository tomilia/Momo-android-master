package com.example.tommylee.myapplication.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import android.support.design.widget.TabLayout;

import com.example.tommylee.myapplication.R;


public class Detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] intentfrag=new String[3];
        try {
            intentfrag[0]= getIntent().getExtras().getString("id");
            intentfrag[1]=getIntent().getExtras().getString("name");
            intentfrag[2]=getIntent().getExtras().getString("district");
            Log.d("hahaha",intentfrag[0]+" "+intentfrag[1]+" "+intentfrag[2]);
        }
        catch(NullPointerException e){

        }if(savedInstanceState==null) {
            Bundle bundle = new Bundle();
            bundle.putStringArray("datafrag", intentfrag);

            BasicInfoFrag fragobj = new BasicInfoFrag();
            fragobj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.detail_frag, fragobj).commit();

            TabbedBottomFrag tabobj = new TabbedBottomFrag();
            getSupportFragmentManager().beginTransaction().add(R.id.tabbed_frag, tabobj).commit();

        }
        setContentView(R.layout.activity_detail_);
    }

    public void onSupportNavigateUp2(View view) {
        onBackPressed();
        getIntent().removeExtra("url");

    }

}
