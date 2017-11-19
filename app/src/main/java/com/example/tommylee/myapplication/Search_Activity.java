package com.example.tommylee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Search_Activity extends AppCompatActivity {
    // List view
    private SharedPreferences savedstring;
    private static final String inputstring="KEYWORD";
    private ListView lv;
    private ArrayList<String> recent=new ArrayList<String>();
    private LinearLayout layout;
    private LinearLayout layout2;
    private static int counter;
    private boolean checknull=true;
    private boolean hotsearch=true;
    private boolean waitAsyc=true;
    ArrayList<DataFetch> productResults = new ArrayList<DataFetch>();
    // Listview Adapter
    AutoCompleteTextView edit;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        lv = (ListView) findViewById(R.id.list_view);
        Hawk.init(this).build();
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        edit=(AutoCompleteTextView) findViewById(R.id.searchmain);
        layout= (LinearLayout) findViewById(R.id.table);//Can also be done in xml by android:orientation="vertical"
        myTask hotsearchregex=new myTask();
        hotsearchregex.execute("p");

        while (waitAsyc){Log.d("","");}
        String[] temp=new String[productResults.size()];
        for(int i=0;i<productResults.size();i++)
            temp[i]=productResults.get(i).getDescription();
        ChipCloud chipCloud = (ChipCloud) findViewById(R.id.chip_cloud);

        new ChipCloud.Configure()
                .chipCloud(chipCloud)
                .selectedColor(Color.parseColor("#18862d"))
                .selectedFontColor(Color.parseColor("#ffffff"))
                .deselectedColor(Color.parseColor("#3cb371"))
                .deselectedFontColor(Color.parseColor("#fffffe"))
                .selectTransitionMS(0)
                .deselectTransitionMS(0)
                .labels(temp)
                .mode(ChipCloud.Mode.SINGLE)
                .allCaps(false)
                .gravity(ChipCloud.Gravity.LEFT)
                .textSize(((int)(getResources().getDimensionPixelSize(R.dimen.default_textsize)*1.2)))
                .verticalSpacing(getResources().getDimensionPixelSize(R.dimen.vertical_spacing))
                .minHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.min_horizontal_spacing))
                .chipListener(new ChipListener() {
                    @Override
                    public void chipSelected(int index) {
                        //...
                        Log.d("chip",String.valueOf(index));
                    }
                    @Override
                    public void chipDeselected(int index) {
                        //...
                    }
                })
                .build();

        layout2= (LinearLayout) findViewById(R.id.table2);//Can also be done in xml by android:orientation="vertical"

        final ListView recentr=(ListView) findViewById(R.id.resultrecent);
for(int a=0;a<Hawk.count();a++)
    recent.add(a,Hawk.get(String.valueOf(a)).toString());
for(int a=0;a<Hawk.count();a++)
Log.d("mardd",a+" "+ recent.get(a));
        final SavedResultAdapter adapter = new SavedResultAdapter(this,recent);

       recentr.setAdapter(adapter);
       recentr.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasfocus) {
                if (hasfocus) {
                    Log.e("TAG", "e1 focused");
                } else {
                    Log.e("TAG", "e1 not focused");
                }
            }
        });

        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO&&!edit.getText().toString().equals("")) {
                    Log.d("spending",edit.getText().toString());
                    recent.add((int)(Hawk.count()),edit.getText().toString());
                    Hawk.put(String.valueOf((int)Hawk.count()),edit.getText().toString());
                    for(int x=0;x<Hawk.count();x++)
                        Log.d("xmen",x+" "+ Hawk.get(String.valueOf(x)).toString());
                    Log.d("spalachi",String.valueOf(Hawk.count())+" "+String.valueOf(recent.size()));
              return true;
                }
                return false;
            }
        });
        edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub



            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

                if(arg0.length()>0) {

                    myTask task = new myTask();
                    spinner.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);
                    checknull=false;
                    task.execute(arg0.toString());
                    layout.setVisibility(View.GONE);
                    hotsearch=false;
                    layout2.setVisibility(View.GONE);
                }
                else {
                    checknull=true;
                    lv.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    hotsearch=true;
                    spinner.setVisibility(View.GONE);
                    Log.d("monty",String.valueOf(hotsearch));
                }

            }
        });

        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("scaoc",String.valueOf(productResults.get(position).getDescription()));

            }
        });
        recentr.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("scaoc",recent.get(position));
            }
        });
    }

@Override
public void onBackPressed(){
    edit.clearFocus();
    super.onBackPressed();

}
    public void onChangeToSmart(View view) {
        Intent intent = new Intent(view.getContext(), SmartScreen_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);

    }
        @Override
    public boolean onSupportNavigateUp() {
            edit.clearFocus();
        onBackPressed();
        return true;
    }

    public void onSupportNavigateUp2(View view) {
        edit.clearFocus();
        onBackPressed();

    }
    class myTask extends AsyncTask<String, Void, String>{
            JSONObject jObject;
            JSONArray searchList;
            String URL;
            String textSearch;




            @Override
            protected String doInBackground(String... sText) {
                searchList=new JSONArray();
                jObject=new JSONObject();
                OkHttpClient client = new OkHttpClient();
                Request request;
                OkHttpClient client1=client.newBuilder().build();
                    request = new Request.Builder().url("http://192.168.1.241:5555/autocomplete?company="+sText[0]).build();

                try {

                    Response response = client1.newCall(request).execute();
                    if(lv.getCount()!=0&&!hotsearch)
                    productResults.clear();
                    JSONArray jsonarray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name = jsonobject.getString("company");
                        String url = jsonobject.getString("address");
                        Log.d("jsonn",sText[0]+" "+name+" "+url);
                        DataFetch fetch = new DataFetch(name,url);


                        productResults.add(fetch);
                    }

                } catch (Exception e) {
                    Log.d("showa","FUCK");
                    e.printStackTrace();
                }
                waitAsyc=false;
                return sText[0];
            }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("montyx",String.valueOf(hotsearch));

            if(!hotsearch) {
                lv.setAdapter(new SearchResultAdapter(Search_Activity.this, productResults));

                spinner.setVisibility(View.GONE);
                if (lv.getCount() != 0 && !checknull)
                    lv.setVisibility(View.VISIBLE);
                else if (lv.getCount() == 0 || checknull)
                    lv.setVisibility(View.GONE);

            }

        }


        };

    }


