package tlu.cse.ht63.coffeeshop.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import tlu.cse.ht63.coffeeshop.R;
import tlu.cse.ht63.coffeeshop.shop.adapter.ItemCardShopAdapter;
import tlu.cse.ht63.coffeeshop.shop.model.Itemcard_shop;
import tlu.cse.ht63.coffeeshop.total.TotalActivity;

public class ShoppingActivity extends AppCompatActivity {

    private GestureDetectorCompat gestureDetectorCompat;
    static TextView tvsumcost;

    static ArrayList<Itemcard_shop> itemShopCard = new ArrayList<Itemcard_shop>();

    public static ArrayList<Itemcard_shop> getItemShopCard() {
        return itemShopCard;
    }
    RecyclerView recyclerViewshop;

    Button btntt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainshop), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mapping();
        filterarray(itemShopCard);
        showCard(itemShopCard);

        btntt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ShoppingActivity.this, TotalActivity.class);
                it.putExtra("key", tvsumcost.getText().toString());
                startActivity(it);
            }
        });

        gestureDetectorCompat = new GestureDetectorCompat(this, new GestureListener());

        findViewById(R.id.recycleviewShop).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return true;
            }
        });
    }
    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            sumMoney(itemShopCard);

            return true;
        }

    }
    private static void filterarray(ArrayList<Itemcard_shop> itemShopCard  ) {
        HashMap<String, Itemcard_shop> itemMap = new HashMap<>();

        for (Itemcard_shop item : itemShopCard) {
            String header = item.getHeader();
            if (!itemMap.containsKey(header) || itemMap.get(header).getCount() < item.getCount()) {
                itemMap.put(header, item);
            }
        }
        sumMoney(itemShopCard);

        itemShopCard.clear();
        itemShopCard.addAll(itemMap.values());
    }

    public void showCard(ArrayList<Itemcard_shop> itemShopCard ){
        recyclerViewshop = findViewById(R.id.recycleviewShop);
        ItemCardShopAdapter adapterItemCardShop = new ItemCardShopAdapter(itemShopCard, ItemCardShopAdapter.OnItemChangeListener);
        recyclerViewshop.setAdapter(adapterItemCardShop);
        recyclerViewshop.setLayoutManager(new GridLayoutManager(this, 1));
    }

    private static void sumMoney(@NonNull ArrayList<Itemcard_shop> itemShopCard) {
        double cost = 0;
        for(int i=0; i<itemShopCard.size(); i++){
            cost += Double.parseDouble(itemShopCard.get(i).getCost()) * itemShopCard.get(i).getCount();
        }

        tvsumcost.setText(String.valueOf(cost));
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
    private void mapping() {
        tvsumcost = findViewById(R.id.sumcost);
        btntt = findViewById(R.id.btntt);
    }

}