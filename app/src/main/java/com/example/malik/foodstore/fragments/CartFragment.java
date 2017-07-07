package com.example.malik.foodstore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.malik.foodstore.FragmentChangeListener;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.activities.MapsActivity;

/**
 * Created by malik on 7/4/2017.
 * by default view for cart fragment
 */

public class CartFragment extends Fragment {
    Button bt_location;
    EditText et_Address;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
       bt_location = (Button)view.findViewById(R.id.bt_location);
        et_Address =(EditText) view.findViewById(R.id.et_Address);
        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
