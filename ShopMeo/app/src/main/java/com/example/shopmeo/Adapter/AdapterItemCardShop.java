package com.example.shopmeo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopmeo.Model.Itemcard_shop;
import com.example.shopmeo.R;

import java.util.ArrayList;

public class AdapterItemCardShop extends RecyclerView.Adapter<AdapterItemCardShop.mAdapterItemCardShop> {
    public AdapterItemCardShop(ArrayList<Itemcard_shop> itemShopCard) {
        this.itemShopCard = itemShopCard;
    }

    ArrayList<Itemcard_shop> itemShopCard;

    @NonNull
    @Override
    public mAdapterItemCardShop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcard_shop, parent, false);
        return new mAdapterItemCardShop(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mAdapterItemCardShop holder, int position) {
        Itemcard_shop item = itemShopCard.get(position);

        Glide.with(holder.img.getContext()).load(item.getUrlImg()).into(holder.img);
        holder.header.setText(item.getHeader());
        holder.cost.setText(item.getCost());
        holder.count.setText(String.valueOf(item.getCount()));
    }

    @Override
    public int getItemCount() {
        return itemShopCard.size();
    }

    class mAdapterItemCardShop extends RecyclerView.ViewHolder{

        ImageView img;
        TextView header, cost;
        EditText count;
        public mAdapterItemCardShop(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imagecard);
            header = itemView.findViewById(R.id.headcard);
            cost = itemView.findViewById(R.id.costcard);
            count = itemView.findViewById(R.id.countcard);
        }
    }
}
