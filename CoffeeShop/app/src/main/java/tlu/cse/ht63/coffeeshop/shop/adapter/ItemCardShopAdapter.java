package tlu.cse.ht63.coffeeshop.shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import tlu.cse.ht63.coffeeshop.R;
import tlu.cse.ht63.coffeeshop.shop.model.Itemcard_shop;

public class ItemCardShopAdapter extends RecyclerView.Adapter<ItemCardShopAdapter.mAdapterItemCardShop> {
    TextView tvsumcost;
    public static ItemCardShopAdapter.OnItemChangeListener OnItemChangeListener;
    private OnItemChangeListener onItemChangeListener;
    public ItemCardShopAdapter(ArrayList<Itemcard_shop> itemShopCard, OnItemChangeListener onItemChangeListener) {
        this.itemShopCard = itemShopCard;
        this.onItemChangeListener = onItemChangeListener;
    }

    ArrayList<Itemcard_shop> itemShopCard;

    @NonNull
    @Override
    public mAdapterItemCardShop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditemshop, parent, false);
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

    public interface OnItemChangeListener {
        void onItemChanged();
    }
    @Override
    public int getItemCount() {
        return itemShopCard.size();
    }

    class mAdapterItemCardShop extends RecyclerView.ViewHolder{

        ImageView img;
        TextView header, cost;
        TextView count, raise, reduce;
        public mAdapterItemCardShop(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imagecard);
            header = itemView.findViewById(R.id.headcard);
            cost = itemView.findViewById(R.id.costcard);
            count = itemView.findViewById(R.id.countcard);
            raise = itemView.findViewById(R.id.raise);
            reduce = itemView.findViewById(R.id.reduce);

            raise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Tăng số lượng và cập nhật giao diện
                        itemShopCard.get(position).setCount(itemShopCard.get(position).getCount() + 1);
                        count.setText(String.valueOf(itemShopCard.get(position).getCount()));

                        // Gọi sự kiện thay đổi
                        if (onItemChangeListener != null) {
                            onItemChangeListener.onItemChanged();
                        }
                    }
                }
            });

            // Gán sự kiện cho nút giảm
            reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Giảm số lượng nếu lớn hơn 1 và cập nhật giao diện
                        if (itemShopCard.get(position).getCount() > 1) {
                            itemShopCard.get(position).setCount(itemShopCard.get(position).getCount() - 1);
                            count.setText(String.valueOf(itemShopCard.get(position).getCount()));


                            // Gọi sự kiện thay đổi
                            if (onItemChangeListener != null) {
                                onItemChangeListener.onItemChanged();
                            }
                        }
                    }
                }
            });
        }
    }

}
