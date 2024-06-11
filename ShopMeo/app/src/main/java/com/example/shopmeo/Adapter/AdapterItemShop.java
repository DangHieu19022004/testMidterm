package com.example.shopmeo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopmeo.MainActivity;
import com.example.shopmeo.Model.ItemShop;

import java.util.ArrayList;
import java.util.List;
import com.example.shopmeo.R;
import com.example.shopmeo.Shopping;


public class AdapterItemShop extends RecyclerView.Adapter<AdapterItemShop.mAdapterItemShop>{

    private Context context;
    public AdapterItemShop(ArrayList<ItemShop> itemShopArrayList, Context context) {
        this.context = context;
        this.itemShopArrayList = itemShopArrayList;
    }

    ArrayList<ItemShop> itemShopArrayList;

    @NonNull
    @Override
    public mAdapterItemShop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemshop, parent, false);
        return new mAdapterItemShop(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mAdapterItemShop holder, int position) {
        ItemShop itemShop = itemShopArrayList.get(position);
        Glide.with(holder.imageView.getContext())
                .load(itemShop.getUrlImg())
                .into(holder.imageView);

        holder.editTextHead.setText(itemShop.getHeader());
        holder.editTextSubHead.setText(itemShop.getSubhead());
        holder.editEditTextCost.setText(itemShop.getCost());

    }

    @Override
    public int getItemCount() {
        return itemShopArrayList.size();
    }

    class mAdapterItemShop extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView editTextHead, editTextSubHead, editEditTextCost;
        CardView cardView;
        public mAdapterItemShop(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            editTextHead = itemView.findViewById(R.id.head);
            editTextSubHead = itemView.findViewById(R.id.subhead);
            editEditTextCost = itemView.findViewById(R.id.cost);
            cardView = itemView.findViewById(R.id.carditem);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ItemShop clickedItem = itemShopArrayList.get(position);

                        Shopping.addtoShopcard(clickedItem.getUrlImg(), clickedItem.getHeader(), clickedItem.getCost());

                        Toast.makeText(context, "san pham '" + clickedItem.getHeader() + "' da duoc them vao gio hang", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
