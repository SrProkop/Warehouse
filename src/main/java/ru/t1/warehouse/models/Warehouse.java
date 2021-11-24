package ru.t1.warehouse.models;

public class Warehouse {

    private int product = 1000;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public synchronized int takeProduct(int count) {
        if (count > product) {
            count = product;
            product = 0;
            return count;
        }
        product = product - count;
        return count;
    }

}
