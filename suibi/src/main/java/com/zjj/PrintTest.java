package com.zjj;

public class PrintTest {
    public static class instance {
        public static PrintTest x = new PrintTest();
    }
    public static PrintTest getInstance() {
        return instance.x;
    }

    public static void main(String[] args) {
        PrintTest instance = PrintTest.getInstance();
        PrintTest instance1 = PrintTest.getInstance();
        PrintTest instance2 = PrintTest.getInstance();
        System.out.println(instance == instance1);
        System.out.println(instance == instance2);
        System.out.println(instance1 == instance2);


    }

}
