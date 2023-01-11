package com.zjj.design;

/**
 * @author zhangjiajie
 */
public class SwordEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "倚天屠龙剑";
    }
}
