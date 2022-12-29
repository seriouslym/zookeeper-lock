package com.zjj.design.factory;

import com.zjj.design.util.ClassLoaderUtils;
import sun.misc.ClassLoaderUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler{
    public ICacheAdapter adapter;
    public <T> T getProxy(Class<T> interfaceClass, ICacheAdapter iCacheAdapter){
        this.adapter = iCacheAdapter;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ICacheAdapter.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(adapter, args);
    }
}
