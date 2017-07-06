package com.example.malik.foodstore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.malik.foodstore.R;

/**
 * Created by malik on 7/4/2017.
 */

public class CartFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        return view;
    }
}
