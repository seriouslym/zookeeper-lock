package com.zjj.springpra;

import com.zjj.springpra.controller.SpringTest;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringPraApplication implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(SpringPraApplication.class);
        SpringTest springTest = applicationContext.getBean("springTest", SpringTest.class);
        springTest.func();

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringPraApplication.applicationContext = applicationContext;
    }
}
