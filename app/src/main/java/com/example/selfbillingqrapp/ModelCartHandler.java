package com.example.selfbillingqrapp;

public class ModelCartHandler
{
    private String product_name;
    private int qty;
    private int price;
    public static int cash=0;

    // Constructor
    public ModelCartHandler(String product_name, int qty,int price) {
        this.product_name = product_name;
        this.qty = qty;
        this.price = price;
    }

    // Getter and Setter to be Accessed by CardAdapter
    public String get_name() {
        return product_name;
    }

    public void set_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int setTotalCash(int cash_in) {
        return cash_in;
    }
}
