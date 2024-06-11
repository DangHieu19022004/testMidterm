package com.example.recycleviewtest;

public class Card {
    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    private int imgID;
    private String header;


    public Card(int imgID, String header) {
        this.imgID = imgID;
        this.header = header;
    }

    public Card() {
    }
}
