package com.zjj;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class JoinTest {



    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        Work work1 = new Work(1, 100, countDownLatch);
        Work work2 = new Work(101, 500, countDownLatch);
        Work work3 = new Work(501, 1000, countDownLatch);
        Thread thread1 = new Thread(work1,"Thread1");
        Thread thread2 = new Thread(work2,"Thread2");
        Thread thread3 = new Thread(work3,"Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
        countDownLatch.await();
        System.out.println(work1.getSum());
        System.out.println(work2.getSum());
        System.out.println(work3.getSum());
        System.out.println("1 ~ 1000之和: " + (work1.getSum() + work2.getSum() + work3.getSum()));


        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Work1 w1 = new Work1(1, 100, cyclicBarrier);
        Work1 w2 = new Work1(101, 500, cyclicBarrier);
        Work1 w3 = new Work1(501, 1000, cyclicBarrier);
        Thread t1 = new Thread(w1,"Thread1");
        Thread t2 = new Thread(w2,"Thread2");
        Thread t3 = new Thread(w3,"Thread3");
        t1.start();
        t2.start();
        t3.start();
        System.out.println(w1.getSum());
        System.out.println(w2.getSum());
        System.out.println(w3.getSum());
        System.out.println("1 ~ 1000之和: " + (w1.getSum() + w2.getSum() + w3.getSum()));







    }
    public static class Work implements Runnable{
        private int start;
        private int end;
        private CountDownLatch countDownLatch;
        private int sum;
        public Work(int start, int end, CountDownLatch countDownLatch) {
            this.start = start;
            this.end = end;
            this.countDownLatch = countDownLatch;
        }
        public int getSum() {
            return sum;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            countDownLatch.countDown();

        }
    }

    public static class Work1 implements Runnable{
        private int start;
        private int end;
        private CyclicBarrier cyclicBarrier;
        private int sum;
        public Work1(int start, int end, CyclicBarrier cyclicBarrier) {
            this.start = start;
            this.end = end;
            this.cyclicBarrier = cyclicBarrier;
        }
        public int getSum() {
            return sum;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
