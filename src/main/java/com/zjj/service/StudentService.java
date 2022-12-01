package com.zjj.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjj.dao.Student;
import com.zjj.mapper.StudentMapper;

public interface StudentService extends IService<Student> {
    void test();
}
