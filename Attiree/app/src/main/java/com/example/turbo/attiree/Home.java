package com.example.turbo.attiree;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment{
    List<ItemListPojo> itemList;
    ItemListPojo itemListPojo;
    RecyclerView myRecyclerView;
    RecyclerView.Adapter adapter;

    String DATA_URL;
    ProgressDialog progressDialog;


    public Home() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        myRecyclerView = view.findViewById(R.id.rcview);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        itemList = new ArrayList<>();
        loadData();




        return view;


    }




    private void loadData() {
        DATA_URL ="http://192.168.10.6:8080/adminpanel/getimages.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(CircularParent.this, response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject reader = new JSONObject(response);
                    JSONObject responseJSON = reader.getJSONObject("response");
                    String resultSuccess = responseJSON.getString("result");
                    if (resultSuccess.equals("success")) {
                        JSONArray jsonarray = responseJSON.getJSONArray("data");
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject c = jsonarray.getJSONObject(i);

                            itemListPojo = new ItemListPojo(
                                    c.getString("title"),
                                    c.getString("description"),
                                    c.getString("image")
                            );
                            itemList.add(itemListPojo);
                        }
                        adapter = new MyAdapter(itemList,getActivity());
                        myRecyclerView.setAdapter(adapter);

                        progressDialog.dismiss();
                    }
                } catch (final JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        progressDialog = ProgressDialog.show(getActivity(), "Please Wait...", null, true, true);
        progressDialog.setMessage("Fetching Your Data ! Please wait...!");
        progressDialog.setCancelable(false);
    }
}



