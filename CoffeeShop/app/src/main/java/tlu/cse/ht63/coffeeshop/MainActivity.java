package tlu.cse.ht63.coffeeshop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tlu.cse.ht63.coffeeshop.shop.ShoppingActivity;
import tlu.cse.ht63.coffeeshop.shop.adapter.CardAdapter;
import tlu.cse.ht63.coffeeshop.shop.model.card;

public class MainActivity extends AppCompatActivity {

    ArrayList<card> itemShopArrayList;
    ArrayList<card> insertArr;
    RecyclerView recyclerViewManHinhChinh;
    DbCoffeeHelper dbHelper;
    ImageButton shoppingcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        deleteDatabase("shop.db");

        mapping();

        shoppingcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ShoppingActivity.class);
                startActivity(it);
            }
        });

        dbHelper = new DbCoffeeHelper(this);
        insertArr  = new ArrayList<>();
        insertArr.add(new card("https://cdn.tgdd.vn/2021/11/CookDish/ca-phe-capuchino-la-gi-cach-uong-cafe-capuchino-va-cac-loai-avt-1200x676.jpg", "Capuchino","Even if one is not a Coffee fan, all must be aware of this famous beverage. Cappuccino is an espresso-based drink that comes from an Austrian drink called Kapuziner. It is touted as the ‘ideal morning Coffee,’ prepared with steamed milk foam or micro foam. ", "50000"));
        insertArr.add(new card("https://cdn.tgdd.vn/2021/09/CookProduct/Cafe-latte-la-gi-latte-va-cupuchino-co-gi-khac-cac-loai-cach-pha-latte-0-1200x676.jpg", "Latte","Latte derives its roots from the Italian land. The word Latte means ‘milk Coffee’ in Italy. This type of Coffee is a much creamier version, said to have served the American tourists in Italy who thought cappuccino was too strong for their palate. ", "40000"));
        insertArr.add(new card("https://dayphache.edu.vn/wp-content/uploads/2018/01/frappuccino-la-gi.jpg", "Frappuccino ","Another popularly known drink is Frappuccino. Although its origin is an accidental one, today, Starbucks owns the trademarks for Frappuccino.  ", "45000"));
        insertArr.add(new card("https://www.thespruceeats.com/thmb/POPhcPYBWx7fNJu8Bc7YjS-Flso=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/SES-mocha-4797918-hero-01-1-f8fb7ebd74914895b61366f6fc1d4b05.jpg", "Mocha ","Some believe that Moka or Mocha is a type of arabica grown in Yemen. On the other hand, the drink is considered to be popularised by an Italian Coffee house named Caffe al Bicerin.  ", "55000"));
        insertArr.add(new card("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzAn7sFMZCSR1VWT-9TPvJhlVghgu0keOYyg&s", "Americano ","Americano is a strong Italian espresso drink. Its history dates back to World War II when American soldiers were stationed in Italy. They wanted something resembling the black Coffee that they used to drink. This adaptation was received to such an extent that it got its name as ‘Americano.’ ", "50000"));

        insertitemShop();
        RenderAdaptoscreen();
    }
    public void insertitemShop() {
        for(int i = 0; i<insertArr.size(); i++){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("image" , insertArr.get(i).getUrlImg());
            contentValues.put("header" ,insertArr.get(i).getHeader());
            contentValues.put("subhead" , insertArr.get(i).getSubhead());
            contentValues.put("cost" , insertArr.get(i).getCost());
            long check = db.insert("ItemShop", null, contentValues);
        }

    }
    private void RenderAdaptoscreen() {
        itemShopArrayList = dbHelper.getItemShop();

        recyclerViewManHinhChinh = findViewById(R.id.recycleviewmanhinhchinh);
        CardAdapter adapterItemShop = new CardAdapter(itemShopArrayList, MainActivity.this);
        recyclerViewManHinhChinh.setAdapter(adapterItemShop);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(this, 2));
    }
    private void mapping() {
        shoppingcard = findViewById(R.id.shoppingcard);
        recyclerViewManHinhChinh = findViewById(R.id.recycleviewmanhinhchinh);
    }
}