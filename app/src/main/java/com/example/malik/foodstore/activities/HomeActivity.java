package com.example.malik.foodstore.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.example.malik.foodstore.FragmentChangeListener;
import com.example.malik.foodstore.fragments.HomeFragment;
import com.example.malik.foodstore.fragments.OrdersFragment;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.fragments.SearchFragment;
import com.example.malik.foodstore.fragments.CartFragment;

public class HomeActivity extends FragmentActivity implements FragmentChangeListener{
    //String TAG = getApplicationContext().toString();
    FragmentTabHost fragmentTabHost;
    /**
     * Home activity that displays 4 tabs, home, search, orders and cart using fragment tab host
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Log.i("","onCreate");
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //home tab
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("home").setIndicator(""),
                HomeFragment.class, null);
        fragmentTabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.home);
        // search tab
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("search").setIndicator(""),
                SearchFragment.class, null);
        fragmentTabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.search);
        //orders tab
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("orders").setIndicator(""),
                OrdersFragment.class, null);
        fragmentTabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.orders);
        //profile tab
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("cart").setIndicator(""),
                CartFragment.class, null);
        fragmentTabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.cart);



    }
    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.realtabcontent,fragment,fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
