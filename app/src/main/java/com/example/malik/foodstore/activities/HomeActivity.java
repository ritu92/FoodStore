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

/**
 * Home activity to display 4 basic fragments (Home, Search, Cart, Track Orders) in the gragment view that the activity holds
 * @see FragmentTabHost
 * @see HomeFragment
 * @see CartFragment
 * @see SearchFragment
 * @see OrdersFragment
 */

public class HomeActivity extends FragmentActivity implements FragmentChangeListener{
    //String TAG = getApplicationContext().toString();
    FragmentTabHost fragmentTabHost;
    /**
     *view that displays 4 tabs, home, search, orders and cart using fragment tab host
     *Called when the activity is first created.

     * @param savedInstanceState original instance to create initial view
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

    /**
     * Interface function overriden, to do fragment replacement throught various activities
     * @param fragment the input fragment we want to replace the current frame layout with
     * @see Fragment
     */
    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.realtabcontent,fragment,fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
