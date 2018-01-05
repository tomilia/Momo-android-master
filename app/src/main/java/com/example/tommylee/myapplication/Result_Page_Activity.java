package com.example.tommylee.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class Result_Page_Activity extends AppCompatActivity {
    String s,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            url=getIntent().getStringExtra("url");
        }
        catch(Exception e){


        }
        Bundle bundle = new Bundle();
        bundle.putString("querys", url);
        ResultFragment fragobj = new ResultFragment();

        fragobj.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.result_frag,fragobj).commit();
        setContentView(R.layout.activity_result__page_);

// set Fragmentclass Arguments

        TextView marque=(TextView)findViewById(R.id.marquee);
        marque.setSelected(true);

        Spinner spinner = (Spinner) findViewById(R.id.resultfillspinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.popuarray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        }
    public void onSupportNavigateUp2(View view) {
        onBackPressed();
        getIntent().removeExtra("url");

    }
    public void FilterClick(View view){
        Intent intent = new Intent(getApplicationContext(), SmartScreen_Activity.class);
        intent.putExtra("keyword",url);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);
    }
    }



