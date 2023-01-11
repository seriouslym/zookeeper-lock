package com.zjj.design;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangjiajie
 */
@Data
@AllArgsConstructor
public class RedGemDecorator implements IEquipDecorator{
    private IEquip iEquip;
    @Override
    public int calculateAttack() {
        return 10 + iEquip.calculateAttack();
    }

    @Override
    public String description() {
        return "红宝石 + " + iEquip.description();
    }
}
