package com.example.selfbillingqrapp;

public class ModelCartHandler
{
    private String product_name;
    private String mfg;
    private int price;

    // Constructor
    public ModelCartHandler(String product_name, String mfg,int price) {
        this.product_name = product_name;
        this.mfg = mfg;
        this.price = price;
    }

    // Getter and Setter
    public String get_name() {
        return product_name;
    }

    public void set_name(String product_name) {
        this.product_name = product_name;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
