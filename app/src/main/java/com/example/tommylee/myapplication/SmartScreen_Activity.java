package com.example.tommylee.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;

import org.apache.http.client.utils.URIBuilder;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.StaticLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tommylee.myapplication.expandrecyclerview.MultiCheckGenreAdapter;
import com.thoughtbot.expandablecheckrecyclerview.listeners.OnCheckChildClickListener;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import info.hoang8f.android.segmented.SegmentedGroup;

import static com.example.tommylee.myapplication.expandrecyclerview.GenreDataFactory.makeMultiCheckGenres;


public class SmartScreen_Activity extends AppCompatActivity {
    private List<String> selectedArray=new ArrayList<String>();
    Uri.Builder builder = new Uri.Builder();
    private List<String> categoriesArray=new ArrayList<String>();
    private int lengthBox = 8;
    private Button[] btn = new Button[8];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.catbut0,R.id.catbut1,R.id.catbut2,R.id.catbut3,R.id.catbut4,R.id.catbut5,R.id.catbut6,R.id.catbut7};
    private MultiCheckGenreAdapter adapter;
    private smartsearchAdapter mAdapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
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
        builder.clearQuery();
        String keyfromresult;
        builder.scheme("http")
                .authority("172.26.5.155")
                .appendPath("autocomplete");
        final TextView keyword=(TextView)findViewById(R.id.searchretrieve);
        try {
             keyfromresult= getIntent().getExtras().getString("keyword");
            keyword.setText(keyfromresult);
        }
        catch(NullPointerException e)
        {

        }
        Button catereset=(Button)findViewById(R.id.catereset);
        //set something
        for(int i = 0; i < btn.length; i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            final int btnct=i;
            btn[i].setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(btn[btnct].isHovered())
                    {

                        btn[btnct].setHovered(true);
                    }
                    if(btn[btnct].isSelected()){
                        btn[btnct].setTextColor(getResources().getColor(R.color.dark_grey));
                        btn[btnct].setSelected(false);
                        categoriesArray.remove(btn[btnct].getText().toString());
                    }
                    if(btn[btnct].isPressed()){
                        btn[btnct].setPressed(true);
                        btn[btnct].setHovered(true);
                        btn[btnct].setSelected(true);
                        btn[btnct].setTextColor(getResources().getColor(R.color.colorPrimary));
                        categoriesArray.add(btn[btnct].getText().toString());
                    }


                }
            });
        }



        recyclerView= (RecyclerView) findViewById(R.id.locationrecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        adapter = new MultiCheckGenreAdapter(makeMultiCheckGenres());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(   new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);





        DisplayMetrics metrics = new DisplayMetrics();
        Display display= getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
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
                    categoriesArray.add(cb.getText().toString());

                    CharSequence myCheck = cb.getText();
                    Toast.makeText(getApplicationContext(),
                            myCheck + " checked!",
                            Toast.LENGTH_SHORT)
                            .show();
                } else if (!b) {
                    categoriesArray.remove(cb.getText().toString());
                    count[0]--;
                }
            }


        };

        final RatingBar rating=(RatingBar)findViewById(R.id.ratingbar);
        final TextView rate=(TextView)findViewById(R.id.categoriesrate);
                rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating,
                                                boolean fromUser) {
                        if (fromUser) {
                            int r = (int) (rating + 0.5f);
                            ratingBar.setRating(r);
                            if(r==0)
                            {
                                rate.setText("不限");
                            }
                            else
                            rate.setText(String.valueOf(r)+"星");
                        }
                }});
        adapter.setOnGroupExpandCollapseListener(new GroupExpandCollapseListener() {
            @Override
            public void onGroupExpanded(ExpandableGroup group) {
                Log.d("abc",String.valueOf(group.getItemCount()));
            }

            @Override
            public void onGroupCollapsed(ExpandableGroup group) {
                Log.d("abc","app");
            }
        });
        adapter.setChildClickListener(new OnCheckChildClickListener() {
            @Override
            public void onCheckChildCLick(View v, boolean checked, CheckedExpandableGroup group,
                                          int childIndex) {
                Log.d("abc",group.getTitle()+" "+String.valueOf(childIndex));
            }
        });
keyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
    public boolean onEditorAction(TextView v, int actionId,
                                  KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            keyword.clearFocus();
            View view = getCurrentFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return true;

        }
        return false;
    }
});

        Button submit=findViewById(R.id.smartsearchsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.clearQuery();
                if(!"".equals(keyword.getText().toString()))
               builder.appendQueryParameter("company",keyword.getText().toString());
                Iterator x=selectedArray.listIterator(0);
                while (x.hasNext()) {
                    builder.appendQueryParameter("location",x.next().toString());
                }
                Iterator y=categoriesArray.listIterator(0);
                while (y.hasNext()) {
                    builder.appendQueryParameter("categories",y.next().toString());
                }
                Intent intent = new Intent(getApplicationContext(), Result_Page_Activity.class);
                try {

                    intent.putExtra("url", "?" + builder.toString().split("\\?")[1]);

                }
                catch(ArrayIndexOutOfBoundsException e){
                    intent.putExtra("url", "");
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                Log.d("msgg",builder.toString());

            }

        });
        Button ratereset=(Button)findViewById(R.id.ratereset);
        ratereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset

                rating.setRating((float)0.0);
                rate.setText("不限");
            }
        });


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
    private URL uriBuilder(String buildpath,int querytype) {

       switch (querytype) {
           case 0:builder.appendQueryParameter("company", buildpath);
           break;
           case 1:builder.appendQueryParameter("location", buildpath);
           break;
       }
        String myUrl = builder.build().toString();
       URL url;

       try{
           url=new URL(myUrl);
           Log.d("msgg",url.toString());
           return url;
       }
       catch (MalformedURLException e){
            return null;
       }

    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
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





