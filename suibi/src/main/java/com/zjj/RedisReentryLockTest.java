package com.zjj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RedisReentryLockTest {
    public static JedisPoolConfig config = new JedisPoolConfig();
    static {
        config.setMaxTotal(50);
        config.setMaxIdle(10);
    }
    public static JedisPool pool = new JedisPool(config, "localhost", 6379);
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static class work implements Runnable {
        @Override
        public void run() {
            try{
                lock.lock();
                Jedis redis = pool.getResource();
                if (redis.setnx("lock", "1") == 0) {
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + ": 获取到redis锁 开始执行");
                Thread.sleep(1000);
                condition.signal();
                redis.del("lock");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new work(), "thread" + String.valueOf(i));
            thread.start();
        }
    }

}
