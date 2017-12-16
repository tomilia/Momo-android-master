package com.example.tommylee.myapplication;

import android.app.Activity;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;


public class SmartScreen_Activity extends AppCompatActivity {
    ListView Location;
    ArrayList<String> productResults = new ArrayList<String>();
    private RecyclerView mRecyclerView;
    private int lengthBox = 8;
    private smartsearchAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
private String[] myDataset={"$100或以下","地區"};
    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> distirctList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_screen_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.smart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> cityList = ArrayAdapter.createFromResource(SmartScreen_Activity.this,
                R.array.lunch,
                R.layout.selection_box_child);

        spinner.setAdapter(cityList);

        spinner.setOnItemSelectedListener(new SpinnerActivity());
        spinner2 = (Spinner)findViewById(R.id.spinner2);

        distirctList = ArrayAdapter.createFromResource(SmartScreen_Activity.this,
                R.array.az,
                R.layout.selection_box_child);
        spinner2.setAdapter(distirctList);
        SegmentedGroup segmented4 = (SegmentedGroup)findViewById(R.id.segmented2);
        segmented4.setTintColor(0xFF229922);
        segmented4.cancelLongPress();

        segmented4.check(R.id.button21);

        Spinner mySpinner = (Spinner)findViewById(R.id.spinnerprice);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.selection_box, R.id.weekofday, myDataset);
        mySpinner.setAdapter(adapter);
        DisplayMetrics metrics = new DisplayMetrics();
        Display display= getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Log.d("width",String.valueOf(size.y));
        final CheckBox[] checkbox = new CheckBox[lengthBox];
        final int[] count = {0};
        final int maxLimit=3;
        CompoundButton.OnCheckedChangeListener checker = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean b) {
                if (count[0] == maxLimit && b) {
                    cb.setChecked(false);
                    Toast.makeText(getApplicationContext(),
                            "Limit reached!!!", Toast.LENGTH_SHORT).show();
                } else if (b) {

                    count[0]++;
                    CharSequence myCheck = checkbox[count[0]].getText();
                    Toast.makeText(getApplicationContext(),
                            myCheck + " checked!",
                            Toast.LENGTH_SHORT)
                            .show();
                } else if (!b) {
                    count[0]--;
                }
            }


        };
        for(int i = 0; i < lengthBox; i++) {

            int id = getResources().getIdentifier("cb"+i, "id", getPackageName());
            Log.d("truee",String.valueOf(id)+" "+String.valueOf(R.id.cb1));
            checkbox[i] = (CheckBox) findViewById(id);
            float textwidth;
            if(width<=576)textwidth=11.5f;
            else if(width>576&&width<=1280)textwidth=13.5f;
            else textwidth=16;

            checkbox[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,textwidth);
            checkbox[i].setOnCheckedChangeListener(checker);
        }


        Log.d("avc",String.valueOf(width));

        ImageButton back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }

        });


    }


        @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
             Log.d("vbx",parent.getItemAtPosition(pos).toString());
            switch(pos) {
                case 0:distirctList = ArrayAdapter.createFromResource(SmartScreen_Activity.this,
                        R.array.az,
                        R.layout.selection_box_child);
                    spinner2.setAdapter(distirctList);
                    break;
                case 2:
                   distirctList = ArrayAdapter.createFromResource(SmartScreen_Activity.this,
                            R.array.sz,
                            R.layout.selection_box_child);
                    spinner2.setAdapter(distirctList);
                    break;
            }

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
}





