package com.zjj.design.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 对应选择题的answer 和options
 * 方便排序
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private String key;
    private Map<String, String> options;
}
