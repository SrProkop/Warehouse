package ru.t1.warehouse.runnable;

import ru.t1.warehouse.models.Warehouse;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Consumer implements Runnable {

    private final Warehouse WAREHOUSE;
    private final CyclicBarrier BARRIER;
    private int countProduct;
    private int countBuy;

    public Consumer(Warehouse warehouse, CyclicBarrier barrier){
        this.WAREHOUSE = warehouse;
        this.BARRIER = barrier;
        this.countProduct = 0;
        this.countBuy = 0;
        new Thread(this).start();
    }

    public void run() {
        Random random = new Random();
        while (true) {
            try {
                BARRIER.await();
                if (WAREHOUSE.getProduct() > 0) {
                    countProduct += WAREHOUSE.takeProduct(random.nextInt(9) + 1);
                    countBuy++;
                }
                BARRIER.await();
                if (WAREHOUSE.getProduct() == 0) {
                    break;
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - Продуктов куплено: " +
                countProduct + "\nПокупок сделано: " +
                countBuy + "\n");
    }
}
