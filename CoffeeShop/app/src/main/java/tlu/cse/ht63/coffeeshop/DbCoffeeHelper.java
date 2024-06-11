package tlu.cse.ht63.coffeeshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import tlu.cse.ht63.coffeeshop.shop.model.card;

public class DbCoffeeHelper extends SQLiteOpenHelper {
    //data base
    private static String dbName = "shop.dp";

    // tb user
    private static String TB_user = "User";
    private static String idUser = "idUser";
    private static String hovaten = "hovaten";
    private static String userName = "userName";
    private static String passW = "passW";
    private static String datecreate = "datecreate";
    private static String role = "role";
    private static String state = "state";

    //tb item
    private static String TB_itemShop = "ItemShop";
    private static String idItem = "idItem";
    private static String urlimg = "image";
    private static String header = "header";
    private static String subheader = "subhead";
    private static String cost = "cost";


    public DbCoffeeHelper(@Nullable Context context) {
        super(context, dbName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tbUser = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT, " +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT)",
                TB_user, idUser, hovaten, userName, passW, datecreate, role, state);

        String tbItemshop = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT)",
                TB_itemShop, idItem, urlimg, header, subheader, cost);

        db.execSQL(tbUser);
        db.execSQL(tbItemshop);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String updateUser = "DROP TABLE IF EXISTS " + TB_user;
        String updateItemShop = "DROP TABLE IF EXISTS " + TB_itemShop;

        db.execSQL(updateUser);
        db.execSQL(updateItemShop);

        onCreate(db);
    }



    public ArrayList<card> getItemShop(){
        ArrayList<card> itemShopArrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM ItemShop";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                card itemShop = new card();
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