package controllers;

public class ModelProductTable {
    int id;
    private String name, price, type, shop, data;

    public ModelProductTable(String name, String price, String type, String shop, String data) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.shop = shop;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getData() {
        return data;
    }

    public void setDate(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }
}
