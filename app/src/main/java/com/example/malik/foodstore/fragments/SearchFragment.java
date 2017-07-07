package com.example.malik.foodstore.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
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
import com.example.malik.foodstore.ApiService;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.RetrofitClient;
import com.example.malik.foodstore.adapters.FoodItemAdapter;
import com.example.malik.foodstore.model.FoodItem;
import com.example.malik.foodstore.model.FoodItemList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by malik on 7/4/2017.
 */

public class SearchFragment extends Fragment {
    /**
     * Views
     */

    private ListView listView;
    private View parentView;
    private ArrayList<FoodItem> foodItem;
    private FoodItemAdapter adapter;
    private Button button, bangaloreButton, delhiButton, vegetarianButton, nonVegetarianButton;
    private final String[] array = {"Search by Category"};
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.view_row, R.id.header_text, array);
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.view_row, R.id.header_text, array);
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) view.findViewById(R.id.listview);


        expandableLayoutListView.setAdapter(arrayAdapter);
        expandableLayoutListView.setAdapter(arrayAdapter2);

        bangaloreButton =(Button)view.findViewById(R.id.bangaloreButton);
        delhiButton =(Button)view.findViewById(R.id.delhiButton);
        vegetarianButton =(Button)view.findViewById(R.id.vegetarianButton);
        nonVegetarianButton =(Button)view.findViewById(R.id.nonVegetarianButton);

        bangaloreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"bangalore",Toast.LENGTH_LONG).show();
            }
        });
        delhiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"delhi",Toast.LENGTH_LONG).show();
            }
        });
        vegetarianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"veg items",Toast.LENGTH_LONG).show();
            }
        });
        nonVegetarianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"non veg items",Toast.LENGTH_LONG).show();
            }
        });
        foodItem = new ArrayList<>();

        parentView = view.findViewById(R.id.parentLayoutSearch);

       /* *
         * Getting List and Setting List Adapter
         */
        listView = (ListView) view.findViewById(R.id.listViewSearch);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, foodItem.get(position).getFood_name() + " => " , Snackbar.LENGTH_LONG).show();
            }
        });
        button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    final ProgressDialog dialog;
                    /**
                     * Progress Dialog for User Interaction
                     */
                    dialog = new ProgressDialog(getActivity());
                    dialog.setTitle("Progress Report");
                    dialog.setMessage("Loading");
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetrofitClient.getApiService();

                   /* *
                     * Calling JSON
                     */
                    Call<FoodItemList> call = api.getMyJSON();

                   /* *
                     * Enqueue Callback will be call when get response...
                     */
                    call.enqueue(new Callback<FoodItemList>() {
                       /* @Override
                        public void onResponse(Call<FoodItemList> call, Response<FoodItemList> response) {
                            FoodItemList productResponse = new FoodItemList();
                            //no need of parsing, directly passing response to model class
                            productResponse = response.body();
                            adapter = new FoodItemAdapter(foodItem, getActivity(), new FoodItemAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(FoodItem item) {

                                    }
                                });
                                //listView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<FoodItemList> call, Throwable t) {

                        }*/
                       @Override
                        public void onFailure(Call<FoodItemList> call, Throwable t) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onResponse(Call<FoodItemList> call, Response<FoodItemList> response) {
                            //Dismiss Dialog
                            dialog.dismiss();

                            if(response.isSuccessful()) {


                                foodItem = response.body().getFoodItems();

                               /* *
                                 * Binding that List to Adapter
                                 */
                                adapter = new FoodItemAdapter(foodItem, getActivity(), new FoodItemAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(FoodItem item) {

                                    }
                                });
                                //listView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView, "something", Snackbar.LENGTH_LONG).show();
                            }
                        }

                    });

                }


        });

        return view;
    }
}
