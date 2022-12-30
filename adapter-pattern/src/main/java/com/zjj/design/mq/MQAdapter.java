package com.zjj.design.mq;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MQAdapter {
    public static RebateInfo filter(String json, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        return filter(JSON.parseObject(json, Map.class), link);
    }
    public static RebateInfo filter(Map obj, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        RebateInfo rebateInfo = new RebateInfo();

        for (String key : link.keySet()){
            Object val = obj.get(link.get(key));
            Field field = RebateInfo.class.getField(key);
            System.out.println(field.getType().getName());
            RebateInfo.class.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), field.getType()).invoke(rebateInfo, val);
        }
        return rebateInfo;
    }
}
