package com.a2.essteling.Shop;

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

import com.a2.essteling.R;
import com.bumptech.glide.Glide;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {
    private static final String LOG_TAG = ShopListAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private ShopItem[] mShopItems;
    private ShopItemListener listener;
    Context context;


    public ShopListAdapter(Context context, ShopItem[] mShopItems, ShopItemListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mShopItems = mShopItems;
        this.listener = listener;
        this.context = context;
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
        //only work if there are shopitems
        if(mShopItems[0] != null) {
            //set the text
            holder.ShopItemName.setText(mShopItems[position].getName());
            holder.ShopItemPrice.setText(mShopItems[position].getPrice() + " points");

            //set the image via local or url
            if (mShopItems[position].getImageLocal() == -1) {
                Glide.with(context).load(mShopItems[position].getImage()).into(holder.ShopItemImage);
            } else {
                holder.ShopItemImage.setImageResource(mShopItems[position].getImageLocal());
            }
        }

        //react to a clicked shopitem
        holder.ShopItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, view.toString() + " Button clicked!");

                listener.onItemClicked(mShopItems[position]);
            }
        });
    }



    @Override
    public int getItemCount() {
        return mShopItems.length;
    }

    //change the list
    public void setShopItems(ShopItem[] mShopItems) {
        this.mShopItems = mShopItems;
        notifyDataSetChanged();
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
