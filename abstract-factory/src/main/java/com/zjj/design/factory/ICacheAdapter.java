package com.zjj.design.factory;

import java.util.concurrent.TimeUnit;

/**
 * 因为不同的集群调用的方法不同，因此还要做一层包装
 */
public interface ICacheAdapter {

    String get(final String key);
    void set(String key, String value);
    void set(String key, String value, long timeout, TimeUnit timeUnit);
    void del(String key);

}
