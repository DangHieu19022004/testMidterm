package com.example.shopmeo.Model;

import android.content.Intent;

public class Itemcard_shop {

    public Itemcard_shop(String urlImg, String header, String cost, int count) {
        this.urlImg = urlImg;
        this.header = header;
        this.cost = cost;
        this.count = count;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    String urlImg;
    String header;
    String cost;
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
