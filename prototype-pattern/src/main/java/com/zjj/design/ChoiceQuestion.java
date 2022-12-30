package com.zjj.design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceQuestion {
    private String question; // 题目
    private Map<String, String> options; // 选项
    private String answer; // 答案
}
