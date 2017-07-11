package com.example.malik.foodstore.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.adapters.FoodItemAdapter;
import com.example.malik.foodstore.adapters.SlidingImage_Adapter;
import com.example.malik.foodstore.model.FoodItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by malik on 7/4/2017.
 * Home default display fragment
 * Displays a rotating food picture layout with various food items on top
 * Displays food items to order from
 */

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<FoodItem> foodItems ;
    ProgressDialog progressDialog;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private String url = "http://rjtmobile.com/ansari/fos/fosapp/fos_food_loc.php?city=delhi";
    public static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    /**
     * Inflating the default home screen view
     * Displays the scrolling pictures on the top
     * Calls the recycler view to display some food items
     */
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewHome);
        recyclerView.setHasFixedSize(true);
        //custom layout for recycler view
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));

        foodItems= new ArrayList<>();
        myloadRecylerView();
        for (int i = 0; i < IMAGES.length; i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) view.findViewById(R.id.pagerHome);
        mPager.setAdapter(new SlidingImage_Adapter(getActivity(), ImagesArray));


        com.example.malik.foodstore.CirclePageIndicator indicator = (com.example.malik.foodstore.CirclePageIndicator) view.findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {

            /**
             * runs the next item image display
             */
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }

        };
        /**
         * how long one image will be displayed
         */
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run()
            {
                handler.post(Update);
            }

        }, 3000, 3000);
/**
 * to enable manual scrolling of pictures
 */
        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position)
            {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }

        });

        return view;
    }
    /**
     * loads the items from delhi URL, using volley, hard coded because only two areas available -
     * delhi, bangalore
     * displays progress dialogue till data is loaded
     */
    private void myloadRecylerView() {
        Log.i(TAG,"myloadRecyclerView");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("loading data");
        progressDialog.show();

        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                // no hardcoding of strings
                Log.i(TAG,response.toString());
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray("Food");
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject item = categories.getJSONObject(i);
                        FoodItem ls = new FoodItem(item.getString("FoodName"), item.getString("FoodPrice"),
                                item.getString("FoodCategory"),item.getString("FoodThumb") );
                        foodItems.add(ls);
                    }

                    adapter = new FoodItemAdapter(foodItems,getActivity().getApplicationContext(), new FoodItemAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(FoodItem item) {
                            Toast.makeText(getActivity(), "Go to search bar to order", Toast.LENGTH_LONG).show();
                        }
                    });

                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(sr);
    }

}
