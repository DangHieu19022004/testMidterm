package tlu.cse.ht63.coffeeshop.shop.model;

public class card {
    public card() {

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

    public card(String urlImg, String header, String subhead, String cost) {
        this.urlImg = urlImg;
        this.header = header;
        this.subhead = subhead;
        this.cost = cost;
    }
}
