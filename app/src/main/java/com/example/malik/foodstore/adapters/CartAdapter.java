package com.example.malik.foodstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.malik.foodstore.R;
import com.example.malik.foodstore.model.CartList;

import java.util.ArrayList;

/**
 * Created by malik on 7/6/2017.
 * Adapter to display items in the cart
 * Lets you  delete existing items
 * Can increase or decrease quantity ot items
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<CartList> cartListItems;
    private Context context;

    /**
     * Constructor to access Cart adapter in other places
     * @param cartListItems items you want to add
     * @param context returns current context to apply
     */
    public CartAdapter(ArrayList<CartList> cartListItems, Context context) {
        this.cartListItems = cartListItems;
        this.context = context;

    }

    /**
     * inflates selected layout from fragment for the cart
     * @param parent parent view
     * @param viewType
     * @return inflates the view
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.cart_detail_fragment,parent,false);

        return new CartAdapter.ViewHolder(v);
    }

    /**
     * binds the view holder to the adapter view
     * @param holder We need cart adapter's view holder
     * @param position sets separate values to each position in the cart
     */
    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, int position) {
        CartList cartList = cartListItems.get(position);
        holder.cart_price.setText(cartList.getCart_price());
        holder.cart_name.setText(cartList.getCart_name());
    }

    /**
     * gets count of the array list we are populating in the view
     * @return returns size of array
     */
    @Override
    public int getItemCount() {
        //return 0;
        return cartListItems.size();
    }

    /**
     * assigns view by id to each view in the cart fragment item
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cart_price, cart_name;
        public ViewHolder(View itemView) {
            super(itemView);
            cart_price = (TextView)itemView.findViewById(R.id.cart_price);
            cart_name = (TextView)itemView.findViewById(R.id.cart_name);
        }
    }
}
