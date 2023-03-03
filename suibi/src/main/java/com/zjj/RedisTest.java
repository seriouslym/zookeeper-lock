package com.zjj;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

    public static JedisPoolConfig config = new JedisPoolConfig();
    static {
        config.setMaxTotal(50);
        config.setMaxIdle(10);
    }
    public static JedisPool pool = new JedisPool(config, "localhost", 9099);
    public static volatile Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new work(),"thread" + String.valueOf(i));
            thread.start();
        }
    }
    public static class work implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                Jedis redis = pool.getResource();
                while (redis.setnx("lock", "1") == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() + ": 获取到redis 开始执行事务");
                redis.del("lock");
                lock.notifyAll();

            }
        }
    }
}
