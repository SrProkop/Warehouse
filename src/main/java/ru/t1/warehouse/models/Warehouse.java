package ru.t1.warehouse.models;

public class Warehouse {

    private volatile int product = 1000;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public synchronized int takeProduct(int count) {
        if (product <= 0) {
            return 0;
        }
        if (count > product) {
            count = product;
            return count;
        }
        product = product - count;
        return count;
    }

}
