package com.zjj.design.abstractP;

import com.zjj.design.instance.DrawAPI;

public abstract class Shape {
    protected DrawAPI drawAPI;
    public Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
