package tlu.cse.ht63.coffeeshop.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tlu.cse.ht63.coffeeshop.R;
import tlu.cse.ht63.coffeeshop.shop.ShoppingActivity;
import tlu.cse.ht63.coffeeshop.shop.model.card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.mAdapterItemShop>{

    private Context context;
    public CardAdapter(ArrayList<card> itemShopArrayList, Context context) {
        this.context = context;
        this.itemShopArrayList = itemShopArrayList;
    }

    ArrayList<card> itemShopArrayList;

    @NonNull
    @Override
    public mAdapterItemShop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new mAdapterItemShop(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mAdapterItemShop holder, int position) {

        card itemShop = itemShopArrayList.get(position);
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
                        card clickedItem = itemShopArrayList.get(position);

                        ShoppingActivity.addtoShopcard(clickedItem.getUrlImg(), clickedItem.getHeader(), clickedItem.getCost());

                        Toast.makeText(context, "san pham '" + clickedItem.getHeader() + "' da duoc them vao gio hang", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
