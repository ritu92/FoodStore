package com.example.malik.foodstore.fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.malik.foodstore.ApiService;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.RetrofitClient;
import com.example.malik.foodstore.adapters.FoodItemAdapter;
import com.example.malik.foodstore.model.FoodItem;
import com.example.malik.foodstore.model.FoodItemList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by malik on 7/4/2017.
 */

public class SearchFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private String url = "http://rjtmobile.com/ansari/fos/fosapp/fos_food_loc.php?";
    String url_end, url_final;
    /**
     * Views
     */

    private RecyclerView recyclerViewSearch;
    private RecyclerView.Adapter rv_adapter;
    private View parentView;
    private ArrayList<FoodItem> foodItem;
    //private FoodItemAdapter adapter;
    private Button button, bangaloreButton, delhiButton, vegetarianButton, nonVegetarianButton;
    private final String[] array = {"Search by Category"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        bangaloreButton = (Button) view.findViewById(R.id.bangaloreButton);
        delhiButton = (Button) view.findViewById(R.id.delhiButton);
        vegetarianButton = (Button) view.findViewById(R.id.vegetarianButton);
        nonVegetarianButton = (Button) view.findViewById(R.id.nonVegetarianButton);

        bangaloreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "bangalore", Toast.LENGTH_LONG).show();
                url_end = "city=banglore";

                myloadRecylerView();
            }
        });
        delhiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "delhi", Toast.LENGTH_LONG).show();
                url_end = "city=delhi";
                myloadRecylerView();
            }
        });
        vegetarianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "veg items", Toast.LENGTH_LONG).show();
                url_end = "food_category=veg&city=delhi";
                myloadRecylerView();

            }
        });
        nonVegetarianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "non veg items", Toast.LENGTH_LONG).show();
                url_end = "food_category=non-veg&city=banglore";
                myloadRecylerView();
            }
        });
        foodItem = new ArrayList<>();

        parentView = view.findViewById(R.id.parentLayoutSearch);
         /* *
         * Getting List and Setting List Adapter
         */
        recyclerViewSearch = (RecyclerView) view.findViewById(R.id.recyclerViewSearch);
        recyclerViewSearch.setHasFixedSize(true);
        //recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));

      /*
        button = (Button) view.findViewById(R.id.button);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog;
                *//**
                 * Progress Dialog for User Interaction
                 *//*
                dialog = new ProgressDialog(getActivity());
                dialog.setTitle("Progress Report");
                dialog.setMessage("Loading");
                dialog.show();

                //Creating an object of our api interface
                ApiService api = RetrofitClient.getApiService();

                   *//* *
                     * Calling JSON
                     *//*
                Call<FoodItemList> call = api.getMyJSON("delhi");

                   *//* *
                     * Enqueue Callback will be call when get response...
                     *//*
                call.enqueue(new Callback<FoodItemList>() {

                    @Override
                    public void onFailure(Call<FoodItemList> call, Throwable t) {
                        Log.i("Ritu", "failed");
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(Call<FoodItemList> call, Response<FoodItemList> response) {
                        //Dismiss Dialog
                        Log.i("Ritu", "in onResponse"+response.raw());
                        dialog.dismiss();

                        if (response.body() != null) {
                            *//*FoodItemList foodItemList = new FoodItemList();
                            foodItemList = response.body();*//*

                            foodItem = response.body().getFoodItems();
                            Log.i("Ritu", foodItem + "body");

                               *//* *
                                 * Binding that List to Adapter
                                 *//*
                            rv_adapter = new FoodItemAdapter(foodItem, getActivity(), new FoodItemAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(FoodItem item) {
                                    Toast.makeText(getActivity(), "add to cart", Toast.LENGTH_LONG).show();
                                }
                            });
                            recyclerViewSearch.setAdapter(rv_adapter);
                            //recyclerViewSearch.notifyAll();
                        }
                        else {
                            Log.i("Ritu","null body");
                        }

                    }

                });

            }


        }); */

        return view;
    }

    private void myloadRecylerView() {

        recyclerViewSearch.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));

        url_final = url+url_end;

        StringRequest sr = new StringRequest(Request.Method.GET, url_final, new com.android.volley.Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                // no hardcoding of strings
                Log.i(TAG,response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray("Food");
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject item = categories.getJSONObject(i);
                        FoodItem ls = new FoodItem(item.getString("FoodName"), item.getString("FoodPrice"),
                                item.getString("FoodCategory"),item.getString("FoodThumb") );
                        foodItem.add(ls);
                    }

                    rv_adapter = new FoodItemAdapter(foodItem,getActivity().getApplicationContext(), new FoodItemAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(FoodItem item) {
                            SharedPreferences pref2 = getActivity().getApplicationContext().getSharedPreferences("MyPrefCart",0);
                            SharedPreferences.Editor editor = pref2.edit();
                            editor.putString("name", item.getFood_name());
                            editor.putString("price", item.getFood_price());
                            editor.commit();
                            Snackbar.make(parentView, "Item added to cart", Snackbar.LENGTH_LONG).show();
                            //Toast.makeText(getActivity(), "Item added to cart", Toast.LENGTH_LONG).show();
                            Log.i("Ritu", item.getFood_name()+item.getFood_price());
                        }
                    });

                    recyclerViewSearch.setAdapter(rv_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(sr);
    }
}
