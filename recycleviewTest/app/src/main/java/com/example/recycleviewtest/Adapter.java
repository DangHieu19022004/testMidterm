package com.example.recycleviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    public Adapter(List<Card> cardList) {
        this.cardList = cardList;
    }
    private List<Card> cardList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.imgView.setImageResource(card.getImgID());
        holder.header.setText(card.getHeader());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgView;
        private CardView cardView;
        private TextView header;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgv);
            header = itemView.findViewById(R.id.header);
            cardView = itemView.findViewById(R.id.cardV);
        }
    }
}
