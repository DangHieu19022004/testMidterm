package com.example.shopmeo.Model;

public class ItemShop {
    public ItemShop() {

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

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    String urlImg;
    String header;
    String subhead;
    String cost;

    public ItemShop(String urlImg, String header, String subhead, String cost) {
        this.urlImg = urlImg;
        this.header = header;
        this.subhead = subhead;
        this.cost = cost;
    }
}
