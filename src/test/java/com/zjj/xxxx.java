package com.zjj;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjj.dao.Student;
import com.zjj.mapper.StudentMapper;
import com.zjj.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class xxxx {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @Test
    public void test(){
        System.out.println(1);

//       1 QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
//         studentQueryWrapper.eq(Student::getId, 5);
//        studentQueryWrapper.gt(Student::getScore, 5);
        List<Student> students = studentMapper.selectList(null);
        students.forEach(System.out::println);
        studentService.test();

        
    }

}
