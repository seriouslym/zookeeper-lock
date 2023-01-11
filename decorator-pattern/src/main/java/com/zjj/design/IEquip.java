package com.zjj.design;

/**
 * @author zhangjiajie
 */
public interface IEquip {
    /** 计算攻击力
     * @return int
     */
    public int calculateAttack();

    /** 武器描述
     * @return
     */
    String description();
}
