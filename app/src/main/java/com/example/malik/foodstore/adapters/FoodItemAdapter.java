package com.example.malik.foodstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.malik.foodstore.R;
import com.example.malik.foodstore.model.FoodItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by malik on 7/4/2017.
 * adapter to populate home screen with food items
 */

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ViewHolder> {
    List<FoodItem> foodItems;
    private Context context;
    private final OnItemClickListener listener;
    /**
     * parameterized constructor
     */
    public FoodItemAdapter(List<FoodItem> foodItems, Context context, OnItemClickListener listener) {
    this.foodItems = foodItems;
        this.context = context;
        this.listener = listener;
    }
    /**
     * enables click in an item
     */
    public interface OnItemClickListener {
        void onItemClick(FoodItem item);
    }
    /**
     * displays items view for a singular card view attached to adapter
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.food_details_fragment,parent,false);

        return new ViewHolder(v);

    }
    /**
     * binds and populates the views inside the card view using holder
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FoodItem foodItemList = foodItems.get(position);
        holder.tv_foodName.setText(foodItemList.getFood_name());
        holder.tv_foodCategory.setText("Category: "+ foodItemList.getFood_category());
        holder.tv_foodPrice.setText("Price: "+ foodItemList.getFood_price());
        Picasso.with(context)
                .load(foodItemList.getFood_img())
                .resize(500,400)
                .into(holder.iv_foodImg);
        holder.bind(foodItems.get(position), listener);
    }
    /**
     * gets the size of array to define how many cards will be displayed
     */
    @Override
    public int getItemCount() {
        return foodItems.size();
    }
    /**
     * class that assigns values to views by finding them in the layout
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_foodName, tv_foodCategory, tv_foodPrice;
        ImageView iv_foodImg;
        /**
         * method that assigns values to views by finding them in the layout
         */
        public ViewHolder(View itemView) {
            super(itemView);
            tv_foodName = (TextView) itemView.findViewById(R.id.tv_foodName);
            tv_foodCategory = (TextView) itemView.findViewById(R.id.tv_foodCategory);
            tv_foodPrice = (TextView) itemView.findViewById(R.id.tv_foodPrice);
            iv_foodImg = (ImageView) itemView.findViewById(R.id.iv_foodImg);
        }
        /**
         * on click listener for a single card
         */
        public void bind(final FoodItem foodItem, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(foodItem);
                }
            });
        }
    }
}
