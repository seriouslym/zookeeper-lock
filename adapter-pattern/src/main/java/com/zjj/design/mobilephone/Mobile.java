package com.zjj.design.mobilephone;

public class Mobile {

    public void inputPower(V5Power v5Power){
        int i = v5Power.ProvideV5Power();
        System.out.println("手机（客户端）：我需要5V电压充电，现在是-->" + i + "V");
    }

}
