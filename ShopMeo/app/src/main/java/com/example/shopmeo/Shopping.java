package com.example.shopmeo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopmeo.Adapter.AdapterItemCardShop;
import com.example.shopmeo.Model.Itemcard_shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Shopping extends AppCompatActivity {

    static ArrayList<Itemcard_shop> itemShopCard = new ArrayList<Itemcard_shop>();

    public static ArrayList<Itemcard_shop> getItemShopCard() {
        return itemShopCard;
    }

    RecyclerView recyclerViewshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Mapping();
        filterarray(itemShopCard);
        showCard(itemShopCard);
    }

    private static void filterarray(ArrayList<Itemcard_shop> itemShopCard  ) {
//        for(int i = 0; i< itemShopCard.size(); i++){
//            for (int j = i+1; j<itemShopCard.size(); j++){
//                if(itemShopCard.get(i).getHeader() == itemShopCard.get(j).getHeader()){
//                    itemShopCard.remove(i);
//                }
//            }
//        }
//        for(int i = 0; i< itemShopCard.size(); i++){
//            for (int j = i+1; j<itemShopCard.size(); j++){
//                if(itemShopCard.get(i).getHeader() == itemShopCard.get(j).getHeader()){
//                    itemShopCard.remove(i);
//                }
//            }
//        }
        HashMap<String, Itemcard_shop> itemMap = new HashMap<>();

        for (Itemcard_shop item : itemShopCard) {
            String header = item.getHeader();
            if (!itemMap.containsKey(header) || itemMap.get(header).getCount() < item.getCount()) {
                itemMap.put(header, item);
            }
        }

        itemShopCard.clear();
        itemShopCard.addAll(itemMap.values());
    }

    private void Mapping() {


    }

    public static void addtoShopcard(String urlimg, String header, String cost){
        int cout = 1;
        for(int i = 0; i< itemShopCard.size(); i++){
            if(itemShopCard.get(i).getHeader() == header){
                cout ++;
            }
        }
        Itemcard_shop item = new Itemcard_shop(urlimg, header, cost, cout);
        itemShopCard.add(item);
    }

    public void showCard(ArrayList<Itemcard_shop> itemShopCard ){
        recyclerViewshop = findViewById(R.id.recycleviewShop);
        AdapterItemCardShop adapterItemCardShop = new AdapterItemCardShop(itemShopCard);
        recyclerViewshop.setAdapter(adapterItemCardShop);
        recyclerViewshop.setLayoutManager(new GridLayoutManager(this, 1));
    }
}