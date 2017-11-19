package com.example.tommylee.myapplication;

import android.app.Application;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.MenuItem;
import android.support.v4.view.ViewPager;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;
    private RecyclerView mRecyclerView2;
    private ImageButton mapcaller;
    private ImageButton smartchoice;
    private ImageButton searchbarbutton;
    ViewPager viewPager;
    private ArrayList<DataFetch> mDataset;
    private ArrayList<DataFetch> mDataset2;
    LinearLayout dots;
    private int dotscount;
    private ImageView[] dotsview;
    public static int CHECK_SUM=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {
            setContentView(R.layout.activity_main);


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            dots = (LinearLayout) findViewById(R.id.Dots);
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            viewpager viewPagerAdapter = new viewpager(this);
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.getLayoutParams().height = (int) (height / 3.3);
            dotscount = viewPagerAdapter.getCount();
            dotsview = new ImageView[dotscount];


            for (int i = 0; i < dotscount; i++) {
                dotsview[i] = new ImageView(this);
                dotsview[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                dots.addView(dotsview[i], params);
            }
            dotsview[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < dotscount; i++) {
                        dotsview[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                    }
                    dotsview[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            mDataset = new ArrayList<>();
            mDataset2 = new ArrayList<>();


            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            TestTask(0);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MainAdapter(this, mDataset, 0);
            mRecyclerView.setAdapter(mAdapter);
            Log.d("checkyo", "checked");
            mRecyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
            mRecyclerView2.setHasFixedSize(true);
            mLayoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

            mRecyclerView2.setLayoutManager(mLayoutManager2);
            TestTask(1);
            mAdapter2 = new MainAdapter(this, mDataset2, 1);
            mRecyclerView2.setAdapter(mAdapter2);

            mapcaller = (ImageButton) findViewById(R.id.mapcaller);
            mapcaller.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage("基本訊息對話功能介紹");
                    dialog.show();
                }
            });

            smartchoice = (ImageButton) findViewById(R.id.smartchoice);
            smartchoice.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage("基本功能");
                    dialog.show();
                }
            });
            searchbarbutton = findViewById(R.id.search_bar);
            searchbarbutton.setOnClickListener(new ImageButton.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Search_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(intent);
                }
            });
        }
        }
    @Override

    public void onStop(){
        Log.d("tryyy","check");
        super.onStop();
    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void TestTask(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading..");
                progressDialog.show();

            }


            @Override
            protected Void doInBackground(Integer... integer) {
                OkHttpClient client = new OkHttpClient();
                Request request;
                if(id==0) {
                   request = new Request.Builder().url("http://192.168.1.241:5555/autocomplete").build();
                    Log.d("checksum1",String.valueOf(id));
                }
                else {
                     request = new Request.Builder().url("http://192.168.1.241:5555/autocomplete?company=z").build();
                    Log.d("checksum2",String.valueOf(id));
                }

                try {

                    Response response = client.newCall(request).execute();

                    JSONArray jsonarray = new JSONArray(response.body().string());
                    for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name = jsonobject.getString("company");
                        String url = jsonobject.getString("address");
                        DataFetch fetch = new DataFetch(jsonobject.getString("company"), jsonobject.getString("address"));
                        if(id==0)
                        mDataset.add(fetch);
                        else
                        mDataset2.add(fetch);
                    }
                    return null;
                } catch (Exception e) {
                    Log.d("showa","FUCK");
                    e.printStackTrace();
                }
            return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            private String RetrieveData(String UrlPath) throws IOException {
                StringBuilder result = new StringBuilder();
                BufferedReader buffer = null;
                try {
                    URL url = new URL(UrlPath);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    Log.wtf("connect123", urlConnection.toString());
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setConnectTimeout(10000);

                    urlConnection.setRequestMethod("GET");

                    urlConnection.setRequestProperty("Content-Type", "application/JSON");

                    urlConnection.connect();

                    InputStream input = urlConnection.getInputStream();
                    buffer = new BufferedReader(new InputStreamReader(input));

                    String line;
                    while ((line = buffer.readLine()) != null) {
                        result.append(line).append("\n");

                    }

                } finally {
                    if (buffer != null)
                        buffer.close();
                }
                return result.toString();
            }
        };
        task.execute(id);
    }
}
