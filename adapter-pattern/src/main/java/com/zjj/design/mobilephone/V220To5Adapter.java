package com.zjj.design.mobilephone;


/**
 * 接口适配
 */
public class V220To5Adapter implements V5Power{
    private V220Power v220Power;
    public V220To5Adapter(V220Power v220Power){
        this.v220Power = v220Power;
    }
    @Override
    public int ProvideV5Power() {
        int i = v220Power.ProvideV220Power();
        System.out.format("正在适配电压 %s \n",i);
        return 5;
    }
}
