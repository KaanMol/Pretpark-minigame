package com.a2.essteling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {
    private static final String LOG_TAG = ShopListAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private final LinkedList<ShopItem> mShopItems;
    private ShopItemListener listener;



    public ShopListAdapter(Context context, LinkedList<ShopItem> mShopItems, ShopItemListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mShopItems = mShopItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mShopView = mInflater.inflate(R.layout.layout_shopitem, parent, false);
        return new ShopViewHolder(mShopView, this);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        holder.ShopItemName.setText(mShopItems.get(position).getName());
        holder.ShopItemPrice.setText(mShopItems.get(position).getPrice());
        holder.ShopItemImage.setImageResource(mShopItems.get(position).getImage());

        holder.ShopItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, view.toString() + " Button clicked!");
                listener.onItemClicked(mShopItems.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mShopItems.size();
    }

    class ShopViewHolder extends RecyclerView.ViewHolder {
        final ShopListAdapter mAdapter;
        public final TextView ShopItemName;
        public final TextView ShopItemPrice;
        public final ImageView ShopItemImage;

        public ShopViewHolder(@NonNull View itemView, ShopListAdapter adapter) {
            super(itemView);
            ShopItemName = itemView.findViewById(R.id.itemName);
            ShopItemPrice = itemView.findViewById(R.id.itemPrice);
            ShopItemImage = itemView.findViewById(R.id.ItemImage);
            this.mAdapter = adapter;
        }
    }


}
