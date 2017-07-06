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

    public SlidingImage_Adapter(Context context,ArrayList<Integer> IMAGES)
    {
        this.context = context;
        this.IMAGES=IMAGES;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position,Object object)
    {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return IMAGES.size();
    }

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

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader)
    {

    }

    @Override
    public Parcelable saveState()
    {
        return null;
    }
}
