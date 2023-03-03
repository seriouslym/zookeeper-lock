package com.zjj;

import java.util.*;
import java.util.concurrent.*;

public class Container {

   private int CAPACITY = 10;
   private int cnt = 0;
   private List<Bread> breadContainer;
   public Container(){
       breadContainer = new ArrayList<>(CAPACITY);
   }


   // 生产
   public void put(Bread bread) throws InterruptedException {
       synchronized (breadContainer) {
           while(breadContainer.size() == CAPACITY){
               // 加入等待队列 放弃锁
               breadContainer.wait();
           }
           breadContainer.add(bread);
           breadContainer.notify();
           System.out.printf("%s : 生产者生产一个面包%s 并通知消费者消费\n", Thread.currentThread().getName(), bread.getId());
       }
   }
   // 消费
   public void get() throws InterruptedException {
       synchronized (breadContainer){

           while(breadContainer.size() == 0){
               breadContainer.wait();
           }
           Bread bread = breadContainer.remove(breadContainer.size() - 1);
           cnt += 1;
           breadContainer.notify();
           System.out.printf("%s : 消费者消费一个面包%s\n", Thread.currentThread().getName(), bread.getId());
       }
   }

   public static Map<Integer, Integer> map = new HashMap<>(4);

    public static void main(String[] args) throws InterruptedException {
//       Container container = new Container();
//       Thread produce = new Thread(new Runnable() {
//           @Override
//           public void run() {
//               for(int i = 0; i < 5000; i++){
//                   Bread bread = new Bread(i);
//                   try {
//                       container.put(bread);
//                   } catch (InterruptedException e) {
//                       throw new RuntimeException(e);
//                   }
//               }
//           }
//       }, "producer");
//
//        Thread consumer = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (;;){
//                        container.get();
//                    }
//
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }, "consumer");
//       produce.start();
//       consumer.start();
//       Thread.sleep(5000);
//       System.out.println(container.cnt);
//        Thread pre = Thread.currentThread();
//        for(int i = 0; i < 10; i++){
//            Thread t = new Thread(new joinTest(pre), String.valueOf(i));
//            pre = t;
//            t.start();
//        }
//        Thread.sleep(5000);
//        System.out.println(1111);
//        System.out.println(Thread.currentThread().getName() + " terminated");
//        int processors = Runtime.getRuntime().availableProcessors();
//        int threadCnt = 1000;
//        CountDownLatch countDownLatch = new CountDownLatch(threadCnt);
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(processors, 30, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardPolicy());
//        for(int i = 0; i < threadCnt; i ++) {
//            executor.submit(new HashMapTest(i, countDownLatch));
//        }
//        countDownLatch.await();
//        System.out.println(map);
//        System.out.println(map.get(5));
//        System.out.println(map.size());
//        executor.shutdown();
        StringBuilder message = new StringBuilder("\"【工单关闭提醒】\\n\"" +
                "            \"您发起的工单已经超过两周未完结，系统已自动关闭流程，如有需要，请重新发起申请。\\n\"" +
                "            \"已关闭的工单链接：\\n\";");
        System.out.println(message.toString());

    }
    public static class HashMapTest implements Runnable{
        private int idx;
        private CountDownLatch countDownLatch;
        HashMapTest(int idx, CountDownLatch countDownLatch){
            this.idx = idx;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            map.put(idx, idx + 1);
            countDownLatch.countDown();
        }
    }
    public static class joinTest implements Runnable{
       private Thread pre;
       joinTest(Thread thread){
           pre = thread;
       }

        @Override
        public void run() {
            try {
                pre.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " terminated");
        }
    }


}
