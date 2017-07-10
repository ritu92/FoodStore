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
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<CartList> cartListItems;
    private Context context;

    public CartAdapter(ArrayList<CartList> cartListItems, Context context) {
        this.cartListItems = cartListItems;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.cart_detail_fragment,parent,false);

        return new CartAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        CartList cartList = cartListItems.get(position);
        holder.cart_price.setText(cartList.getCart_price());
        holder.cart_name.setText(cartList.getCart_name());
    }

    @Override
    public int getItemCount() {
        return cartListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView cart_price, cart_name;
        public ViewHolder(View itemView) {
            super(itemView);
            cart_price = (TextView)itemView.findViewById(R.id.cart_price);
            cart_name = (TextView)itemView.findViewById(R.id.cart_name);
        }
    }
}
