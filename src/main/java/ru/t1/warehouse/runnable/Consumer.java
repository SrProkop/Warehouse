package ru.t1.warehouse.runnable;

import ru.t1.warehouse.models.Warehouse;

import java.util.concurrent.CountDownLatch;

public class Consumer implements Runnable {

    private final Warehouse warehouse;
    private final CountDownLatch countDownLatch;
    private final int maxProduct;
    private int countProduct;

    public Consumer(Warehouse warehouse, CountDownLatch countDownLatch, int maxProduct){
        this.warehouse = warehouse;
        this.maxProduct = maxProduct;
        this.countDownLatch = countDownLatch;
        this.countProduct = 0;
        new Thread(this).start();
    }

    public void run() {
        while (warehouse.getProduct() > 0 && countProduct < maxProduct) {
            int randomCountProduct = (int) (Math.random() * 10);
            if (randomCountProduct > maxProduct - countProduct) {
                countProduct += warehouse.takeProduct(maxProduct - countProduct);
            } else {
                countProduct += warehouse.takeProduct(randomCountProduct);
            }
        }
        try {
            countDownLatch.countDown();
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (warehouse) {
            if (warehouse.getProduct() > 0) {
                countProduct += warehouse.takeProduct(1);
            }
        }
        System.out.println(Thread.currentThread().getName() + " - " + countProduct);
    }
}
