package com.zjj.design;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjiajie
 */
@Data
public class Employee {
    private String name;
    private String dept;
    private int salary;


    private List<Employee> subordinates;
    public Employee(String name, String dept, int salary){
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.subordinates = new ArrayList<>();
    }
    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    public void add(Employee employee){
        this.subordinates.add(employee);
    }
    public void remove(Employee employee){
        this.subordinates.remove(employee);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", subordinates=" + subordinates +
                '}';
    }

}
