package com.zjj;

import java.util.ArrayList;
import java.util.List;

public class BlockArray<T> {
    // 阻塞队列大小
    private int capacity;
    private List<T> queue;

    public BlockArray(int capacity) {
        this.capacity = capacity;
        queue = new ArrayList<>(capacity);
    }
    public void put(T t) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == capacity) {
                System.out.println("已经满了等一会");
                Thread.sleep(2000);
                queue.wait();
            }
            queue.add(t);
            queue.notify();
        }
    }
    public T get() throws InterruptedException {
        synchronized (queue) {
            while(queue.size() == 0) {
                queue.wait();

            }
            T remove = queue.remove(0);
            queue.notify();
            return remove;
        }
    }

    public static void main(String[] args) {
        BlockArray<Bread> blockArray = new BlockArray<>(10);
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new work(i, blockArray), "thread" + String.valueOf(i));
            thread.start();
        }

    }

    public static class work implements Runnable {
        private int idx;
        private BlockArray<Bread> breadList;
        public work(int idx, BlockArray<Bread> breadList) {
            this.idx = idx;
            this.breadList = breadList;
        }
        @Override
        public void run() {
            if (idx < 15) {
                try {
                    breadList.put(new Bread(idx));
                    System.out.println(Thread.currentThread().getName() + ": put " + idx);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    Bread bread = breadList.get();
                    System.out.println(Thread.currentThread().getName() + ": get " + bread.getId());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
