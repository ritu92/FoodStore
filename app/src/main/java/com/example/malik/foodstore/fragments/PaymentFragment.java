package com.example.malik.foodstore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.malik.foodstore.R;

/**
 * Created by malik on 7/7/2017.
 */

public class PaymentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.payment_fragment, container, false);
        return view;
    }
}
