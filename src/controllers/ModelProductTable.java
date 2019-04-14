package controllers;

public class ModelProductTable {
    private int id;
    private String name, price, type, shop, data;

    ModelProductTable(String name, String price, String type, String shop, String data) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.shop = shop;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    public String getShop() {
        return shop;
    }

    void setShop(String shop) {
        this.shop = shop;
    }

    public String getData() {
        return data;
    }

    void setDate(String data) {
        this.data = data;
    }

    int getId() {
        return id;
    }
}
