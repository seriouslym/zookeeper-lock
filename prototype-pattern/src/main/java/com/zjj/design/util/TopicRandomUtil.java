package com.zjj.design.util;

import java.util.*;

public class TopicRandomUtil {
    public static Topic random(Map<String, String> options, String key){
        Map<String, String> newOptions = new HashMap<>();
        Set<String> originKey = options.keySet(); // A B C D
        List<String> keyList = new ArrayList<>(originKey);
        Collections.shuffle(keyList);   // B D A C  打乱顺序 A -> B  B->D C->A D->C
        int idx = 0;
        String newKey = "";
        for(String each : originKey){
            String tmp = keyList.get(idx++);
            if(each.equals(key)){
                newKey = tmp;
            }
            newOptions.put(tmp, options.get(each));
        }
        return new Topic(newKey, newOptions);

    }
}
