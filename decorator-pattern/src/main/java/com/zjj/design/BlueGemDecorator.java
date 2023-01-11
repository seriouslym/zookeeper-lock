package com.zjj.design;

import lombok.AllArgsConstructor;
import lombok.Data;

/** 蓝宝石装饰
 * @author zhangjiajie
 */
@Data
@AllArgsConstructor
public class BlueGemDecorator implements IEquipDecorator{
    /**
     * 每一个宝石管理一个装备(这个装备 可以是原生的 也可以是镶嵌了宝石的装备)
     */
    private IEquip iEquip;
    @Override
    public int calculateAttack() {
        // 把计算的逻辑交给了代理类
        return 5 + iEquip.calculateAttack();
    }

    @Override
    public String description() {
        return "蓝宝石 + " + iEquip.description();
    }
}
