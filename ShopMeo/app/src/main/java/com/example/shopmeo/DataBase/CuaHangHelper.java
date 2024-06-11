package com.example.shopmeo.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.shopmeo.Model.ItemShop;
import com.example.shopmeo.SignInActivity;

import java.util.ArrayList;
import java.util.Random;

public class CuaHangHelper extends SQLiteOpenHelper {
    //data base
    private static String dbName = "shop.dp";

    // tb user
    private static String TB_user = "User";
    private static String idUser = "idUser";
    private static String hovaten = "hovaten";
    private static String phoneNum = "phoneNum";
    private static String passW = "passW";
    private static String rePassW = "repassW";

    //tb item
    private static String TB_itemShop = "ItemShop";
    private static String idItem = "idItem";
    private static String urlimg = "image";
    private static String header = "header";
    private static String subheader = "subhead";
    private static String cost = "cost";

    //tb shopping card
    private static String TB_shopping = "Shopping";
    private static String iditemcard = "idshopcard";
    private static String urlimgcard = "imagecard";
    private static String headercard = "headercard";
    private static String costcard = "costcard";
    private static String countcard = "countcard";


    public CuaHangHelper(@Nullable Context context) {
        super(context, dbName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbUser = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT, " +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT)",
                TB_user, idUser, hovaten, phoneNum, passW, rePassW);

        String tbItemshop = String.format("CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT)",
                TB_itemShop, idItem, urlimg, header, subheader, cost);

        String tbShopping = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s INT)",
                TB_shopping, iditemcard, urlimgcard, headercard, costcard, countcard);

        db.execSQL(tbUser);
        db.execSQL(tbItemshop);
        db.execSQL(tbShopping);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String updateUser = "DROP TABLE IF EXISTS " + TB_user;
        String updateItemShop = "DROP TABLE IF EXISTS " + TB_itemShop;
        String updateItemShopCard = "DROP TABLE IF EXISTS " + TB_shopping;

        db.execSQL(updateUser);
        db.execSQL(updateItemShop);
        db.execSQL(updateItemShopCard);

        onCreate(db);
    }

    public void insertitemShop() {
        String mheader, mcost;

        for(int i = 0; i < 20; i++){
            mheader = "Iphone " + i;

            Random random = new Random();
            int cost = random.nextInt(100000000);
            mcost = cost + " VND";

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("image" , "https://cellphones.com.vn/sforum/wp-content/uploads/2020/09/anh-render-iPhone12-1.jpg");
            contentValues.put("header" , mheader);
            contentValues.put("subhead" , "sale");
            contentValues.put("cost" , mcost);

            long check = db.insert("ItemShop", null, contentValues);
        }
    }

    public ArrayList<ItemShop> getItemShop(){
        ArrayList<ItemShop> itemShopArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM ItemShop";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                ItemShop itemShop = new ItemShop();
                int imageIndex = cursor.getColumnIndex("image");
                int headerIndex = cursor.getColumnIndex("header");
                int subheadIndex = cursor.getColumnIndex("subhead");
                int costIndex = cursor.getColumnIndex("cost");

                if(imageIndex != -1) {
                    itemShop.setUrlImg(cursor.getString(imageIndex));
                }
                if(headerIndex != -1) {
                    itemShop.setHeader(cursor.getString(headerIndex));
                }
                if(subheadIndex != -1) {
                    itemShop.setSubhead(cursor.getString(subheadIndex));
                }
                if(costIndex != -1) {
                    itemShop.setCost(cursor.getString(costIndex));
                }

                itemShopArrayList.add(itemShop);
            }while (cursor.moveToNext());
        }

        return itemShopArrayList;
    }

}
