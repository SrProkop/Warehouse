package ru.t1.warehouse;

import ru.t1.warehouse.models.Warehouse;
import ru.t1.warehouse.runnable.Consumer;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            if (isPositiveInteger(args[0])) {
                int numberConsumer = Integer.parseInt(args[0]);
                CyclicBarrier cyclicBarrier = new CyclicBarrier(numberConsumer);
                Warehouse warehouse = new Warehouse();
                for (int i = 0; i < numberConsumer; i++) {
                    new Consumer(warehouse, cyclicBarrier);
                }
            }
        } else {
            System.out.println("Передайте в аргументы количество покупателей");
        }
    }

    private static boolean isPositiveInteger(String line) {
        try {
            int number = Integer.parseInt(line);
            if (number < 1) {
                System.out.println("Переданный аргумент меньше 1");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Переданный аргумент не является числом");
            return false;
        }
    }
}
