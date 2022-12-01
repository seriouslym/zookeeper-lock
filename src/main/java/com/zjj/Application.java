package com.zjj;

import com.zjj.dao.Student;
import com.zjj.mapper.StudentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjj.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
