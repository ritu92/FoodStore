package com.example.malik.foodstore.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.model.Order;

import java.util.List;

/**
 * Created by malik on 7/5/2017.
 * adapter to populate order tracking  screen with past orders given the user enters a mobile number
 */

public class TrackOrderAdapter extends RecyclerView.Adapter<TrackOrderAdapter.ViewHolder> {
    List<Order> order;
    private Context context;
    private final OnItemClickListener listener;
    /**
     * displays items in a single card view, attaches them to the layout
      */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.order_detail_fragment,parent,false);
        return new TrackOrderAdapter.ViewHolder(v);
    }
    /**
     * parameterized contructor for the adapter
     */
    public TrackOrderAdapter(List<Order> order, Context context, OnItemClickListener listener){
        this.order = order;
        this.context = context;
        this.listener = listener;
    }
    /**
     * enables click in an item
     */
    public interface OnItemClickListener {
        void onItemClick(Order item);
    }
    /**
     * binds and populates the views inside the card view using holder
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Order list = order.get(position);
        holder.tv_orderID.setText("ID:"+list.getOrderId());
        holder.tv_orderName.setText("Item:"+list.getOrderName());
        holder.tv_orderQuantity.setText("Quantity:"+list.getOrderQuantity());
        holder.tv_orderPrice.setText("Price:"+list.getOrderPrice());
        holder.bind(order.get(position), listener);
    }
    /**
     * gets the size of array to define how many cards will be displayed
     */
    @Override
    public int getItemCount() {
        return order.size();
    }

    /**
     * class assigns values to views by finding them in the layout
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_orderID, tv_orderName, tv_orderQuantity, tv_orderPrice;
        /**
         * method that assigns values to views by finding them in the layout
         */
        public ViewHolder(View itemView) {
            super(itemView);
            tv_orderID = (TextView) itemView.findViewById(R.id.tv_orderID);
            tv_orderName = (TextView) itemView.findViewById(R.id.tv_orderName);
            tv_orderQuantity = (TextView) itemView.findViewById(R.id.tv_orderQuantity);
            tv_orderPrice = (TextView) itemView.findViewById(R.id.tv_orderPrice);
        }
        /**
         * on click listener for a single card
         */
        public void bind(final Order order, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(order);
                }
            });
        }
    }
}
