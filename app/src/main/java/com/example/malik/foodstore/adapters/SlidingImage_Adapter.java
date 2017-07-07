package com.example.malik.foodstore.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.malik.foodstore.R;

import java.util.ArrayList;

/**
 * Created by Raul on 7/4/2017.
 * adapter to display images in sliding view on home screen
 */

public class SlidingImage_Adapter extends PagerAdapter {

    private ArrayList<Integer> IMAGES;
    private LayoutInflater layoutInflater;
    private Context context;
    /**
     * parameterized constructor
     */
    public SlidingImage_Adapter(Context context,ArrayList<Integer> IMAGES)
    {
        this.context = context;
        this.IMAGES=IMAGES;
        layoutInflater = LayoutInflater.from(context);
    }
    /**
     * removes the current picture in order to create space for a new one
     */
    @Override
    public void destroyItem(ViewGroup container, int position,Object object)
    {
        container.removeView((View) object);
    }
    /**
     * gets the size of array to define how many images will be displayed
     */
    @Override
    public int getCount() {
        return IMAGES.size();
    }
    /**
     * inflates the image in the view
     */
    @Override
    public Object instantiateItem(ViewGroup view, int position)
    {
      View imageLayout = layoutInflater.inflate(R.layout.slidingimages_layout, view, false);

      assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);

        imageView.setImageResource(IMAGES.get(position));

            view.addView(imageLayout, 0);

            return imageLayout;
    }
    /**
     * checks if picture is populating the view with correct object
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    /**
     * could restore state if we had a saved state, does nothing here
     */
    @Override
    public void restoreState(Parcelable state, ClassLoader loader)
    {

    }
    /**
     * could save state if we needed it later,  no requirement here so returns null
     */
    @Override
    public Parcelable saveState()
    {
        return null;
    }
}
