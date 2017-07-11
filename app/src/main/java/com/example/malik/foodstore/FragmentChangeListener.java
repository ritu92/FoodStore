package com.example.malik.foodstore;

import android.support.v4.app.Fragment;

/**
 * Created by malik on 7/7/2017.
 * interface which ay activity or fragment can implement to replace their fragment with another
 */

public interface FragmentChangeListener {
    /**
     * overridable function
     * @param fragment
     */
    public void replaceFragment(Fragment fragment);
}
