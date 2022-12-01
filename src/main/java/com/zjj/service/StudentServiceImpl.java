package com.zjj.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjj.dao.Student;
import com.zjj.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    public StudentMapper studentMapper;


    @Override
    public void test() {
        List<Student> students = studentMapper.selectList(null);
        System.out.println("jiagong");
        for(int i = 0; i < 3; i++){
            System.out.println(students.get(i));
        }
    }
}
