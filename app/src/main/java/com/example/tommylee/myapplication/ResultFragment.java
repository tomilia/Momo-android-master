package com.example.tommylee.myapplication;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tommylee on 17/12/2017.
 */

public class ResultFragment extends Fragment {
    RecyclerView resultlist;
    private RecyclerView.Adapter resultmAdapter;
    ArrayList<DataFetch> result;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.result_fragment,container,false);
        Bundle strtext = getArguments();
        try{
            result=new ArrayList<>();
            String key=strtext.getString("querys");

        Fetching fetch=new Fetching();
        fetch.execute(key);
            Log.d("TORRNE",key);
        }
        catch(Exception e){

        }
        resultlist=(RecyclerView)view.findViewById(R.id.result_list);
        resultlist.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //Layout manager for Recycler view
        resultlist.setHasFixedSize(true);
        resultlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
    private class Fetching extends AsyncTask<String, Void, String> {
        JSONObject jObject;
        JSONArray searchList;





        @Override
        protected String doInBackground(String... sText) {
            searchList=new JSONArray();

            jObject=new JSONObject();
            OkHttpClient client = new OkHttpClient();
            Request request;
            OkHttpClient client1=client.newBuilder().build();
            Log.d("buildurl",sText[0]);
            request = new Request.Builder().url("http://123.203.71.211:5555/autocomplete"+sText[0]).build();
            try {

                Response response = client1.newCall(request).execute();

                JSONArray jsonarray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String name = jsonobject.getString("company");
                    String url = jsonobject.getString("address");
                    int averageprice= jsonobject.getInt("price");
                    String id=jsonobject.getString("_id");
                    Log.d("jjxx",sText[0]+" "+name+" "+url);
                    DataFetch fetch = new DataFetch(name,url,averageprice,id);
                    result.add(fetch);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return sText[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resultmAdapter=new Result_Page_Adapter(getActivity(),result);
            resultlist.setAdapter(resultmAdapter);
            resultlist.setHasFixedSize(true);
            TextView txt4=getActivity().findViewById(R.id.textView4);
            txt4.setText("返回共"+result.size()+"個結果");

            }

        };


    }

