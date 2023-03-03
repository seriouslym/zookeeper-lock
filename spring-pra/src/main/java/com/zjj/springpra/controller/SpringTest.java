package com.zjj.springpra.controller;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
public class SpringTest {
    String name;
    int age;

    public void func() {
        for (int i = 0; i < 100000; i++){

        }
        System.out.println(12312312);
    }

}
